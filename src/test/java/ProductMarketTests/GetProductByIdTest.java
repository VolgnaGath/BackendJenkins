package ProductMarketTests;

import ProductMarket.Services.ProductService;
import ProductMarket.dto.Product;
import ProductMarket.ultils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductByIdTest extends AbstractTestClass {


    @SneakyThrows
    @Test
    void getProductsTest() {
        Response<Product> findId = productService.createProduct(product).execute();
        id = findId.body().getId();
        Response<Product> response = productService.getProductById(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        long dbId = id;
        example.createCriteria().andIdEqualTo(dbId);
        List<db.model.Products> list = productsMapper.selectByExample(example);
        int newId = Math.toIntExact(list.get(0).getId());
        assert response.body() != null;
        assertThat(response.body().getId(), equalTo(newId));
    }
}

