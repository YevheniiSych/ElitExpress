package elit.express.elitexpress;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;

public class Items {

    static void setReservationActivity(Activity activity){
        Intent intent = new Intent("ReservationActivity");
        activity.startActivity(intent);
//        activity.finish();
    }

    static void setRegistrationActivity(Activity activity){
        Intent intent = new Intent("RegistrationActivity");
        activity.startActivity(intent);
        //activity.finish();
    }

    static void setHistoryActivity(Activity activity){
        Intent intent = new Intent("HistoryActivity");
        activity.startActivity(intent);
        //activity.finish();
    }

    static void showInfo(Activity activity){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(activity);
        aBuilder.setMessage(R.string.info)
                .setCancelable(true)
                .setNegativeButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = aBuilder.create();
        alert.setTitle(R.string.toInfo);
        alert.show();
    }
}
