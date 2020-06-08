package co.com.ias.certification.backend.product.adapters.out.persistence;

import co.com.ias.certification.backend.product.application.domain.*;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapToDomainEntity(ProductJpaEntity jpaEntity) {
        Preconditions.checkNotNull(jpaEntity);
        return Product.builder()
                .id(ProductId.of(jpaEntity.getId()))
                .name(ProductName.of(jpaEntity.getName()))
                .description(ProductDescription.of(jpaEntity.getDescription()))
                .basePrice(BasePrice.of(jpaEntity.getBasePrice()))
                .taxRate(TaxRate.of(jpaEntity.getTaxRate()))
                .productStatus(ProductStatus.valueOf(jpaEntity.getStatus()))
                .inventoryQuantity(Quantity.of(jpaEntity.getInventoryQuantity()))
                .build();
    }

    public ProductJpaEntity mapToJpaEntity(ProductNotCreated product) {
        Preconditions.checkNotNull(product);
        return ProductJpaEntity
                .builder()
                .name(product.getName().getValue())
                .description(product.getDescription().getValue())
                .basePrice(product.getBasePrice().getValue())
                .taxRate(product.getTaxRate().getValue())
                .status(product.getStatus().toString())
                .inventoryQuantity(product.getInventoryQuantity().getValue())
                .build();
    }

    /*
    public ProductIndexEntity mapToIndexEntity(Product product) {
        return ProductIndexEntity.of(
                product.getName().getValue(),
                product.getDescription().getValue()
        );
    }*/
}
