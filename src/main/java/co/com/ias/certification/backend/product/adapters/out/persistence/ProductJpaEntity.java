package co.com.ias.certification.backend.product.adapters.out.persistence;

import co.com.ias.certification.backend.product.adapters.out.storage.ImageJpaEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String name;

    @Column
    String description;

    @Column
    BigDecimal basePrice;

    @Column
    BigDecimal taxRate;

    @Column
    String status;

    @Column
    Integer inventoryQuantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    @Singular
    List<ImageJpaEntity> images;
}
