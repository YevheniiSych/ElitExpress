package elit.express.elit;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {
    static private AlertDialog dialog;

    static public void loading(Activity activity){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);

        LayoutInflater inflater =activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }

    static public void dismiss(){
        dialog.dismiss();
    }
}
