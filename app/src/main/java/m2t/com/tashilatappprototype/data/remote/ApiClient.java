package m2t.com.tashilatappprototype.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import m2t.com.tashilatappprototype.common.pojo.Categories;
import m2t.com.tashilatappprototype.common.utils.CustomJsonDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by laanaya on 10/4/17.
 */

public class ApiClient {

    public static final String BASE_URL = "http://192.168.2.16:14005/";
    private static Retrofit retrofit = null;

    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Categories.class, new CustomJsonDeserializer())
            .setLenient()
            .create();



    public static Retrofit getClient() {
        if (retrofit==null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                                                  .addInterceptor(interceptor)
                                                  //.connectTimeout(10, TimeUnit.SECONDS)
                                                  //.writeTimeout(10, TimeUnit.SECONDS)
                                                  //.readTimeout(30, TimeUnit.SECONDS)
                                                  .build();

            retrofit = new Retrofit.Builder()
                                   .baseUrl(BASE_URL)
                                   .client(client)
                                   .addConverterFactory(GsonConverterFactory.create(gson))
                                   .build();
        }

        return retrofit;
    }
}
