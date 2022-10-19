package dwiyan.com.scm_anagata.Adapter;

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

import dwiyan.com.scm_anagata.DataModel.ModelItemCategory;
import dwiyan.com.scm_anagata.Holder.HolderItemCategory;
import dwiyan.com.scm_anagata.Order.Holder.HolderCategory;
import dwiyan.com.scm_anagata.R;


public class AdapterItemCategory extends RecyclerView.Adapter<HolderCategory> {

    private List<ModelItemCategory> assetslist;
    private OnItemClickListener onItemClickListener;

    public AdapterItemCategory(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assetslist = new ArrayList<>();

    }


    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemcategory, parent, false);
        return new HolderCategory(v);
    }

    @Override
    public void onBindViewHolder(final HolderCategory holder, final int position) {
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
                onItemClickListener.onItemClick(da.getCategoryid(),da.getCategoryname(),da.getImageFileContent());
            }
        });
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

    public interface OnItemClickListener {
        void onItemClick(String IdProduct,String ProductName,String FileContent);
    }

}
