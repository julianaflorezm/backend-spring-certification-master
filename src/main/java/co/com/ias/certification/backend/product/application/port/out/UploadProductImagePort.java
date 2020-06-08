package co.com.ias.certification.backend.product.application.port.out;

import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import org.springframework.web.multipart.MultipartFile;

public interface UploadProductImagePort {
    Try<Object> storeImage(ProductId id, MultipartFile file);
}
