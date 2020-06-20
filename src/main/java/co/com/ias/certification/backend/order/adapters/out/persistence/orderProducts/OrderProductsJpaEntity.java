package co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts;


import co.com.ias.certification.backend.order.adapters.out.persistence.order.OrderJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ORDERPRODUCTS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductsJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    OrderJpaEntity order;

    @Column
    Long productId;
}
