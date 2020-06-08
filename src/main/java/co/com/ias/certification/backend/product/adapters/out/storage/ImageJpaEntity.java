package co.com.ias.certification.backend.product.adapters.out.storage;

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

    @Column
    Long productId;

    @Column
    String name;

    @Column
    Long size;

    @Column
    String contentType;

    @Column
    String extension;
}
