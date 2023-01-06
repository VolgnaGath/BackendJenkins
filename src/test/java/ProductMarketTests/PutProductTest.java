package ProductMarketTests;

import ProductMarket.Services.ProductService;
import ProductMarket.dto.Product;
import ProductMarket.ultils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PutProductTest extends AbstractTestClass {


    @SneakyThrows
    @Test
    void putProductTest() {
        Response<Product> findId = productService.createProduct(product).execute();
        id = findId.body().getId();
        product.setTitle(title);
        Response<Product> response = productService.modifyProduct(product.withId(id)).execute();
        assertThat(response.body().getTitle(), equalTo(title));
        example.createCriteria().andTitleEqualTo(title);
        List<db.model.Products> list = productsMapper.selectByExample(example);
        assert response.body() != null;
        assertThat(response.body().getTitle(), equalTo(list.get(0).getTitle()));
    }
}
