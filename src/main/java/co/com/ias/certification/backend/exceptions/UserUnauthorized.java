package co.com.ias.certification.backend.exceptions;

import co.com.ias.certification.backend.order.application.domain.Customer;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value(staticConstructor = "of")
public class UserUnauthorized extends UserException {
    Customer customer;

    private UserUnauthorized(Customer customer) {
        super(String.format("error: %s is unauthorized to do this action", customer.getValue()));
        this.customer = customer;
    }
}
