package co.com.ias.certification.backend.order.application.domain.OrderProducts;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderProductsId implements NumberSerializable {
    public static OrderProductsId fromNumber(Number number) {
        return new OrderProductsId(number.longValue());
    }

    Long value;

    private OrderProductsId(Long value) {
        Preconditions.checkNotNull(value, "value can not be null");
        Preconditions.checkArgument(value >= 1, "value should be greater than 0");
        this.value = value;
    }

    @Override
    public Number valueOf() {
        return value;
    }
}
