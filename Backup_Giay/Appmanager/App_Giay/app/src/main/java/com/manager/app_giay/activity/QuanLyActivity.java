package com.manager.app_giay.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.manager.app_giay.R;
import com.manager.app_giay.adapter.SanPhamMoiAdapter;
import com.manager.app_giay.model.EventBus.SuaXoaEvent;
import com.manager.app_giay.model.SanPhamMoi;
import com.manager.app_giay.retrofit.ApiBanHang;
import com.manager.app_giay.retrofit.RetrofitClient;
import com.manager.app_giay.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QuanLyActivity extends AppCompatActivity {

    ImageView img_them;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SanPhamMoi> list;
    SanPhamMoiAdapter adapter;
    SanPhamMoi sanPhamSuaXoa;
    private static final int REQUEST_ADD_PRODUCT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        initView();
        initControl();
        getSpMoi();
    }

    private void initControl() {
        img_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThemSPActivity.class);
                startActivityForResult(intent, REQUEST_ADD_PRODUCT);
            }
        });
    }
    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if(sanPhamMoiModel.isSuccess()){
                                list = sanPhamMoiModel.getResult();
                                adapter = new SanPhamMoiAdapter(getApplicationContext(),list);
                                recyclerView.setAdapter(adapter);
                            }
                        },
                        throwable ->{
                            Toast.makeText(getApplicationContext(), "Không kết nối được với server" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_PRODUCT && resultCode == RESULT_OK) {
            getSpMoi();
        }
    }

    private void initView() {
        img_them = findViewById(R.id.img_them);
        recyclerView = findViewById(R.id.recyclerview_ql);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals("Sửa")){
            suaSanPham();
        }else if(item.getTitle().equals("Xoá")){
            xoaSanPham();
        }
        return super.onContextItemSelected(item);
    }

    private void xoaSanPham() {
        compositeDisposable.add(apiBanHang.xoaSanPham(sanPhamSuaXoa.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        messageModel -> {
                            if(messageModel.isSuccess()){
                                Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_LONG).show();
                                getSpMoi();
                            }else {
                                Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }, throwable -> {
                            Log.d("log1", throwable.getMessage());
                        }
                ));
    }

    private void suaSanPham() {
        Intent intent = new Intent(getApplicationContext(), ThemSPActivity.class);
        intent.putExtra("sua", sanPhamSuaXoa);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventSuaXoa(SuaXoaEvent event){
        if(event != null){
            sanPhamSuaXoa = event.getSanPhamMoi();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}