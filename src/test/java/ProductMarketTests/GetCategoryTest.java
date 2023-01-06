package ProductMarketTests;

import ProductMarket.Services.CategoryService;
import ProductMarket.dto.GetCategoryResponse;
import ProductMarket.ultils.RetrofitUtils;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class GetCategoryTest {

    static CategoryService categoryService;
    static SqlSession session = null;
    static db.dao.CategoriesMapper categoriesMapper;
    static db.model.CategoriesExample example;
    @BeforeAll
    static void beforeAll() throws IOException {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        categoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
        example = new db.model.CategoriesExample();
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Food")));
        example.createCriteria().andIdEqualTo(1l);
        List<db.model.Categories> list = categoriesMapper.selectByExample(example);
        assertThat(response.body().getTitle(), equalTo(list.get(0).getTitle()));


    }
    @Test
    void getCategoryByIdNegativeTest() throws IOException {
       Response response = categoryService.getCategory(10).execute();
       assertThat(response.code(), equalTo(404));
    }

}

