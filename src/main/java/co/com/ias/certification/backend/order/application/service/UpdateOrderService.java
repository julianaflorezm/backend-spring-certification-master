package co.com.ias.certification.backend.order.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.order.application.port.in.UpdateOrderUseCase;
import co.com.ias.certification.backend.order.application.port.out.UpdateOrderPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class UpdateOrderService implements UpdateOrderUseCase {
    private final UpdateOrderPort updateOrderPort;
    @Override
    public Try<List<Object>> updateOrder(UpdateOrderCommand command) {
        return updateOrderPort.updateOrder(command.getId(), command.getStatus());
    }
}
