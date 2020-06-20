package co.com.ias.certification.backend.product.application.port.in;

import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface UploadProductImageUseCase {
    Try<Object> uploadProductImage(UploadProductImageCommand command);

    Try<Boolean> userHasPermission(Collection authorities);

    @Value(staticConstructor = "of")
    class UploadProductImageCommand{
        ProductId id;
        MultipartFile file;
    }
}
