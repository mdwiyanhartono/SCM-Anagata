package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.ItemDetail.Keranjang.ConfirmRequest;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Holder.HolderRequestItem;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ModelRequestItem;
import dwiyan.com.scm_anagata.R;

public class AdapterRequestAll extends RecyclerView.Adapter<HolderRequestItem> {

    private List<ModelRequestItem> assetslist;
    private OnItemClickListener onItemClickListener;

    public AdapterRequestAll(ConfirmRequest onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assetslist = new ArrayList<>();

    }


    @NonNull
    @Override
    public HolderRequestItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_request_item, parent, false);
        return new HolderRequestItem(v);
    }

    String status = "";

    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    public void onBindViewHolder(final HolderRequestItem holder, final int position) {
        final ModelRequestItem da = assetslist.get(position);
        holder.namaitem.setText(da.getItemName());
        holder.visitDate.setText(da.getVisitDate());
        holder.VisitTime.setText(da.getTimeRequest());
        if (da.getStatus().equals("1")) {
            status = "Sesuai Request";
            holder.status.setText(status);
            holder.status.setTextColor(Color.parseColor("#51EA45"));
        } else {
            status = "Perubahan Jam Kunjungan terhadap Item ini dari yang sudah anda request";
            holder.status.setText(status);
            holder.status.setTextColor(Color.parseColor("#FF3636"));
        }
    }

    @Override
    public int getItemCount() {
        return assetslist.size();
    }

    public void setData(List<ModelRequestItem> assetslist) {
        this.assetslist.clear();
        this.assetslist = assetslist;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void Detail(String MenuID);
    }

}
