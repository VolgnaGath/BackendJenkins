package ProductMarketTests;

import ProductMarket.Services.ProductService;
import ProductMarket.dto.Product;
import ProductMarket.ultils.RetrofitUtils;
import com.github.javafaker.Faker;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractTestClass {
    Faker faker = new Faker();
    Product product;
    String title = faker.food().ingredient();
    static SqlSession session = null;
    static db.dao.ProductsMapper productsMapper;
    static db.model.ProductsExample example;

    int id;
    static ProductService productService;
    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        example = new db.model.ProductsExample();
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withPrice(100)
                .withCategoryTitle("Food");}
}
