package dwiyan.com.scm_anagata.Adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.DataModel.ModelChat;
import dwiyan.com.scm_anagata.R;


public class AdapterChat extends RecyclerView.Adapter<AdapterChat.HolderChat> {

    String alamatusaha, dpd, loanid, email;
    private List<ModelChat> chat;
//    private AdapterImageListViewComplaint.OnItemClickListener onItemClickListener;

    public AdapterChat() {
//        this.onItemClickListener = onItemClickListener;
        chat = new ArrayList<>();
    }


    @NonNull
    @Override
    public HolderChat onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 2){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
            return new HolderChat(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
            return new HolderChat(v);
        }

    }

    @Override
    public void onBindViewHolder(final AdapterChat.HolderChat holder, final int position) {
        final ModelChat dm = chat.get(position);
        holder.Comment.setText(dm.getChattext());
        holder.username.setText(dm.getDisplayname());
        holder.tgl.setText(dm.getCreateddate());
//        File image = new File(dm.getF_file_doc());
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        Bitmap bitmap1 = null;
//        bitmap1 = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);

//        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
//        bitmap1.compress(Bitmap.CompressFormat.JPEG,100, baos);
//        byte [] b = baos.toByteArray();
//        String temp = Base64.encodeToString(b, Base64.DEFAULT);
//
//
//
//        byte[] decodedString1 = Base64.decode(dm.get(), Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
//        holder.image1.setImageBitmap(decodedByte);
    }

    @Override
    public int getItemCount() {
        return chat.size();
    }

    // Untuk resize bitmap
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void setData(List<ModelChat> comment) {
        this.chat.clear();
        this.chat.addAll(comment);
        notifyDataSetChanged();
    }

    public class HolderChat extends RecyclerView.ViewHolder{

        public TextView tgl,Comment,username;
        public HolderChat(View itemView){
            super(itemView);

            tgl = itemView.findViewById(R.id.tglComment);
            username = itemView.findViewById(R.id.namauser);
            Comment = itemView.findViewById(R.id.Comment);
        }
    }

    @Override
    public int getItemViewType(int position) {

        return Integer.parseInt(chat.get(position).getType());
    }
}