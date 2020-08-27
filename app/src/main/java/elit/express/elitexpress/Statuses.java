package elit.express.elitexpress;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Statuses {
    private final String app = "ios";

    private ArrayList<Status1> status1ArrayList;
    private ArrayList<Status5> status5ArrayList;
    private ArrayList<Status7> status7ArrayList;
    private String price;
    private String orderId;

    public void sendStatus1(final ReceiveCallback callback, final String marshrut_reis, final String data_reis) {
        final String status = "1";

        NetworkService.getInstance()
                .getJSONApi()
                .sendStatus1(status, app, marshrut_reis, data_reis)
                .enqueue(new Callback<ArrayList<Status1>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<Status1>> call, @NonNull Response<ArrayList<Status1>> response) {
                        status1ArrayList = response.body();
                        callback.onCallback();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<Status1>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void sendStatus2(final ReceiveCallback callback, final String marshrut_reis) {
        final String status = "2";

        NetworkService.getInstance()
                .getJSONApi()
                .sendStatus2(status, app, marshrut_reis)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        callback.onCallback();
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    void sendStatus3(final ReceiveCallback callback, final String zone, final String marshrut_reis) {
        final String status = "3";

        NetworkService.getInstance()
                .getJSONApi()
                .sendStatus3(status, app, marshrut_reis, zone)
                .enqueue(new Callback<Map<String, String>[]>() {
                    @Override
                    public void onResponse(Call<Map<String, String>[]> call, Response<Map<String, String>[]> response) {
                        Map<String, String> responseMap = response.body()[0];
                        price = responseMap.get("price");
                        callback.onCallback();
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>[]> call, Throwable t) {

                    }
                });
    }

    void sendStatus4(final String marshrut_reis, final String id_reis, final String date,
                     final String time, final String fio, final String tel,
                     final String mest, final String id_from, final String id_to,
                     final String price, final String info) {
        final String status = "4";

        NetworkService.getInstance()
                .getJSONApi()
                .sendStatus4(status,app,marshrut_reis,id_reis,
                        date,time,fio,tel,mest,id_from,id_to,price,info)
                .enqueue(new Callback<Map<String, String>[]>() {
                    @Override
                    public void onResponse(Call<Map<String, String>[]> call, Response<Map<String, String>[]> response) {
                        Map<String, String> responseMap = response.body()[0];
                        orderId = responseMap.get("order");
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>[]> call, Throwable t) {

                    }
                });
    }

    public void sendStatus5(final ReceiveCallback callback, final String phone){
        final String status="5";

        NetworkService.getInstance()
                .getJSONApi()
                .sendStatus5(status,app,phone)
                .enqueue(new Callback<ArrayList<Status5>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Status5>> call, Response<ArrayList<Status5>> response) {
                        status5ArrayList=response.body();
                        callback.onCallback();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Status5>> call, Throwable t) {

                    }
                });

    }

    void sendStatus6(final String orderid, final String new_status){
        final String status="6";

        NetworkService.getInstance()
                .getJSONApi()
                .sendStatus6(status,app,orderid,new_status)
                .enqueue(new Callback<Map<String, String>[]>() {
                    @Override
                    public void onResponse(Call<Map<String, String>[]> call, Response<Map<String, String>[]> response) {

                    }

                    @Override
                    public void onFailure(Call<Map<String, String>[]> call, Throwable t) {

                    }
                });

    }

    public void sendStatus7(final ReceiveCallback callback, final String marshrut_reis) {
        final String status = "7";

        NetworkService.getInstance()
                .getJSONApi()
                .sendStatus7(status, app, marshrut_reis)
                .enqueue(new Callback<ArrayList<Status7>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<Status7>> call, @NonNull Response<ArrayList<Status7>> response) {
                        status7ArrayList = response.body();
                        callback.onCallback();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<Status7>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public ArrayList<Status1> getStatus1ArrayList() {
        return status1ArrayList;
    }


    public ArrayList<Status7> getStatus7ArrayList() {
        return status7ArrayList;
    }

    public ArrayList<Status5> getStatus5ArrayList() {
        return status5ArrayList;
    }

    public String getPrice() {
        return price;
    }

    public String getOrderId() {
        return orderId;
    }

    void getStatus2() {
        SendRequest sendRequest1 = new SendRequest();

        String request1 = "app=android" + "&status=2" + "&marshrut_reis=" + "1";
        sendRequest1.execute("https://test.sumy.kiev.ua", request1);
//        sendRequest1.execute("http://192.168.0.102/elit.php", request1);
    }

    void getStatus3() {
        SendRequest sendRequest1 = new SendRequest();

        String request1 = "app=ios" + "&status=3" + "&zone=65" + "&marshrut_reis=" + "1";
        sendRequest1.execute("https://test.sumy.kiev.ua", request1);
//        sendRequest1.execute("http://192.168.0.102/elit.php", request1);
    }

    void getStatus4(){
        SendRequest sendRequest1 = new SendRequest();
        String request4 = "app=android" + "&status=4" + "&marshrut_reis=" + "1"
                + "&id_reis=" + "14335"
                + "&date=" + "2020-09-05"
                + "&time=" + "01:00:00"
                + "&fio=" + "xxxxxx"
                + "&tel=" + "yyyy"
                + "&mest=" + "1"
                + "&id_from=" + "37"
                + "&id_to=" + "44"
                + "&price=" + "300"
                + "&info=" + "rervdf";
        sendRequest1.execute("https://test.sumy.kiev.ua", request4);
    }

    void getStatus5(){
        SendRequest sendRequest = new SendRequest();
        String request = "app=android" + "&status=5" + "&phone=" + "0959144687";
        sendRequest.execute("https://test.sumy.kiev.ua", request);
    }

    void getStatus6(){
        SendRequest sendRequest = new SendRequest();
        String request = "app=android" + "&status=6" + "&orderid=" + "138147" + "&new_status=1";

        sendRequest.execute("https://test.sumy.kiev.ua", request);
    }

}
