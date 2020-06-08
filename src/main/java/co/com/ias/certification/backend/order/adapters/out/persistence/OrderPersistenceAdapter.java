package co.com.ias.certification.backend.order.adapters.out.persistence;

import co.com.ias.certification.backend.common.PersistenceAdapter;
import co.com.ias.certification.backend.order.adapters.out.persistence.order.OrderJpaEntity;
import co.com.ias.certification.backend.order.adapters.out.persistence.order.OrderMapper;
import co.com.ias.certification.backend.order.adapters.out.persistence.order.OrderRepository;
import co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts.OrderProductsJpaEntity;
import co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts.OrderProductsMapper;
import co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts.OrderProductsRepository;
import co.com.ias.certification.backend.order.application.domain.Order;
import co.com.ias.certification.backend.order.application.domain.OrderId;
import co.com.ias.certification.backend.order.application.domain.OrderNotCreated;
import co.com.ias.certification.backend.order.application.domain.OrderProducts.OrderProductsNotCreated;
import co.com.ias.certification.backend.order.application.domain.OrderStatus;
import co.com.ias.certification.backend.order.application.port.out.*;
import co.com.ias.certification.backend.product.adapters.out.persistence.ProductJpaEntity;
import co.com.ias.certification.backend.product.adapters.out.persistence.ProductMapper;
import co.com.ias.certification.backend.product.adapters.out.persistence.ProductRepository;
import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

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
    public Try<List<Object>> createOrder(List<ProductId> products, OrderNotCreated order) {
        return Try.of(() -> {

            List<Object> completeOrder = new ArrayList<>();

            OrderJpaEntity orderJpaEntity = orderMapper.mapToJpaEntity(order);
            OrderJpaEntity orderCreatedJpa = orderRepository.save(orderJpaEntity);
            Order orderCreated = orderMapper.mapToDomainEntity(orderCreatedJpa);

            List<Product> productsList = new ArrayList<>();
            for (ProductId productId:
                 products) {
                Optional<ProductJpaEntity> currentProduct = productRepository.findById(productId.getValue());
                Product product = productMapper.mapToDomainEntity(currentProduct.get());
                OrderProductsNotCreated orderProducts = new OrderProductsNotCreated(orderCreated.getId(), productId);
                OrderProductsJpaEntity orderProductsJpaEntity = orderProductsMapper.mapToJpaEntity(orderProducts);
                orderProductsRepository.save(orderProductsJpaEntity);
                productsList.add(product);
            }
            completeOrder.add(productsList);
            completeOrder.add(orderCreated);
            return completeOrder;
        });
    }

    @Override
    public Try<List<Object>> deleteOrder(ProductId id) {
        return Try.of(() -> {
            Long orderId = id.getValue();
            Optional<OrderJpaEntity> currentOrder = orderRepository.findById(orderId);
            List<Object> completeDeleted = new ArrayList<>();
            return currentOrder.map(jpaOrder -> {
                Iterable<OrderProductsJpaEntity> orderProductsList = orderProductsRepository.findAll();
                List<Product> productsList = new ArrayList<>();
                orderProductsList.forEach(orderProductsJpa -> {
                    if(orderProductsJpa.getIdOrder() == currentOrder.get().getId()){
                        orderProductsRepository.deleteById(orderProductsJpa.getId());
                        Optional<ProductJpaEntity> productJpaEntity = productRepository.findById(orderProductsJpa.getIdProduct());
                        Product product = productMapper.mapToDomainEntity(productJpaEntity.get());
                        productsList.add(product);
                    }
                });
                orderRepository.deleteById(orderId);
                Order order = orderMapper.mapToDomainEntity(jpaOrder);
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
                Iterable<OrderProductsJpaEntity> orderProductsList = orderProductsRepository.findAll();
                List<Product> productsList = new ArrayList<>();
                orderProductsList.forEach(orderProductsJpa -> {
                    if(orderProductsJpa.getIdOrder() == currentOrder.get().getId()){
                        Optional<ProductJpaEntity> productJpaEntity = productRepository.findById(orderProductsJpa.getIdProduct());
                        Product product = productMapper.mapToDomainEntity(productJpaEntity.get());
                        productsList.add(product);
                    }
                });
                Order order = orderMapper.mapToDomainEntity(jpaOrder);
                completeOrder.add(productsList);
                completeOrder.add(order);
                return completeOrder;
            })
                    .orElseThrow(() -> new NullPointerException("Order not found"));
        });
    }

    @Override
    public Try<List<Object>> updateOrder(OrderId id, OrderStatus status) {
        return Try.of(() -> {
            Long orderId = id.getValue();
            Optional<OrderJpaEntity> currentOrder = orderRepository.findById(orderId);
            List<Object> completeUpdated = new ArrayList<>();
            return currentOrder.map(jpaOrder -> {
                        Iterable<OrderProductsJpaEntity> orderProductsList = orderProductsRepository.findAll();
                        List<Product> productsList = new ArrayList<>();
                        orderProductsList.forEach(orderProductsJpa -> {
                            if(orderProductsJpa.getIdOrder() == currentOrder.get().getId()){
                                Optional<ProductJpaEntity> productJpaEntity = productRepository.findById(orderProductsJpa.getIdProduct());
                                Product product = productMapper.mapToDomainEntity(productJpaEntity.get());
                                productsList.add(product);
                            }
                        });

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

            Iterable<OrderProductsJpaEntity> orderProductsList = orderProductsRepository.findAll();
            List<Product> productsList = new ArrayList<>();
            orderProductsList.forEach(orderProductsJpa -> {
                if(orderProductsJpa.getIdOrder() == jpaEntity.getId()){
                    Optional<ProductJpaEntity> productJpaEntity = productRepository.findById(orderProductsJpa.getIdProduct());
                    Product product = productMapper.mapToDomainEntity(productJpaEntity.get());
                    productsList.add(product);

                }
            });

            List<Object> response = new ArrayList<>();
            response.add(productsList);
            response.add(order);
            orders.add(response);
        });
        return orders;
    }
}
