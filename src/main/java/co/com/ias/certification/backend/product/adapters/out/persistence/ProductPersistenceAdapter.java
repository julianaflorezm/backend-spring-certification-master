package co.com.ias.certification.backend.product.adapters.out.persistence;

import co.com.ias.certification.backend.common.PersistenceAdapter;
import co.com.ias.certification.backend.product.adapters.out.storage.ImageJpaEntity;
import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.domain.ProductNotCreated;
import co.com.ias.certification.backend.product.application.port.out.*;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class ProductPersistenceAdapter implements CreateProductPort, UpdateProductPort, DeleteProductPort, FindProductPort, FindAllProductsPort
{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Try<Product> createProduct(ProductNotCreated product) {
        return Try.of(() -> {
            ProductJpaEntity productJpaEntity = productMapper.mapToJpaEntity(product);
            ProductJpaEntity createdProduct = productRepository.save(productJpaEntity);
            return productMapper.mapToDomainEntity(createdProduct);
        });
    }


    @Override
    public Try<Product> updateProduct(ProductId productId, ProductNotCreated product) {
        return Try.of(() -> {
            Long id = productId.getValue();
            Optional<ProductJpaEntity> currentProductOptional = productRepository.findById(id);
            ProductJpaEntity updateEntity = productMapper.mapToJpaEntity(product);
            return currentProductOptional
                    .map(productJpaEntity -> {
                        productJpaEntity.setName(updateEntity.getName());
                        productJpaEntity.setDescription(updateEntity.getDescription());
                        productJpaEntity.setBasePrice(updateEntity.getBasePrice());
                        productJpaEntity.setTaxRate(updateEntity.getTaxRate());
                        productJpaEntity.setStatus(updateEntity.getStatus());
                        productJpaEntity.setInventoryQuantity(updateEntity.getInventoryQuantity());
                        ProductJpaEntity updatedProduct = productRepository.save(productJpaEntity);
                        return productMapper.mapToDomainEntity(updatedProduct);
                    })
                    .orElseThrow(() -> new NullPointerException("Product not found"));
        });
    }

    @Override
    public Try<Product> deleteProduct(ProductId id) {
        return Try.of(() -> {
            Long productId = id.getValue();
            Optional<ProductJpaEntity> currentProduct = productRepository.findById(productId);
            return currentProduct.map(product -> {
                productRepository.deleteById(productId);
                return productMapper.mapToDomainEntity(product);
            }).orElseThrow(() -> new NullPointerException("Product not found"));
        });
    }

    @Override
    public Try<List<Object>> findProduct(ProductId id) {
        return Try.of(() -> {
            Long productId = id.getValue();
            Optional<ProductJpaEntity> currentProduct = productRepository.findById(productId);
            Product product = currentProduct.map(productMapper::mapToDomainEntity)
                    .orElseThrow(() -> new NullPointerException("Product not found"));
            List<Resource> images = getImage(currentProduct.get().getImages());
            List<Object> response = new ArrayList<>();
            response.add(images);
            response.add(product);
            return response;
        });
    }



    public List<Resource> getImage(List<ImageJpaEntity> list) {
        List<Resource> images = new ArrayList<>();
        list.forEach(jpaImage -> {
                Path path = Paths.get("src\\main\\java\\co\\com\\ias\\certification\\backend\\uploads\\").resolve(jpaImage.getName()).toAbsolutePath();
                try {
                    Resource resource = new UrlResource(path.toUri());
                    if(resource.exists()){
                        images.add(resource);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        });
        return images;
    }

    @Override
    public List<Object> findAllProducts() {
        List<Object> products = new ArrayList<>();
        Iterable<ProductJpaEntity> list = productRepository.findAll();
        list.forEach(jpaProduct -> {
            Product product = productMapper.mapToDomainEntity(jpaProduct);
            List<Resource> images = getImage(jpaProduct.getImages());
            List<Object> response = new ArrayList<>();
            response.add(images);
            response.add(product);
            products.add(response);
        });
        return products;
    }


}
