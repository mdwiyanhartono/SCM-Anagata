package dwiyan.com.scm_anagata.Order.Adapter;

import static androidx.core.content.ContextCompat.getColor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.DataModel.ModelItemCategory;
import dwiyan.com.scm_anagata.Order.Holder.HolderCategory;
import dwiyan.com.scm_anagata.Order.UpdateRC;
import dwiyan.com.scm_anagata.R;


public class AdapterCategory extends RecyclerView.Adapter<HolderCategory> {

    private List<ModelItemCategory> assetslist;
//    private OnItemClickListener onItemClickListener;
    UpdateRC updateRC;
    Activity activity;
    int row_index = -1;
    boolean check = true;
    boolean select = true;
    public AdapterCategory( UpdateRC updateRC) {
//        this.onItemClickListener = onItemClickListener;
        assetslist = new ArrayList<>();
        this.activity = activity;
        this.updateRC = updateRC;

    }


    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_category, parent, false);
        return new HolderCategory(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final HolderCategory holder, @SuppressLint("RecyclerView") final int position) {
        final ModelItemCategory da = assetslist.get(position);
//        holder.image.setImageResource(da.getImage());
        holder.label.setText(da.getCategoryname());
//        holder.desc.setVisibility(View.GONE);
//        holder.desc.setText(da.getDescription());
        if(!da.getImageFileContent().isEmpty()){
            byte[] decodedString1 = Base64.decode(da.getImageFileContent(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1,0, decodedString1.length);
            holder.image.setImageBitmap(decodedByte);
        } else {
            holder.image.setImageResource(R.drawable.nodata);
        }
        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                updateRC.callback(da.getCategoryid());
//                onItemClickListener.onItemClick(da.getCategoryid(),da.getCategoryname(),da.getImageFileContent());
            }
        });
        if(select){
            if(position == 0){
                updateRC.callback(da.getCategoryid());
                holder.cardview.setCardBackgroundColor(Color.parseColor("#87D8F7"));
//                holder.cardview.setCardBackgroundColor(getColor(activity,R.color.basic3));
            }
            select = false;
        } else {
            if(row_index == position){
//                holder.cardview.setCardBackgroundColor(getColor(activity,R.color.basic3));
            holder.cardview.setCardBackgroundColor(Color.parseColor("#87D8F7"));
            } else {
//                holder.cardview.setCardBackgroundColor(getColor(activity,R.color.basic));
            holder.cardview.setCardBackgroundColor(Color.parseColor("#A1CCEC"));
            }
        }




    }

    @Override
    public int getItemCount() {
        return assetslist.size();
    }

    //
//
    public void setData(List<ModelItemCategory> assetslist) {
        this.assetslist.clear();
        this.assetslist = assetslist;
        notifyDataSetChanged();
    }

//    public interface OnItemClickListener {
//        void onItemClick(String IdProduct,String ProductName,String FileContent);
//    }

}
