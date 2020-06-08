package co.com.ias.certification.backend.product.application.domain.image;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class Size implements NumberSerializable {
    public static Size fromNumber(Number number) {
        return new Size(number.longValue());
    }

    Long value;

    private Size(Long value) {
        Preconditions.checkNotNull(value, "value can not be null");
        this.value = value;
    }

    @Override
    public Number valueOf() {
        return value;
    }
}
