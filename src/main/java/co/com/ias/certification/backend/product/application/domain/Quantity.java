package co.com.ias.certification.backend.product.application.domain;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class Quantity implements NumberSerializable {
    public static Quantity fromNumber(Number number) {
        return new Quantity(number.intValue());
    }


    Integer value;

    private Quantity(Integer value) {
        Preconditions.checkNotNull(value, "quantity can not be null");
        Preconditions.checkArgument(value > 0, "quantity should be greater than 0");
        this.value = value;
    }

    @Override
    public Integer valueOf() {
        return value;
    }
}
