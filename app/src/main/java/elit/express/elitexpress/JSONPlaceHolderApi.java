package elit.express.elitexpress;


import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @FormUrlEncoded
    @POST("/")
    Call<ArrayList<Status1>> getStatus1(
            @Field("status") String status,
            @Field("app") String app,
            @Field("marshrut_reis") String marshrut_reis,
            @Field("data_reis") String data_reis
    );
    //    @POST("elit.php")

//    @FormUrlEncoded
//    @POST("/")
//    Call<Status1> getStatus1(@Body Request1 request1);

//    @POST("/history")
//    Call<Rates7Days> getPost(
//            @Query("start_at") String start_at,
//            @Query("end_at") String end_at,
//            @Query("base") String base,
//            @Query("symbols") String symbols
//    );
}

//https://sumy.kiev.ua/status=1&app=android&marshrut_reis=1&data_reis=2020-08-26
