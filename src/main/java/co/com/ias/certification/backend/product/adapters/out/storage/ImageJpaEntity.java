package co.com.ias.certification.backend.product.adapters.out.storage;

import co.com.ias.certification.backend.product.adapters.out.persistence.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "IMAGES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    ProductJpaEntity product;

    @Column
    String name;

    @Column
    Long size;

    @Column
    String contentType;

    @Column
    String extension;
}
