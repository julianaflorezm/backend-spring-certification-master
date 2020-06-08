package co.com.ias.certification.backend.product.adapters.out.persistence;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductJpaEntity, Long> {

}
