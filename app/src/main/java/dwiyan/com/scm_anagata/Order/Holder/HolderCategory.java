package dwiyan.com.scm_anagata.Order.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import dwiyan.com.scm_anagata.Holder.ItemClickListner;
import dwiyan.com.scm_anagata.R;


public class HolderCategory extends RecyclerView.ViewHolder {
    public TextView label,desc;
    public ImageView image;
    public CardView cardview;

    public LinearLayout lv;

    public HolderCategory(@NonNull View itemView) {
        super(itemView);
        label = itemView.findViewById(R.id.label);
//        desc = itemView.findViewById(R.id.desc);
        cardview = itemView.findViewById(R.id.cardview);
        image = itemView.findViewById(R.id.imagepromo);
        lv = itemView.findViewById(R.id.lv);
//        lv.setOnClickListener(this);
    }

}
