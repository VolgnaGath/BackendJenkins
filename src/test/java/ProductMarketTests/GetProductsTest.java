package ProductMarketTests;

import ProductMarket.Services.ProductService;
import ProductMarket.dto.Product;
import ProductMarket.ultils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.checkerframework.checker.units.qual.A;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetProductsTest extends AbstractTestClass {


    @SneakyThrows
    @Test
    void getProductsTest() {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
