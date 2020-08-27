package elit.express.elitexpress;


import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {

    @FormUrlEncoded
    @POST("/")
    Call<ArrayList<Status1>> sendStatus1(
            @Field("status") String status,
            @Field("app") String app,
            @Field("marshrut_reis") String marshrut_reis,
            @Field("data_reis") String data_reis
    );

    @FormUrlEncoded
    @POST("/")
    Call<String> sendStatus2(
            @Field("status") String status,
            @Field("app") String app,
            @Field("marshrut_reis") String marshrut_reis
    );

    @FormUrlEncoded
    @POST("/")
    Call<Map<String,String>[]> sendStatus3(
            @Field("status") String status,
            @Field("app") String app,
            @Field("marshrut_reis") String marshrut_reis,
            @Field("zone") String zone
    );

    @FormUrlEncoded
    @POST("/")
    Call<Map<String,String>[]> sendStatus4(
            @Field("status") String status,
            @Field("app") String app,
            @Field("marshrut_reis") String marshrut_reis,
            @Field("id_reis") String id_reis,
            @Field("date") String date,
            @Field("time") String time,
            @Field("fio") String fio,
            @Field("tel") String tel,
            @Field("mest") String mest,
            @Field("id_from") String id_from,
            @Field("id_to") String id_to,
            @Field("price") String price,
            @Field("info") String info

    );

    @FormUrlEncoded
    @POST("/")
    Call<ArrayList<Status5>> sendStatus5(
            @Field("status") String status,
            @Field("app") String app,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("/")
    Call<Map<String,String>[]> sendStatus6(
            @Field("status") String status,
            @Field("app") String app,
            @Field("orderid") String orderid,
            @Field("new_status") String new_status
    );

    @FormUrlEncoded
    @POST("/")
    Call<ArrayList<Status7>> sendStatus7(
            @Field("status") String status,
            @Field("app") String app,
            @Field("marshrut_reis") String marshrut_reis
    );

}

