package co.com.ias.certification.backend.order.adapters.out.persistence.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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
}
