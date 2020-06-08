package co.com.ias.certification.backend.product.adapters.out.storage;

import co.com.ias.certification.backend.product.application.domain.*;
import co.com.ias.certification.backend.product.application.domain.image.*;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageJpaEntity mapToJpaEntity(ProductImage image) {

        return ImageJpaEntity
                .builder()
                .productId(image.getProductId().getValue())
                .name(image.getName().getValue())
                .size(image.getSize().getValue())
                .contentType(image.getContentType().getValue())
                .extension(image.getExtension().getValue())
                .build();
    }

    public ProductImage mapToDomainEntity(ImageJpaEntity jpaEntity) {
        Preconditions.checkNotNull(jpaEntity);
        return ProductImage.builder()
                .productId(ProductId.of(jpaEntity.getProductId()))
                .name(ImageName.of(jpaEntity.getName()))
                .size(Size.of(jpaEntity.getSize()))
                .contentType(ContentType.of(jpaEntity.getContentType()))
                .extension(Extension.of(jpaEntity.getExtension()))
                .build();
    }
}
