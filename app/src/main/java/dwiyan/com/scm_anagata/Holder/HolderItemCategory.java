package dwiyan.com.scm_anagata.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dwiyan.com.scm_anagata.R;


public class HolderItemCategory extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView label,desc;
    public ImageView image;
    ItemClickListner itemClickListener;
    public LinearLayout lv;

    public HolderItemCategory(@NonNull View itemView) {
        super(itemView);
        label = itemView.findViewById(R.id.label);
        desc = itemView.findViewById(R.id.desc);
        image = itemView.findViewById(R.id.imagepromo);
        lv = itemView.findViewById(R.id.lv);
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
