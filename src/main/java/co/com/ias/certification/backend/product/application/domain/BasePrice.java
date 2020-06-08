package co.com.ias.certification.backend.product.application.domain;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class BasePrice implements NumberSerializable {
    public static BasePrice fromNumber(Number number) {
        return new BasePrice(BigDecimal.valueOf(number.doubleValue()));
    }

    BigDecimal value;

    private BasePrice(BigDecimal value) {
        Preconditions.checkNotNull(value, "Base price can not be null");
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0, "Base price can not be negative");
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
