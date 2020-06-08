package co.com.ias.certification.backend.configuration;


import co.com.ias.certification.backend.order.application.domain.Customer;
import co.com.ias.certification.backend.order.application.domain.Discount;
import co.com.ias.certification.backend.order.application.domain.OrderId;
import co.com.ias.certification.backend.order.application.domain.OrderProducts.OrderProductsId;
import co.com.ias.certification.backend.order.application.domain.Total;
import co.com.ias.certification.backend.product.application.domain.*;
import co.com.ias.certification.backend.serialization.NumberValueAdapter;
import co.com.ias.certification.backend.serialization.StringValueAdapter;
import co.com.ias.certification.backend.serialization.TryAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.vavr.control.Try;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfiguration {

    @Bean
    public Gson gson() {

        return new GsonBuilder()
                .registerTypeAdapter(BasePrice.class, new NumberValueAdapter<>(BasePrice::fromNumber))
                .registerTypeAdapter(ProductId.class, new NumberValueAdapter<>(ProductId::fromNumber))
                .registerTypeAdapter(TaxRate.class, new NumberValueAdapter<>(TaxRate::fromNumber))
                .registerTypeAdapter(Quantity.class, new NumberValueAdapter<>(Quantity::fromNumber))
                .registerTypeAdapter(ProductName.class, new StringValueAdapter<>(ProductName::of))
                .registerTypeAdapter(ProductDescription.class, new StringValueAdapter<>(ProductDescription::of))
                .registerTypeAdapter(Try.class, new TryAdapter<>())
                .registerTypeAdapter(Customer.class, new StringValueAdapter<>(Customer::of))
                .registerTypeAdapter(Discount.class, new NumberValueAdapter<>(Discount::fromNumber))
                .registerTypeAdapter(OrderId.class, new NumberValueAdapter<>(OrderId::fromNumber))
                .registerTypeAdapter(Total.class, new NumberValueAdapter<>(Total::fromNumber))
                .registerTypeAdapter(OrderProductsId.class, new NumberValueAdapter<>(OrderProductsId::fromNumber))
                .create();
    }
}
