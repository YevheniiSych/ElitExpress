package elit.express.elitexpress;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private Statuses statuses;
    ArrayList <Status5> status5ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        statuses=new Statuses();

        setInitialData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.toHistory).setVisible(false);
        menu.findItem(R.id.toEditName).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Items.showInfo(this);
        return super.onOptionsItemSelected(item);
    }

    void setInitialData(){
        downloadHistory();
    }

    void downloadHistory(){

        String phone=RegistrationActivity.getPhone();

        statuses.sendStatus5(new ReceiveCallback() {
            @Override
            public void onCallback() {
                showHistory();
            }
        }, phone);
    }

    void showHistory(){
        status5ArrayList=statuses.getStatus5ArrayList();
    }

}
