package elit.express.elit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkService {
    private static final String BASE_URL = "https://m.sumy.kiev.ua";
    private static final String TEST_URL = "https://test.sumy.kiev.ua";
    //    private static final String BASE_URL = "http://192.168.0.101";
    private static NetworkService mInstance;
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.DEBUG ? TEST_URL : BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }

}
