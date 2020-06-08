package co.com.ias.certification.backend.order.adapters.out.persistence.order;

import co.com.ias.certification.backend.order.application.domain.*;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order mapToDomainEntity(OrderJpaEntity jpaEntity) {
        Preconditions.checkNotNull(jpaEntity);
        return Order.builder()
                .id(OrderId.of(jpaEntity.getId()))
                .customer(Customer.of(jpaEntity.getCustomer()))
                .total(Total.of(jpaEntity.getTotal()))
                .discount(Discount.of(jpaEntity.getDiscount()))
                .status(OrderStatus.valueOf(jpaEntity.getStatus()))
                .build();
    }

    public OrderJpaEntity mapToJpaEntity(OrderNotCreated order) {
        Preconditions.checkNotNull(order);
        return OrderJpaEntity
                .builder()
                .customer(order.getCustomer().getValue())
                .total(order.getTotal().getValue())
                .discount(order.getDiscount().getValue())
                .status(order.getStatus().toString())
                .build();
    }
}
