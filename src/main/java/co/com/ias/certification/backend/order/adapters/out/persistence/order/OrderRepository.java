package co.com.ias.certification.backend.order.adapters.out.persistence.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderJpaEntity, Long> {
}
