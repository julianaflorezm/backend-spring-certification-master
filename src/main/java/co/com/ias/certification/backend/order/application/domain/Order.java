package co.com.ias.certification.backend.order.application.domain;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {
    OrderId id;
    Customer customer;
    Total total;
    Discount discount;
    OrderStatus status;

    public Order(OrderId id, Customer customer, Total total, Discount discount, OrderStatus status){
        this.id = Preconditions.checkNotNull(id);
        this.customer = Preconditions.checkNotNull(customer);
        this.total = Preconditions.checkNotNull(total);
        this.discount = discount;
        this.status = Preconditions.checkNotNull(status);
    }
}
