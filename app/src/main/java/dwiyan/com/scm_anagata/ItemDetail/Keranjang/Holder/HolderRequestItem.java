package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dwiyan.com.scm_anagata.Holder.ItemClickListner;
import dwiyan.com.scm_anagata.R;

public class HolderRequestItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView namaitem,visitDate,status,VisitTime;
    ItemClickListner itemClickListener;
    public LinearLayout lv;

    public HolderRequestItem(@NonNull View itemView) {
        super(itemView);
        namaitem = itemView.findViewById(R.id.namaitem);
        visitDate = itemView.findViewById(R.id.VisitDate);
        VisitTime = itemView.findViewById(R.id.VisitTime);
        status = itemView.findViewById(R.id.status);
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
