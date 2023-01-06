package ProductMarketTests;

import ProductMarket.Services.ProductService;
import ProductMarket.dto.Product;
import ProductMarket.ultils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostProductTest extends AbstractTestClass {

    @SneakyThrows
    @Test
    void postProductTest() {
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        example.createCriteria().andTitleEqualTo(product.getTitle());
        List<db.model.Products> list = productsMapper.selectByExample(example);
        assert response.body() != null;
        assertThat(response.body().getTitle(), equalTo(list.get(0).getTitle()));

    }
}
