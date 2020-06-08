package co.com.ias.certification.backend.order.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.order.application.port.in.CreateOrderUseCase;
import co.com.ias.certification.backend.order.application.port.out.CreateOrderPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final CreateOrderPort createOrderPort;

    @Override
    public Try<List<Object>> createOrder(CreateOrderCommand command) {
        return createOrderPort.createOrder(command.getProducts(), command.getOrder());
    }
}
