package dwiyan.com.scm_anagata.Transaksi.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dwiyan.com.scm_anagata.Holder.ItemClickListner;
import dwiyan.com.scm_anagata.R;

public class HolderItemTransaction extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView count,namamenu,harga,desc,satuan;
    ItemClickListner itemClickListener;
    public LinearLayout lv;

    public HolderItemTransaction(@NonNull View itemView) {
        super(itemView);
        count = itemView.findViewById(R.id.count);
        satuan = itemView.findViewById(R.id.satuan);
        desc = itemView.findViewById(R.id.description);
        harga = itemView.findViewById(R.id.harga);
        namamenu = itemView.findViewById(R.id.namamakanan);
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
