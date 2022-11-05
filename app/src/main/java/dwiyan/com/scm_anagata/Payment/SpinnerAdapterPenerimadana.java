package dwiyan.com.scm_anagata.Payment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dwiyan.com.scm_anagata.R;

public class SpinnerAdapterPenerimadana extends BaseAdapter {
    private List<ModelBank> Isdata2 ;
    private Activity activity;
    private LayoutInflater inflater;

    public SpinnerAdapterPenerimadana(List<ModelBank> isdata, Activity activity) {
        Isdata2 = isdata;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return Isdata2.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null)
            view = inflater.inflate(R.layout.spinner_bank, null);
        TextView tv = (TextView) view.findViewById(R.id.textview);
        TextView tvID = (TextView) view.findViewById(R.id.textviewID);
        ModelBank da  = Isdata2.get(position);
        tv.setText(da.getAccountname() +" - " + da.getAccountbank());
        tvID.setText(da.getBankaccountid());
        return view;
    }
}
