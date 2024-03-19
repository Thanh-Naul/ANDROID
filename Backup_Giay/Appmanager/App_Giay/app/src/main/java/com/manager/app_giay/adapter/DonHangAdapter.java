package com.manager.app_giay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manager.app_giay.Interface.ItemClickListener;
import com.manager.app_giay.R;
import com.manager.app_giay.model.DonHang;
import com.manager.app_giay.model.EventBus.DonHangEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    Context context;
    List<DonHang> listdonhang;

    public DonHangAdapter(Context context, List<DonHang> listdonhang) {
        this.context = context;
        this.listdonhang = listdonhang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonHang donHang = listdonhang.get(position);
        holder.txtdonhang.setText("Đơn hàng: " + donHang.getId());
        holder.diachi.setText("Địa chỉ: " + donHang.getAddress());
        holder.username.setText("Nguời đặt: " + donHang.getUsername());
        holder.status.setText(trangThaiDon(donHang.getStatus()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                holder.rechitiet.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        linearLayoutManager.setInitialPrefetchItemCount(donHang.getItem().size());
         ChiTietAdapter chiTietAdapter = new ChiTietAdapter(context,donHang.getItem());
         holder.rechitiet.setLayoutManager(linearLayoutManager);
         holder.rechitiet.setAdapter(chiTietAdapter);
         holder.rechitiet.setRecycledViewPool(viewPool);
         holder.setListener(new ItemClickListener() {
             @Override
             public void onClick(View view, int pos, boolean isLongClick) {
                 if(isLongClick){
                     EventBus.getDefault().postSticky(new DonHangEvent(donHang));

                 }

             }
         });

    }
    private String trangThaiDon(int status){
        String result = "";
        switch (status){
            case 0:
                result = "Đơn hàng đang được xử lý";
                break;
            case 1:
                result = "Đơn hàng đã được chấp nhận";
                break;
            case 2:
                result = "Đơn hàng đã giao cho đơn vị vận chuyển";
                break;
            case 3:
                result = "Đơn hàng đã giao thành công";
                break;
            case 4:
                result = "Đơn hàng đã huỷ";
                break;

        }

        return result;

    }

    @Override
    public int getItemCount() {
        return listdonhang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        TextView txtdonhang, status, diachi, username;
        RecyclerView rechitiet;
        ItemClickListener listener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdonhang = itemView.findViewById(R.id.iddonhang);
            status = itemView.findViewById(R.id.trangthai);
            diachi = itemView.findViewById(R.id.diachi_donhang);
            username = itemView.findViewById(R.id.user_donhang);
            rechitiet = itemView.findViewById(R.id.recyclerview_chitiet);
            itemView.setOnLongClickListener(this);
        }

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onLongClick(View v) {
            listener.onClick(v, getAdapterPosition(),true);
            return false;
        }
    }
}
