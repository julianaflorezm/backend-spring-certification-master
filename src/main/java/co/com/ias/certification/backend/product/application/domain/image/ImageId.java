package co.com.ias.certification.backend.product.application.domain.image;

import co.com.ias.certification.backend.serialization.NumberSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class ImageId implements NumberSerializable {
    public static ImageId fromNumber(Number number) {
        return new ImageId(number.longValue());
    }

    Long value;

    private ImageId(Long value) {
        Preconditions.checkNotNull(value, "value can not be null");
        Preconditions.checkArgument(value >= 1, "value should be greater than 0");
        this.value = value;
    }

    @Override
    public Number valueOf() {
        return value;
    }
}
