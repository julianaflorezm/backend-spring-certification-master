package co.com.ias.certification.backend.product.application.domain;

import co.com.ias.certification.backend.serialization.StringSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value(staticConstructor = "of")
public class ProductDescription implements StringSerializable {
    String value;

    private ProductDescription(String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNotBlank(value), "Product description can't be blank");
        Preconditions.checkArgument(StringUtils.length(value) <= 280, "Product description can not be longer than 280");
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
