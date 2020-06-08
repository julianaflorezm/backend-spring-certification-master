package co.com.ias.certification.backend.product.application.domain;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductNotCreated {
    ProductName name;
    ProductDescription description;
    BasePrice basePrice;
    TaxRate taxRate;
    ProductStatus status;
    Quantity inventoryQuantity;

    public ProductNotCreated(ProductName name, ProductDescription description, BasePrice basePrice, TaxRate taxRate, ProductStatus status, Quantity inventoryQuantity) {
        this.name = Preconditions.checkNotNull(name);
        this.description = Preconditions.checkNotNull(description);
        this.basePrice = Preconditions.checkNotNull(basePrice);
        this.taxRate = Preconditions.checkNotNull(taxRate);
        this.status = Preconditions.checkNotNull(status);
        this.inventoryQuantity = Preconditions.checkNotNull(inventoryQuantity);
    }
}
