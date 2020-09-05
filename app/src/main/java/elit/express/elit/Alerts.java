package elit.express.elit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Alerts {
    public void networkNotAvailable(Activity activity) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(activity);
        aBuilder.setMessage(R.string.checkInternet)
                .setCancelable(true)
                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = aBuilder.create();
        alert.setTitle(R.string.connectionError);
        alert.show();
    }

    public void reserveAlert(final ReceiveCallback callback, final Activity activity, final Filler filler) {

        String message = Parser.allData(activity);

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(activity);
        aBuilder.setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                LoadingDialog.loading(activity);
                                sendReserve(callback,filler);

                            }
                        }
                )
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                });
        AlertDialog alert = aBuilder.create();
        alert.setTitle(R.string.checkInputData);
        alert.show();
    }

    private void sendReserve(final ReceiveCallback callback, Filler filler) {
        String  race=filler.getRace(),
                race_id=filler.getReisID(),
                date=filler.getDate(),
                time=filler.getTime(),
                name=RegistrationActivity.getName(),
                phone=RegistrationActivity.getPhone(),
                placeCount=filler.getPlaceCount(),
                id_from=filler.getID_from(),
                id_to=filler.getId_to(),
                price=Statuses.getPrice(),
                info=filler.getInfo();
        Statuses.sendStatus4(new ReceiveCallback() {
                                 @Override
                                 public void onCallback() {
                                     callback.onCallback();
                                 }
                             },
                race,
                race_id,
                date,
                time,
                name,
                phone,
                placeCount,
                id_from,
                id_to,
                price,
                info
        );
    }

    public void cancelAlert(final Activity activity, final String orderId){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(activity);
        aBuilder.setMessage(R.string.checkCancelBody)
                .setCancelable(true)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Statuses.sendStatus6(new ReceiveCallback() {
                                    @Override
                                    public void onCallback() {
                                        activity.finish();
                                        Items.setHistoryActivity(activity);
                                    }
                                }, orderId, "1");
                            }
                        }
                )
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                });
        AlertDialog alert = aBuilder.create();
        alert.setTitle(R.string.checkCancel);
        alert.show();
    }

}
