package co.com.ias.certification.backend.order.application.domain;


import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Total implements NumberSerializable {
    public static Total fromNumber(Number number) {
        return new Total(BigDecimal.valueOf(number.doubleValue()));
    }

    BigDecimal value;

    private Total(BigDecimal value) {
        Preconditions.checkNotNull(value, "Total can not be null");
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0, "Total can not be negative");
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
