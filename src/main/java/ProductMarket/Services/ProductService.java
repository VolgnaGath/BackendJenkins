package ProductMarket.Services;

import ProductMarket.dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductService {

    @POST("api/v1/products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @DELETE("api/v1/products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @PUT("api/v1/products")
    Call<Product> modifyProduct(@Body Product modifyProductRequest);

    @GET("api/v1/products/{id}")
    Call<Product> getProductById(@Path("id") int id);

    @GET("api/v1/products")
    Call<ResponseBody> getProducts();

}
