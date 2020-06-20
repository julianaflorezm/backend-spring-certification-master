package co.com.ias.certification.backend.order.adapters.out.persistence.order;

import co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts.OrderProductsJpaEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String customer;

    @Column
    BigDecimal total;

    @Column
    BigDecimal discount;

    @Column
    String status;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    @Singular
    List<OrderProductsJpaEntity> orderProducts;
}
