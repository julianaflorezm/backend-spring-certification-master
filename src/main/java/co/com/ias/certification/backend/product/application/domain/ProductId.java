package co.com.ias.certification.backend.product.application.domain;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductId implements NumberSerializable {
    public static ProductId fromNumber(Number number) {
        return new ProductId(number.longValue());
    }

    Long value;

    private ProductId(Long value) {
        Preconditions.checkNotNull(value, "value can not be null");
        Preconditions.checkArgument(value >= 1, "value should be greater than 0");
        this.value = value;
    }

    @Override
    public Number valueOf() {
        return value;
    }
}
