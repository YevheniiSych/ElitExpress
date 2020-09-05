package elit.express.elitexpress;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<Status5> status5ArrayList;
    LinearLayout historyLinearLayout;
    ListView listView;
    ArrayList<Status5> historyList;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


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

    void setInitialData() {
        historyLinearLayout = findViewById(R.id.historyLinearLayout);
        listView = findViewById(R.id.historyListView);
        downloadHistory();
        historyList = new ArrayList<>();
    }

    void downloadHistory() {

        LoadingDialog.loading(this);

        String phone = RegistrationActivity.getPhone();

        Statuses.sendStatus5(new ReceiveCallback() {
            @Override
            public void onCallback() {
                showHistory();
            }
        }, phone);
    }

    void showHistory() {
        status5ArrayList = Statuses.getStatus5ArrayList();
        showTrips();
    }

    void showTrips() {
        selectReservedTrips();
        selectPaidTrips();
        selectFinishedTrips();
        if(!historyList.isEmpty()) {
            HistoryAdapter adapter = new HistoryAdapter(this, historyList);
            listView.setAdapter(adapter);
        }
        else {
            showEmpty();
        }
        LoadingDialog.dismiss();
    }

    void selectReservedTrips() {
//        listView.addHeaderView(createHeader(getString(R.string.finishedTrips)), null, false);
        boolean j=true;
        for (int i = 0; i < status5ArrayList.size(); i++) {
            if ((status5ArrayList.get(i).getStatus().equals("2") ||
                    status5ArrayList.get(i).getStatus().equals("3")) &&
                    status5ArrayList.get(i).getStatusPay().equals("1")
            ) {
                if(j) {
                    historyList.add(new Status5("reserved"));
                    j=false;
                }
                status5ArrayList.get(i).setPredoplata("reserved");
                historyList.add(status5ArrayList.get(i));
            }
        }
    }

    void selectPaidTrips() {
//        listView.addHeaderView(createHeader(getString(R.string.paidTrips)), null, false);
        boolean j=true;
        for (int i = 0; i < status5ArrayList.size(); i++) {
            if (!status5ArrayList.get(i).getStatusPay().equals("1") &&
                    !status5ArrayList.get(i).getStatus().equals("4")
                    && !status5ArrayList.get(i).getStatus().equals("2")) {
                if(j) {
                    historyList.add(new Status5("paid"));
                    j=false;
                }
                status5ArrayList.get(i).setPredoplata("paid");
                historyList.add(status5ArrayList.get(i));
            }
        }
    }

    void selectFinishedTrips() {
//        listView.addHeaderView(createHeader(getString(R.string.reservedTrips)), null, false);
        boolean j=true;
        for (int i = 0; i < status5ArrayList.size(); i++) {
            if (status5ArrayList.get(i).getStatus().equals("4")) {
                if(j) {
                    historyList.add(new Status5("finished"));
                    j=false;
                }
                status5ArrayList.get(i).setPredoplata("finished");
                historyList.add(status5ArrayList.get(i));
            }
        }
    }

    void showEmpty() {
        TextView empty = new TextView(this);
        empty.setText(R.string.emptyTrips);
        empty.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        empty.setTextSize(20);

        historyLinearLayout.addView(empty);
    }

}
