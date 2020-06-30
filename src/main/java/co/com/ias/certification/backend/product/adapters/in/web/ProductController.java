package co.com.ias.certification.backend.product.adapters.in.web;

import co.com.ias.certification.backend.common.WebAdapter;
import co.com.ias.certification.backend.exceptions.UserUnauthorized;
import co.com.ias.certification.backend.order.application.domain.Customer;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.port.in.*;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@WebAdapter
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final FindProductUseCase findProductUseCase;
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final UploadProductImageUseCase uploadProductImageUseCase;


    @PostMapping()
    public ResponseEntity createProduct(@RequestBody CreateProductUseCase.CreateProductCommand command, Authentication authentication ) {
        Collection authorities = authentication.getAuthorities();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String customerName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Try<Boolean> hasPermission = createProductUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(createProductUseCase.createProduct(command));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Try.failure(UserUnauthorized.of(Customer.of(customerName))));
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody UpdateProductUseCase.UpdateProductCommand command, Authentication authentication){
        Collection authorities = authentication.getAuthorities();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String customerName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Try<Boolean> hasPermission = updateProductUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(updateProductUseCase.updateProduct(command));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Try.failure(UserUnauthorized.of(Customer.of(customerName))));
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestBody DeleteProductUseCase.DeleteProductCommand command, Authentication authentication){
        Collection authorities = authentication.getAuthorities();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String customerName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Try<Boolean> hasPermission = deleteProductUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(deleteProductUseCase.deleteProduct(command));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Try.failure(UserUnauthorized.of(Customer.of(customerName))));
    }

    @GetMapping()
    public ResponseEntity findProduct(@RequestBody FindProductUseCase.FindProductQuery query, Authentication authentication){
        Collection authorities = authentication.getAuthorities();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String customerName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Try<Boolean> hasPermission = findProductUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(findProductUseCase.findProduct(query));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Try.failure(UserUnauthorized.of(Customer.of(customerName))));
    }

    @GetMapping("/all")
    public ResponseEntity findAllProducts(Authentication authentication){
        Collection authorities = authentication.getAuthorities();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String customerName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Try<Boolean> hasPermission = findAllProductsUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(findAllProductsUseCase.findAllProducts());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Try.failure(UserUnauthorized.of(Customer.of(customerName))));
    }

    @PostMapping("/images")
    ResponseEntity uploadProductImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long productId, Authentication authentication) throws IOException {
        ProductId id = ProductId.of(productId);
        UploadProductImageUseCase.UploadProductImageCommand command = UploadProductImageUseCase.UploadProductImageCommand.of(
                id,
                file
        );
        Collection authorities = authentication.getAuthorities();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String customerName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Try<Boolean> hasPermission = uploadProductImageUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(uploadProductImageUseCase.uploadProductImage(command));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Try.failure(UserUnauthorized.of(Customer.of(customerName))));
    }


}
