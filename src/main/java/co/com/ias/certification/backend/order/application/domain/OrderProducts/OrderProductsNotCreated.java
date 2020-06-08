package co.com.ias.certification.backend.order.application.domain.OrderProducts;

import co.com.ias.certification.backend.order.application.domain.OrderId;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderProductsNotCreated {
    OrderId orderId;
    ProductId productId;

    public OrderProductsNotCreated(OrderId orderId, ProductId productId){
        this.orderId = Preconditions.checkNotNull(orderId);
        this.productId = Preconditions.checkNotNull(productId);
    }
}
