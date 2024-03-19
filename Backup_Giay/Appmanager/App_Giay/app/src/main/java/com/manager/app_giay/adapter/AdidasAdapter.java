package com.manager.app_giay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manager.app_giay.Interface.ItemClickListener;
import com.manager.app_giay.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manager.app_giay.activity.ChiTietActivity;
import com.manager.app_giay.model.SanPhamMoi;
import com.manager.app_giay.utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

public class AdidasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<SanPhamMoi> array;
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public AdidasAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_DATA){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adidas, parent, false);
            return new MyViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            SanPhamMoi sanpham = array.get(position);
            myViewHolder.tensp.setText(sanpham.getTitle().trim());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.giasp.setText("Giá: " +decimalFormat.format(Double.parseDouble(sanpham.getPrice())) + "Đ" );
            myViewHolder.mota.setText(sanpham.getDescription());


            if(sanpham.getThumbnail().contains("http")){
                Glide.with(context).load(sanpham.getThumbnail()).into(myViewHolder.hinhanh);
            }else {
                String hinh = Utils.BASE_URL + "images/" + sanpham.getThumbnail();
                Glide.with(context).load(hinh).into(myViewHolder.hinhanh);
            }

            myViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){
                        Intent intent = new Intent(context, ChiTietActivity.class);
                        intent.putExtra("chitiet",sanpham);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tensp, giasp, mota, idsp;
        ImageView hinhanh;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.itemadi_tensp);
            giasp = itemView.findViewById(R.id.itemadi_gia);
            mota = itemView.findViewById(R.id.itemadi_mota);
            hinhanh = itemView.findViewById(R.id.itemadi_image);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),false);
        }
    }
}
