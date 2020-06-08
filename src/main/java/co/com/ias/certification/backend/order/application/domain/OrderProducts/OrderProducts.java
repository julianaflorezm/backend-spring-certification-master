package co.com.ias.certification.backend.order.application.domain.OrderProducts;

import co.com.ias.certification.backend.order.application.domain.OrderId;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderProducts {
    OrderProductsId id;
    OrderId orderId;
    ProductId productId;

    public OrderProducts(OrderProductsId id, OrderId orderId, ProductId productId){
        this.id = Preconditions.checkNotNull(id);
        this.orderId = Preconditions.checkNotNull(orderId);
        this.productId = Preconditions.checkNotNull(productId);
    }
}
