package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Adapter;

import static java.lang.Double.parseDouble;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Holder.HolderKeranjang;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ModelKeranjangAll;
import dwiyan.com.scm_anagata.R;

public class AdapterKeranjang extends RecyclerView.Adapter<HolderKeranjang> {

    private List<ModelKeranjangAll> assetslist;
    private OnItemClickListener onItemClickListener;
    private DecimalFormat df;
    String pattern = "#,###,###,###,###.00";
    public AdapterKeranjang(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assetslist = new ArrayList<>();

    }


    @NonNull
    @Override
    public HolderKeranjang onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_keranjang, parent, false);
        return new HolderKeranjang(v);
    }

    @Override
    public void onBindViewHolder(final HolderKeranjang holder, final int position) {
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        df = (DecimalFormat) nf;
        {
            df.applyPattern(pattern);
        }
        df.setDecimalSeparatorAlwaysShown(true);
        final ModelKeranjangAll da = assetslist.get(position);
        holder.namamenu.setText(da.getItemname());
        holder.satuan.setText(da.getUomname());
        holder.desc.setText(da.getDescription());
        holder.harga.setText(String.valueOf(df.format(parseDouble(da.getTotalprice()))));
        holder.count.setText(da.getQuantity());

        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.Detail(da.getItemid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return assetslist.size();
    }

    public void setData(List<ModelKeranjangAll> assetslist) {
        this.assetslist.clear();
        this.assetslist = assetslist;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void Detail(String MenuID);
    }

}
