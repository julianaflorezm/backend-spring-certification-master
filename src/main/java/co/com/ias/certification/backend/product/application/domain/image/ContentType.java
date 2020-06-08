package co.com.ias.certification.backend.product.application.domain.image;

import co.com.ias.certification.backend.serialization.StringSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value(staticConstructor = "of")
public class ContentType implements StringSerializable {
    String value;

    private ContentType(String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNotBlank(value), "Content type can't be blank");
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
