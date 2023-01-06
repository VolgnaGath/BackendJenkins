package ProductMarket.Services;


import ProductMarket.dto.GetCategoryResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface CategoryService {

    @GET("api/v1/categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
}
