package com.manager.app_giay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.app_giay.R;
import com.manager.app_giay.adapter.AdidasAdapter;
import com.manager.app_giay.model.SanPhamMoi;
import com.manager.app_giay.retrofit.ApiBanHang;
import com.manager.app_giay.retrofit.RetrofitClient;
import com.manager.app_giay.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edtsearch;
    AdidasAdapter adapteradi;
    List<SanPhamMoi> sanPhamMoiList;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        ActionToolBar();
    }

    private void initView() {
        sanPhamMoiList = new ArrayList<>();
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        edtsearch = findViewById(R.id.edtsearch);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tìm kiếm");
        recyclerView = findViewById(R.id.recyclerview_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    sanPhamMoiList.clear();
                    adapteradi = new AdidasAdapter(getApplicationContext(), sanPhamMoiList);
                    recyclerView.setAdapter(adapteradi);
                }else {
                    getDataSearch(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getDataSearch(String s) {
        sanPhamMoiList.clear();

        compositeDisposable.add(apiBanHang.search(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    sanPhamMoiModel -> {
                        if(sanPhamMoiModel.isSuccess()){
                            sanPhamMoiList = sanPhamMoiModel.getResult();
                            adapteradi = new AdidasAdapter(getApplicationContext(), sanPhamMoiList);
                            recyclerView.setAdapter(adapteradi);
                        }else {
                            //Toast.makeText(getApplicationContext(), sanPhamMoiModel.getMessage(), Toast.LENGTH_SHORT).show();
                            sanPhamMoiList.clear();
                            adapteradi.notifyDataSetChanged();
                        }

                    },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}