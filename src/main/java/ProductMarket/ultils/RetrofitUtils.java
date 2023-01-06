package ProductMarket.ultils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import okhttp3.logging.HttpLoggingInterceptor;


import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

    @UtilityClass
    public class RetrofitUtils {

        Properties prop = new Properties();
        private static InputStream configFile;

        static {
            try {
                configFile = new FileInputStream("src/main/resources/my.properties.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @SneakyThrows
        public String getUrl() {
            prop.load(configFile);
            return prop.getProperty("url");
        }


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        LoggingInterceptor logging2 = new LoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();



        public Retrofit getRetrofit(){
            logging.setLevel(BODY);
            httpClient.addInterceptor(logging2);
            return new Retrofit.Builder()
                    .baseUrl(getUrl())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

    }

