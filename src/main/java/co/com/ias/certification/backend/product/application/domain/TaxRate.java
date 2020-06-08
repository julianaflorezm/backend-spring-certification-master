package co.com.ias.certification.backend.product.application.domain;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class TaxRate implements NumberSerializable {
    public static TaxRate fromNumber(Number number) {
        return new TaxRate(BigDecimal.valueOf(number.doubleValue()));
    }


    BigDecimal value;

    private TaxRate(BigDecimal value) {
        Preconditions.checkNotNull(value, "Tax rate  can not be null");
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0, "Tax rate can not be negative");
        Preconditions.checkArgument(value.compareTo(BigDecimal.ONE) <= 0, "Tax rate can not be more than 1");
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
