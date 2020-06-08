package co.com.ias.certification.backend.order.adapters.out.persistence.orderProducts;


import org.springframework.data.repository.CrudRepository;

public interface OrderProductsRepository extends CrudRepository<OrderProductsJpaEntity, Long> {
}
