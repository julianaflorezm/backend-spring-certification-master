package co.com.ias.certification.backend.order.application.domain;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Discount implements NumberSerializable {
    public static Discount fromNumber(Number number) {
        return new Discount(BigDecimal.valueOf(number.doubleValue()));
    }

    BigDecimal value;

    private Discount(BigDecimal value) {
        Preconditions.checkArgument(value.doubleValue() >= 0, "Discount can not be less than 0");
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
