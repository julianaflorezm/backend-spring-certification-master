package co.com.ias.certification.backend.order.adapters.out.persistence;

import co.com.ias.certification.backend.common.PersistenceAdapter;
import co.com.ias.certification.backend.order.adapters.out.persistence.order.OrderJpaEntity;
import co.com.ias.certification.backend.order.adapters.out.persistence.order.OrderMapper;
import co.com.ias.certification.backend.order.adapters.out.persistence.order.OrderRepository;
import co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts.OrderProductsJpaEntity;
import co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts.OrderProductsMapper;
import co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts.OrderProductsRepository;
import co.com.ias.certification.backend.order.application.domain.*;
import co.com.ias.certification.backend.order.application.domain.OrderProducts.OrderProductsNotCreated;
import co.com.ias.certification.backend.order.application.port.out.*;
import co.com.ias.certification.backend.product.adapters.out.persistence.ProductJpaEntity;
import co.com.ias.certification.backend.product.adapters.out.persistence.ProductMapper;
import co.com.ias.certification.backend.product.adapters.out.persistence.ProductRepository;
import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class OrderPersistenceAdapter implements CreateOrderPort, DeleteOrderPort, FindOrderPort, UpdateOrderPort, FindAllOrdersPort {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProductsRepository orderProductsRepository;
    private final OrderProductsMapper orderProductsMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Try<List<Object>> createOrder(List<ProductId> products, IncompleteOrder notCompletedOrder, Customer customer) {
        return Try.of(() -> {
            OrderNotCreated order = calculateDiscount(notCompletedOrder, customer);
            List<Object> completeOrder = new ArrayList<>();
            OrderJpaEntity orderJpaEntity = orderMapper.mapToJpaEntity(order);
            OrderJpaEntity orderCreatedJpa = orderRepository.save(orderJpaEntity);
            Order orderCreated = orderMapper.mapToDomainEntity(orderCreatedJpa);
            List<Product> productsList = new ArrayList<>();
            for (ProductId productId:
                 products) {
                Optional<ProductJpaEntity> currentProduct = productRepository.findById(productId.getValue());
                Product product = currentProduct.map(productMapper::mapToDomainEntity).orElseThrow(() -> {
                    deleteOrder(orderCreated.getId());
                    return new NullPointerException("Some product was not Found");
                });
                OrderProductsNotCreated orderProducts = new OrderProductsNotCreated(orderCreated.getId(), productId);
                OrderProductsJpaEntity orderProductsJpaEntity = orderProductsMapper.mapToJpaEntity(orderProducts, orderCreatedJpa);
                orderProductsRepository.save(orderProductsJpaEntity);
                productsList.add(product);
            }
            completeOrder.add(productsList);
            completeOrder.add(orderCreated);
            return completeOrder;
        });
    }

    public OrderNotCreated calculateDiscount(IncompleteOrder order, Customer customer){
        Iterable<OrderJpaEntity> ordersJpa = orderRepository.findAll();
        List<Order> list = new ArrayList<>();
        ordersJpa.forEach(orderJpa -> list.add(orderMapper.mapToDomainEntity(orderJpa)));
        Integer count = 0;
        for(Order orderEntity: list){
            if(orderEntity.getCustomer().getValue().equals(customer.getValue())){
                count++;
            }
        }
        if(count != 0 && (count + 1) % 3 == 0){
            BigDecimal total = order.getTotal().getValue();
            BigDecimal discount =  new BigDecimal(total.doubleValue() * 0.1);
            BigDecimal finalTotal = new BigDecimal(total.doubleValue() - discount.doubleValue());
            return new OrderNotCreated(
                    customer,
                    Total.of(finalTotal),
                    Discount.of(discount),
                    order.getStatus()
            );
        }else {
            return new OrderNotCreated(
                    customer,
                    order.getTotal(),
                    Discount.of(new BigDecimal(0)),
                    order.getStatus()
            );
        }
    }

    @Override
    public Try<List<Object>> deleteOrder(OrderId id) {
        return Try.of(() -> {
            Long orderId = id.getValue();
            Optional<OrderJpaEntity> currentOrder = orderRepository.findById(orderId);
            List<Object> completeDeleted = new ArrayList<>();
            return currentOrder.map(orderJpa -> {
                List<OrderProductsJpaEntity> orderProducts = orderJpa.getOrderProducts();
                List<Product> productsList = getProducts(orderProducts);
                orderRepository.deleteById(orderId);
                Order order = orderMapper.mapToDomainEntity(orderJpa);
                completeDeleted.add(productsList);
                completeDeleted.add(order);
                return completeDeleted;
            }).orElseThrow(() -> new NullPointerException("Order not found"));
        });
    }

    @Override
    public Try<List<Object>> findOrder(OrderId id) {
        return Try.of(() -> {
            Optional<OrderJpaEntity> currentOrder = orderRepository.findById(id.getValue());
            List<Object> completeOrder = new ArrayList<>();
            return currentOrder.map(jpaOrder -> {
                List<Product> productsList = getProducts(currentOrder.get().getOrderProducts());
                Order order = orderMapper.mapToDomainEntity(jpaOrder);
                completeOrder.add(productsList);
                completeOrder.add(order);
                return completeOrder;
            })
                    .orElseThrow(() -> new NullPointerException("Order not found"));
        });
    }

    public List<Product> getProducts(List<OrderProductsJpaEntity> orderProductsList){
        List<Product> productsList = new ArrayList<>();
        orderProductsList.forEach(orderProductsJpa -> {
                Optional<ProductJpaEntity> productJpaEntity = productRepository.findById(orderProductsJpa.getProductId());
                Product product = productJpaEntity.map(productMapper::mapToDomainEntity).orElseThrow(() -> new NullPointerException("Product not Found"));
                productsList.add(product);
        });
        return productsList;
    }

    @Override
    public Try<List<Object>> updateOrder(OrderId id, OrderStatus status) {
        return Try.of(() -> {
            Long orderId = id.getValue();
            Optional<OrderJpaEntity> currentOrder = orderRepository.findById(orderId);
            List<Object> completeUpdated = new ArrayList<>();
            return currentOrder.map(jpaOrder -> {
                /**/    List<Product> productsList = getProducts(currentOrder.get().getOrderProducts());
                        jpaOrder.setStatus(status.name());
                        OrderJpaEntity updatedOrder =  orderRepository.save(jpaOrder);
                        Order order = orderMapper.mapToDomainEntity(updatedOrder);
                        completeUpdated.add(productsList);
                        completeUpdated.add(order);
                    return completeUpdated;
            }).orElseThrow(() -> new NullPointerException("Order not found"));
        });
    }

    @Override
    public List<List<Object>> findAllOrders() {
        List<List<Object>> orders = new ArrayList<>();
        Iterable<OrderJpaEntity> jpaOrders = orderRepository.findAll();
        jpaOrders.forEach(jpaEntity -> {
            Order order = orderMapper.mapToDomainEntity(jpaEntity);
            List<Product> productsList = getProducts(jpaEntity.getOrderProducts());
            List<Object> response = new ArrayList<>();
            response.add(productsList);
            response.add(order);
            orders.add(response);
        });
        return orders;
    }
}
