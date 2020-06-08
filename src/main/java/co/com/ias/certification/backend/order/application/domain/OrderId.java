package co.com.ias.certification.backend.order.application.domain;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderId implements NumberSerializable {
    public static OrderId fromNumber(Number number) {
        return new OrderId(number.longValue());
    }

    Long value;

    private OrderId(Long value) {
        Preconditions.checkNotNull(value, "value can not be null");
        Preconditions.checkArgument(value >= 1, "value should be greater than 0");
        this.value = value;
    }

    @Override
    public Number valueOf() {
        return value;
    }
}
