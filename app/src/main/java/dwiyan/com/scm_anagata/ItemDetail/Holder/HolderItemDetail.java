package dwiyan.com.scm_anagata.ItemDetail.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dwiyan.com.scm_anagata.Holder.ItemClickListner;
import dwiyan.com.scm_anagata.R;

public class HolderItemDetail extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView labelnama,labelkategory,desc,harga,countkeranjang;
    public ImageView image;
    ItemClickListner itemClickListener;
    public LinearLayout lv,lk;

    public HolderItemDetail(@NonNull View itemView) {
        super(itemView);
        labelnama = itemView.findViewById(R.id.judulmakanan);
        image = itemView.findViewById(R.id.img1);
        labelkategory = itemView.findViewById(R.id.kategory);
        harga = itemView.findViewById(R.id.labelharga);
        countkeranjang = itemView.findViewById(R.id.countKeranjang);
        desc = itemView.findViewById(R.id.labeldesc);
        lv = itemView.findViewById(R.id.lyitem);
        lk = itemView.findViewById(R.id.lykeranjang);
        lv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;

    }
}
