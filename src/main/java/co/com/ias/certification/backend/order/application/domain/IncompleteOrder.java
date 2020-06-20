package co.com.ias.certification.backend.order.application.domain;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class IncompleteOrder {
    Total total;
    OrderStatus status;
    
    public IncompleteOrder(Total total, OrderStatus status){
        this.total = Preconditions.checkNotNull(total);
        this.status = Preconditions.checkNotNull(status);
    }
}
