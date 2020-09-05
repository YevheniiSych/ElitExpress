package elit.express.elit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Status5> historyArrayList;

    public HistoryAdapter(Context context, ArrayList<Status5> historyArrayList) {
        this.context = context;
        this.historyArrayList = historyArrayList;
    }

    @Override
    public int getCount() {
        return historyArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return historyArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View v= View.inflate(context,R.layout.items_history,null);
        TextView header= v.findViewById(R.id.headerTextView);
        TextView item=v.findViewById(R.id.historyItem);
        TextView subitem=v.findViewById(R.id.historySubItem);
        ImageButton cancelButton=v.findViewById(R.id.cancelButton);
        ImageButton payButton=v.findViewById(R.id.payButton);


        if(historyArrayList.get(i).getId().equals("reserved")){
            header.setVisibility(View.VISIBLE);
            item.setVisibility(View.GONE);
            subitem.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
            payButton.setVisibility(View.GONE);
            header.setText(R.string.reservedTrips);
        }
        else if(historyArrayList.get(i).getId().equals("paid")){
            header.setVisibility(View.VISIBLE);
            item.setVisibility(View.GONE);
            subitem.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
            payButton.setVisibility(View.GONE);
            header.setText(R.string.paidTrips);
        }
        else if(historyArrayList.get(i).getId().equals("finished")){
            header.setVisibility(View.VISIBLE);
            item.setVisibility(View.GONE);
            subitem.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
            payButton.setVisibility(View.GONE);
            header.setText(R.string.finishedTrips);
        }
        else if(historyArrayList.get(i).getPredoplata().equals("reserved")) {
            header.setVisibility(View.GONE);
            item.setVisibility(View.VISIBLE);
            subitem.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
            payButton.setVisibility(View.VISIBLE);

            item.setText(historyArrayList.get(i).getMarshrut());
            subitem.setText(
                    Parser.swapDate(historyArrayList.get(i).getDates())+", "+
                            historyArrayList.get(i).getTimes() + "\n" +
                    "к-ть місць - "+historyArrayList.get(i).getMest());

            payButtonClick(payButton,i);
            cancelButtonClick(cancelButton,i);
        }
        else if(historyArrayList.get(i).getPredoplata().equals("paid")){
            header.setVisibility(View.GONE);
            item.setVisibility(View.VISIBLE);
            subitem.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
            payButton.setVisibility(View.GONE);

            item.setText(historyArrayList.get(i).getMarshrut());
            subitem.setText(
                    Parser.swapDate(historyArrayList.get(i).getDates())+", "+
                            historyArrayList.get(i).getTimes() + "\n" +
                            "к-ть місць - "+historyArrayList.get(i).getMest());

            cancelButtonClick(cancelButton,i);
        }
        else if(historyArrayList.get(i).getPredoplata().equals("finished")){
            item.setText(historyArrayList.get(i).getMarshrut());
            subitem.setText(
                    Parser.swapDate(historyArrayList.get(i).getDates())+", "+
                            historyArrayList.get(i).getTimes() + "\n" +
                            "к-ть місць - "+historyArrayList.get(i).getMest());

            header.setVisibility(View.GONE);
            item.setVisibility(View.VISIBLE);
            subitem.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.GONE);
            payButton.setVisibility(View.GONE);
        }

        v.setTag(historyArrayList.get(i).getId());
        return v;
    }

    private void cancelButtonClick(ImageButton cancelButton, int i){
        cancelButton.setId(Integer.parseInt(historyArrayList.get(i).getId()));
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderId=String.valueOf(view.getId());
                Alerts alerts=new Alerts();
                alerts.cancelAlert((Activity) context,orderId);
            }
        });
    }

    private void payButtonClick(ImageButton payButton, int i){
        payButton.setId(Integer.parseInt(historyArrayList.get(i).getId()));
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderId=String.valueOf(view.getId());
                Pay pay=new Pay();
                pay.sendPay((Activity) context,orderId);
            }
        });
    }

}
