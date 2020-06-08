package co.com.ias.certification.backend.product.application.domain.image;

import co.com.ias.certification.backend.product.application.domain.*;
import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value(staticConstructor = "from")
@Builder
public class ProductImage {
    ProductId productId;
    ImageName name;
    Size size;
    ContentType contentType;
    Extension extension;

    public ProductImage(ProductId productId, ImageName name, Size size, ContentType contentType, Extension extension) {
        this.productId = Preconditions.checkNotNull(productId);
        this.name = Preconditions.checkNotNull(name);
        this.size = Preconditions.checkNotNull(size);
        this.contentType = Preconditions.checkNotNull(contentType);
        this.extension = Preconditions.checkNotNull(extension);
    }
}
