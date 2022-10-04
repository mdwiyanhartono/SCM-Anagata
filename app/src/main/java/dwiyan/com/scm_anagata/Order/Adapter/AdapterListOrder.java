package dwiyan.com.scm_anagata.Order.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.History.Holder.HolderItemHistory;
import dwiyan.com.scm_anagata.History.Model.ModelHistory;
import dwiyan.com.scm_anagata.Order.Holder.HolderListOrder;
import dwiyan.com.scm_anagata.Order.Model.ModelOrder;
import dwiyan.com.scm_anagata.R;


public class AdapterListOrder extends RecyclerView.Adapter<HolderListOrder> {

    private List<ModelOrder> menulist;

    private OnItemClickListener onItemClickListener;

    public AdapterListOrder(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        menulist = new ArrayList<>();
    }

    @NonNull
    @Override
    public HolderListOrder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order, parent, false);
        return new HolderListOrder(v);
    }

    @Override
    public void onBindViewHolder(final HolderListOrder holder, final int position) {
        final ModelOrder menu = menulist.get(position);
        holder.labelnama.setText("#"+menu.getTransactionmastercode());
        holder.invoice.setText(menu.getInvoicenumber());
        holder.date.setText("\u2022 masa tenggang : "+menu.getDuedate());
        if(Integer.parseInt(menu.getIsapprove()) == 1 && menu.getIspaid().equals("f")){
            holder.Status.setText("\u2022 Pending Confirm");
        } else if (Integer.parseInt(menu.getIsapprove()) == 2 && menu.getIspaid().equals("f")){
            holder.Status.setText("\u2022 Pending Approval");
        } else if (Integer.parseInt(menu.getIsapprove()) == 3 && menu.getIspaid().equals("f")){
            holder.Status.setText("\u2022 Confirmed");
        } else if (Integer.parseInt(menu.getIsapprove()) == 3 && menu.getIspaid().equals("t")){
            holder.Status.setText("\u2022 Closed");
        } else {
            holder.Status.setText("\u2022 Declined");
        }
        if(menu.getIspaid().equals("f")){
            holder.ispaid.setText("\u2022 Belum dibayar");
        } else if (Integer.parseInt(menu.getIsapprove()) == 3 && menu.getIspaid().equals("t")){
            holder.ispaid.setText("\u2022 Sudah dibayar");
        } else {
            holder.Status.setText("\u2022 Blank");
        }
        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(menu.getTransactionmasterid(),menu.getIspaid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return menulist.size();
    }

    public void setData(List<ModelOrder> menulist) {
        this.menulist.clear();
        this.menulist = menulist;
        notifyDataSetChanged();
    }



    public interface OnItemClickListener {
        void onItemClick(String idmenu, String ispaid);
    }
}
