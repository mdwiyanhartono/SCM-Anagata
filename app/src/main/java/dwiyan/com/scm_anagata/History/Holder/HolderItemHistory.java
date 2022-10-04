package dwiyan.com.scm_anagata.History.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dwiyan.com.scm_anagata.Holder.ItemClickListner;
import dwiyan.com.scm_anagata.R;

public class HolderItemHistory extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView labelnama,Status;
    ItemClickListner itemClickListener;
    public LinearLayout lv,lk;

    public HolderItemHistory(@NonNull View itemView) {
        super(itemView);
        labelnama = itemView.findViewById(R.id.labelTrans);
        Status = itemView.findViewById(R.id.status);
        lv = itemView.findViewById(R.id.ly1);
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
