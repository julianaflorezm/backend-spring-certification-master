package co.com.ias.certification.backend.product.adapters.out.storage;

import co.com.ias.certification.backend.product.adapters.out.persistence.ProductJpaEntity;
import co.com.ias.certification.backend.product.adapters.out.persistence.ProductMapper;
import co.com.ias.certification.backend.product.adapters.out.persistence.ProductRepository;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.domain.image.*;
import co.com.ias.certification.backend.product.application.port.out.UploadProductImagePort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductImages implements UploadProductImagePort {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public Try<Object> storeImage(ProductId id, MultipartFile file) {
        Path root = Paths.get("src\\main\\java\\co\\com\\ias\\certification\\backend\\uploads\\");
        Long productId = id.getValue();
        Optional<ProductJpaEntity> jpaProduct = productRepository.findById(productId);
        return Try.of(() -> {
            if(jpaProduct.isPresent()){
                if(!file.getOriginalFilename().isEmpty()){
                    Path completeRoot = root.resolve(file.getOriginalFilename());
                    if(!Files.exists(completeRoot)){
                        Files.copy(file.getInputStream(), completeRoot);
                        ProductImage image = new ProductImage(id, ImageName.of(file.getOriginalFilename()), Size.of(file.getSize()), ContentType.of(file.getContentType()), Extension.of(FilenameUtils.getExtension(file.getOriginalFilename())));
                        ImageJpaEntity jpaImage = imageMapper.mapToJpaEntity(image, jpaProduct.get());
                        imageRepository.save(jpaImage);
                        return "Success process: " + file.getOriginalFilename() + " is uploaded";
                    }else{
                        return "File already exists";
                    }
                }else{
                    return "File is required";
                }
            }else{
                return jpaProduct.map(productMapper::mapToDomainEntity).orElseThrow(() -> new NullPointerException("Product not found"));
            }
        });
    }
}
