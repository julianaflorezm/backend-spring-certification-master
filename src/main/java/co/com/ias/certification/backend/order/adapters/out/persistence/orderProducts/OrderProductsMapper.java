package co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts;

import co.com.ias.certification.backend.order.application.domain.OrderId;
import co.com.ias.certification.backend.order.application.domain.OrderProducts.OrderProducts;
import co.com.ias.certification.backend.order.application.domain.OrderProducts.OrderProductsId;
import co.com.ias.certification.backend.order.application.domain.OrderProducts.OrderProductsNotCreated;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

@Component
public class OrderProductsMapper {

    public OrderProducts mapToDomainEntity(OrderProductsJpaEntity jpaEntity) {
        Preconditions.checkNotNull(jpaEntity);
        return OrderProducts.builder()
                .id(OrderProductsId.of(jpaEntity.getId()))
                .orderId(OrderId.of(jpaEntity.getIdOrder()))
                .productId(ProductId.of(jpaEntity.getIdProduct()))
                .build();
    }


    public OrderProductsJpaEntity mapToJpaEntity(OrderProductsNotCreated orderProducts) {
        Preconditions.checkNotNull(orderProducts);
        return OrderProductsJpaEntity
                .builder()
                .idOrder(orderProducts.getOrderId().getValue())
                .idProduct(orderProducts.getProductId().getValue())
                .build();
    }
}
