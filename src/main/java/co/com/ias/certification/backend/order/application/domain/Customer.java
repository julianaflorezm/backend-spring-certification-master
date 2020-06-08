package co.com.ias.certification.backend.order.application.domain;

import co.com.ias.certification.backend.serialization.StringSerializable;
import com.google.common.base.Preconditions;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value(staticConstructor = "of")
public class Customer implements StringSerializable {
    String value;

    private Customer(String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNotBlank(value), "Customer can't be blank");
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
