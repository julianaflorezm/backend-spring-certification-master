package co.com.ias.certification.backend.product.application.domain;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Product {
    ProductId id;
    ProductName name;
    ProductDescription description;
    BasePrice basePrice;
    TaxRate taxRate;
    ProductStatus productStatus;
    Quantity inventoryQuantity;

    public Product(ProductId id, ProductName name, ProductDescription description, BasePrice basePrice, TaxRate taxRate, ProductStatus productStatus, Quantity inventoryQuantity) {
        this.id = Preconditions.checkNotNull(id);
        this.name = Preconditions.checkNotNull(name);
        this.description = Preconditions.checkNotNull(description);
        this.basePrice = Preconditions.checkNotNull(basePrice);
        this.taxRate = Preconditions.checkNotNull(taxRate);
        this.productStatus = Preconditions.checkNotNull(productStatus);
        this.inventoryQuantity = Preconditions.checkNotNull(inventoryQuantity);
    }
}
