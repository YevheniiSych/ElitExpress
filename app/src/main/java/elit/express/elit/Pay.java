package elit.express.elit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class Pay {
    public void sendPay(Activity activity, String orderNumber) {
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("https://m.sumy.kiev.ua/pay.php?orderID=" + orderNumber));
        activity.startActivity(browserIntent);
    }
}
