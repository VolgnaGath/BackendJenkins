package ProductMarketTests;
import com.github.javafaker.Faker;
import ProductMarket.Services.CategoryService;
import ProductMarket.Services.ProductService;
import ProductMarket.dto.GetCategoryResponse;
import ProductMarket.dto.Product;
import ProductMarket.ultils.RetrofitUtils;
import lombok.SneakyThrows;

import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductTests extends AbstractTestClass {



    @SneakyThrows
    @Test

    void deleteProductByIdPositiveTest() {
        Response<Product> findId = productService.createProduct(product).execute();
        id = findId.body().getId();
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        long dbId = id;
        example.createCriteria().andIdEqualTo(dbId);
        List<db.model.Products> list = productsMapper.selectByExample(example);
        assertThat(list.isEmpty(), Matchers.is(true));
    }
}
