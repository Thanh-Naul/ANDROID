package com.manager.app_giay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.manager.app_giay.R;
import com.manager.app_giay.retrofit.ApiBanHang;
import com.manager.app_giay.retrofit.RetrofitClient;
import com.manager.app_giay.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThongKeActivity extends AppCompatActivity {

    Toolbar toolbar;
    PieChart pieChart;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        initView();
        ActionToolBar();
        getDataChart();
    }

    private void getDataChart() {
        List<PieEntry> listdata = new ArrayList<>();
        compositeDisposable.add(apiBanHang.getThongKe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        thongKeModel -> {
                            if(thongKeModel.isSuccess()){
                                for (int i = 0; i < thongKeModel.getResult().size(); i++){
                                    String title = thongKeModel.getResult().get(i).getTitle();
                                    int total = thongKeModel.getResult().get(i).getTotal();
                                    listdata.add(new PieEntry(total, title));
                                }
                                PieDataSet pieDataSet = new PieDataSet(listdata, "");
                                PieData data = new PieData();
                                data.setDataSet(pieDataSet);
                                data.setValueTextSize(12f);
                                data.setValueFormatter(new PercentFormatter(pieChart));
                                pieDataSet.setColors(color());

                                Legend legend = pieChart.getLegend();
                                legend.setEnabled(true);
                                legend.setForm(Legend.LegendForm.SQUARE);
                                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                                legend.setWordWrapEnabled(true);
                                legend.setTextSize(15f);

                                pieChart.setHoleColor(Color.parseColor("#CCC7C7"));
                                pieChart.setData(data);
                                pieChart.animateXY(2000, 2000);
                                pieChart.setUsePercentValues(true);
                                pieChart.getDescription().setEnabled(false);
                                pieChart.invalidate();
                            }
                        },
                        throwable -> {
                            Log.d("log1", throwable.getMessage());
                        }
                ));
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        pieChart = findViewById(R.id.piechart);
    }
    private List<Integer> color() {
        List<Integer> customColors = new ArrayList<>();

        customColors.add(Color.RED);
        customColors.add(Color.BLUE);
        customColors.add(Color.GREEN);
        customColors.add(Color.YELLOW);

        customColors.add(Color.parseColor("#FFFF9800"));
        customColors.add(Color.parseColor("#FFB73EE3"));
        customColors.add(Color.parseColor("#FF1ED8C7"));
        customColors.add(Color.parseColor("#8BC51F"));
        customColors.add(Color.parseColor("#FFE91E63"));
        customColors.add(Color.parseColor("#FF5871FA"));

        return customColors;
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