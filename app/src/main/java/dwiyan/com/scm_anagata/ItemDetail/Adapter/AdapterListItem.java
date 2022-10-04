package dwiyan.com.scm_anagata.ItemDetail.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.ItemDetail.Holder.HolderItemDetail;
import dwiyan.com.scm_anagata.ItemDetail.Model.ModelItem;
import dwiyan.com.scm_anagata.R;


public class AdapterListItem extends RecyclerView.Adapter<HolderItemDetail> {

    private List<ModelItem> menulist;

    private OnItemClickListener onItemClickListener;

    public AdapterListItem(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        menulist = new ArrayList<>();
    }

    @NonNull
    @Override
    public HolderItemDetail onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new HolderItemDetail(v);
    }

    @Override
    public void onBindViewHolder(final HolderItemDetail holder, final int position) {
        final ModelItem menu = menulist.get(position);
        holder.labelnama.setText(menu.getItemname());
        holder.labelkategory.setText("Dalam satuan / " + menu.getItemuom());
        holder.harga.setText(menu.getItempriceview());
        holder.desc.setText(menu.getItemdesc());
        if(Integer.parseInt(menu.getCountChart()) > 0){
            holder.lk.setVisibility(View.VISIBLE);
            holder.countkeranjang.setText(menu.getCountChart());
        } else {
            holder.lk.setVisibility(View.GONE);
            holder.countkeranjang.setText("");
        }
        if(!menu.getImageFileContent().isEmpty()){
            byte[] decodedString1 = Base64.decode(menu.getImageFileContent(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1,0, decodedString1.length);
            holder.image.setImageBitmap(decodedByte);
        }
        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(menu.getItemid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return menulist.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ModelItem> menulist) {
        this.menulist.clear();
        this.menulist = menulist;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String idmenu);
    }
}
