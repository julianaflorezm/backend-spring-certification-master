package co.com.ias.certification.backend.product.application.domain;

import co.com.ias.certification.backend.serialization.StringSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value(staticConstructor = "of")
public class ProductName implements StringSerializable {
    String value;

    private ProductName(String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNotBlank(value), "Product name can't be blank");
        Preconditions.checkArgument(StringUtils.length(value) <= 100, "Product name can not be longer than 100");
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
