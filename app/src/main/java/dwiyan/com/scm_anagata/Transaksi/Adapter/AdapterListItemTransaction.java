package dwiyan.com.scm_anagata.Transaksi.Adapter;

import static java.lang.Double.parseDouble;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dwiyan.com.scm_anagata.DataModel.ModelConfirmTrans;
import dwiyan.com.scm_anagata.ItemDetail.Holder.HolderItemDetail;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ModelKeranjangAll;
import dwiyan.com.scm_anagata.ItemDetail.Model.ModelItem;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.Transaksi.Holder.HolderItemTransaction;


public class AdapterListItemTransaction extends RecyclerView.Adapter<HolderItemTransaction> {

    private List<ModelConfirmTrans> assetslist;
    private DecimalFormat df;
    String pattern = "#,###,###,###,###.00";
    private OnItemClickListener onItemClickListener;

    public AdapterListItemTransaction(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assetslist = new ArrayList<>();
    }

    @NonNull
    @Override
    public HolderItemTransaction onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_keranjang, parent, false);
        return new HolderItemTransaction(v);
    }

    @Override
    public void onBindViewHolder(final HolderItemTransaction holder, final int position) {
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        df = (DecimalFormat) nf;
        {
            df.applyPattern(pattern);
        }
        df.setDecimalSeparatorAlwaysShown(true);
        final ModelConfirmTrans da = assetslist.get(position);
        holder.namamenu.setText(da.getItemname());
        holder.satuan.setText(da.getUomname());
        holder.desc.setText(da.getDescription());
        holder.harga.setText(String.valueOf(df.format(parseDouble(da.getTotalpriceapproved()))));
        holder.count.setText(da.getQuantityapproved());

        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onItemClickListener.Detail(da.getItemid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return assetslist.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ModelConfirmTrans> menulist) {
        this.assetslist.clear();
        this.assetslist = menulist;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String idmenu);
    }
}
