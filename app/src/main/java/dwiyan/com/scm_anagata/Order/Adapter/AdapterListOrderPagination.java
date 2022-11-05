package dwiyan.com.scm_anagata.Order.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dwiyan.com.scm_anagata.Holder.OnItemClickListener;
import dwiyan.com.scm_anagata.ItemDetail.Adapter.AdapterListItem;
import dwiyan.com.scm_anagata.ItemDetail.Holder.HolderItemDetail;
import dwiyan.com.scm_anagata.ItemDetail.Model.ModelItem;
import dwiyan.com.scm_anagata.R;

public class AdapterListOrderPagination extends RecyclerView.Adapter<HolderItemDetail> {

    // variable for our array list and context.
    private ArrayList<ModelItem> menulist;
    private Context context;

    private OnItemClickListener onItemClickListener;


    public AdapterListOrderPagination(ArrayList<ModelItem> modelItems, Context context,OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.menulist = modelItems;
        this.context = context;
    }


    @NonNull
    @Override
    public HolderItemDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new HolderItemDetail(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItemDetail holder, int position) {
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


    public interface OnItemClickListener {
        void onItemClick(String idmenu);
    }


}
