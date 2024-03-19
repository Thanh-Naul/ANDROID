package com.example.app_giay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.app_giay.R;
import com.example.app_giay.adapter.AdidasAdapter;
import com.example.app_giay.model.SanPhamMoi;
import com.example.app_giay.retrofit.ApiBanHang;
import com.example.app_giay.retrofit.RetrofitClient;
import com.example.app_giay.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AdidasActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page = 1;
    int loai;
    AdidasAdapter adapteradi;
    List<SanPhamMoi> sanPhamMoiList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adidas);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        loai = getIntent().getIntExtra("loai",1);
        AnhXa();
        ActionToolBar();
        getData(page);
        addEventLoad();
    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading == false){
                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == sanPhamMoiList.size() - 1){
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.add(null);
                adapteradi.notifyItemInserted(sanPhamMoiList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.remove(sanPhamMoiList.size()-1);
                adapteradi.notifyItemInserted(sanPhamMoiList.size());
                page = page + 1;
                getData(page);
                adapteradi.notifyDataSetChanged();
                isLoading = false;
            }
        },1700);
    }

    private void getData(int page) {
        compositeDisposable.add(apiBanHang.getSanPham(page,loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   sanPhamMoiModel -> {
                        if(sanPhamMoiModel.isSuccess()){
                            if(adapteradi == null){
                                sanPhamMoiList = sanPhamMoiModel.getResult();
                                adapteradi = new AdidasAdapter(getApplicationContext(),sanPhamMoiList);
                                recyclerView.setAdapter(adapteradi);
                            }else {
                                int vitri = sanPhamMoiList.size()-1;
                                int soluongadd = sanPhamMoiModel.getResult().size();
                                for(int i = 0; i < soluongadd; i++){
                                    sanPhamMoiList.add(sanPhamMoiModel.getResult().get(i));
                                }
                                adapteradi.notifyItemRangeInserted(vitri, soluongadd);
                            }

                        }else {
                            Toast.makeText(getApplicationContext(),"Hết dữ liệu", Toast.LENGTH_LONG).show();
                            isLoading =true;
                        }
                   },
                   throwable -> {
                       Toast.makeText(getApplicationContext(), "Không kết nối server", Toast.LENGTH_SHORT).show();
                   }
                ));
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar);
        if(loai == 1){
            toolbar.setTitle("Adidas");
        }else {
            toolbar.setTitle("Nike");
        }
        recyclerView = findViewById(R.id.recyclerview_adidas);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        sanPhamMoiList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}