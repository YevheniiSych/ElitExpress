package elit.express.elitexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

import static android.widget.Toast.LENGTH_LONG;

public class ReservationActivity extends AppCompatActivity {

    Filler filler;
    ArrayList<Status1> status1ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        filler=new Filler(this);
        fillInitialData();

        getStatus1();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toEditName:
                Items.setRegistrationActivity(this);
                break;
            case R.id.toHistory:
                Items.setHistoryActivity(this);
                break;
            case R.id.toInfo:
                Items.showInfo(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }

    void getStatus1(){
        final String status="1";
        final String app="ios";
        final String marshrut_reis=filler.getRace();
        final String data_reis=filler.getDate();

        NetworkService.getInstance()
                .getJSONApi()
                .getStatus1(status,app,marshrut_reis,data_reis)
                .enqueue(new Callback<ArrayList<Status1>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<Status1>> call, @NonNull Response<ArrayList<Status1>> response) {
                        status1ArrayList=response.body();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<Status1>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    void fillInitialData(){
        filler.setDate();
        filler.fillRaceSpinner();
    }

//    void getStatus1(){
//        SendRequest sendRequest1 = new SendRequest();
//
//        String request1 = "app=ios" + "&status=1" + "&marshrut_reis=" + "1" + "&data_reis=" + "2020-08-27";
//        sendRequest1.execute("https://m.sumy.kiev.ua", request1);
////        sendRequest1.execute("http://192.168.0.102/elit.php", request1);
//    }

}
