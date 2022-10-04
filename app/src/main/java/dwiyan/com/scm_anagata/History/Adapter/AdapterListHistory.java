package dwiyan.com.scm_anagata.History.Adapter;

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

import dwiyan.com.scm_anagata.History.Holder.HolderItemHistory;
import dwiyan.com.scm_anagata.History.Model.ModelHistory;
import dwiyan.com.scm_anagata.ItemDetail.Holder.HolderItemDetail;
import dwiyan.com.scm_anagata.ItemDetail.Model.ModelItem;
import dwiyan.com.scm_anagata.R;


public class AdapterListHistory extends RecyclerView.Adapter<HolderItemHistory> {

    private List<ModelHistory> menulist;

    private OnItemClickListener onItemClickListener;

    public AdapterListHistory(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        menulist = new ArrayList<>();
    }

    @NonNull
    @Override
    public HolderItemHistory onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return new HolderItemHistory(v);
    }

    @Override
    public void onBindViewHolder(final HolderItemHistory holder, final int position) {
        final ModelHistory menu = menulist.get(position);
        holder.labelnama.setText(menu.getCreateddate());
        if(Integer.parseInt(menu.getIsapprove()) == 1 && menu.getIspaid().equals("f")){
            holder.Status.setText("Pending Confirm");
        } else if (Integer.parseInt(menu.getIsapprove()) == 2 && menu.getIspaid().equals("f")){
            holder.Status.setText("Pending Approval");
        } else if (Integer.parseInt(menu.getIsapprove()) == 3 && menu.getIspaid().equals("f")){
            holder.Status.setText("Confirmed");
        } else if (Integer.parseInt(menu.getIsapprove()) == 3 && menu.getIspaid().equals("t")){
            holder.Status.setText("Closed");
        } else {
            holder.Status.setText("Declined");
        }


        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(menu.getTransactionmasterid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return menulist.size();
    }

    public void setData(List<ModelHistory> menulist) {
        this.menulist.clear();
        this.menulist = menulist;
        notifyDataSetChanged();
    }



    public interface OnItemClickListener {
        void onItemClick(String idmenu);
    }
}
