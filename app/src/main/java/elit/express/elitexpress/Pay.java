package elit.express.elitexpress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class Pay {
    public void sendPay(Activity activity, String orderNumber) {
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("https://m.sumy.kiev.ua/pay.php?orderID=" + orderNumber));
        activity.startActivity(browserIntent);
    }
}
