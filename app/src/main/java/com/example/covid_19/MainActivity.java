package com.example.covid_19;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19.Models.WorldStat;
import com.example.covid_19.Retrofit.ICovidApiClient;
import com.example.covid_19.Retrofit.RetrofitClient;
import com.example.covid_19.Util.Constanst;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.covid_19.Util.ConvertUtil.ReplaceAllStringToInt;

public class MainActivity extends AppCompatActivity {

    ICovidApiClient iCovidApiClient;
    CompositeDisposable compositeDisposable;
    BarChart chart;

    TextView txtCase, txtRecovered, txtTodayCasesColor, txtDeaths, txtTodayDeathsColor, txtCriticalColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        init();
        loadDataWorldStat(mWifi);
        setupChart();

    }

    private void loadDataWorldStat(NetworkInfo mWifi) {
        if (mWifi.isConnected()) {
            Disposable disposable = iCovidApiClient.getAllWorldStat().subscribeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<WorldStat>() {
                        @Override
                        public void accept(WorldStat worldStat) throws Exception {
                            onGetAllWorldStat(worldStat);
                        }
                    });
            compositeDisposable.add(disposable);
        }
    }

    private void onGetAllWorldStat(WorldStat worldStats) {
        txtCase.setText(worldStats.getTotal_cases() + " tổng ca nhiễm");
        txtRecovered.setText(worldStats.getTotal_recovered()  + " ca hồi phục");
        txtTodayCasesColor.setText(worldStats.getNew_cases() + " ca mới nhiễm");
        txtDeaths.setText(worldStats.getTotal_deaths() + " tổng ca tử vong");
        txtTodayDeathsColor.setText(worldStats.getNew_deaths()+ " tử vong hôm này");
        txtCriticalColor.setText(worldStats.getSerious_critical() + " ca nghiêm trọng");

        setData(ReplaceAllStringToInt(worldStats.getTotal_cases()),
                ReplaceAllStringToInt(worldStats.getNew_cases()),
                ReplaceAllStringToInt(worldStats.getTotal_deaths()),
                ReplaceAllStringToInt(worldStats.getNew_deaths()),
                ReplaceAllStringToInt(worldStats.getTotal_recovered()),
                ReplaceAllStringToInt(worldStats.getSerious_critical()));
    }

    public void setData(int Cases, int TodayCases, int Deaths, int TodayDeaths, int Recovered, int Critical) {
        String[] mParties = new String[]{"Cases", "Today Cases", "Deaths", "Today Deaths", "Recovered", "Critical"};
        BarDataSet set1;

        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, Cases, mParties[0]));
        values.add(new BarEntry(1, TodayCases, mParties[1]));
        values.add(new BarEntry(2, Deaths, mParties[2]));
        values.add(new BarEntry(3, TodayDeaths, mParties[3]));
        values.add(new BarEntry(4, Recovered, mParties[4]));
        values.add(new BarEntry(5, Critical, mParties[5]));

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Horizontal Chart");
            set1.setDrawIcons(false);
            ArrayList<Integer> colors = new ArrayList<Integer>();
            colors.add(getResources().getColor(R.color.CasesColor));
            colors.add(getResources().getColor(R.color.TodayCasesColor));
            colors.add(getResources().getColor(R.color.DeathsColor));
            colors.add(getResources().getColor(R.color.TodayDeathsColor));
            colors.add(getResources().getColor(R.color.RecoveredColor));
            colors.add(getResources().getColor(R.color.CriticalColor));
            set1.setColors(colors);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            chart.setData(data);
            chart.setNoDataTextColor(Color.BLACK);
            data.setValueTextColor(Color.BLACK);
            chart.getLegend().setTextColor(Color.BLACK);
        }
    }

    private void setupChart() {
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);
        YAxis yl = chart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f);
        YAxis yr = chart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        chart.setFitBars(true);
        chart.animateY(2500);
    }

    private void init() {
        compositeDisposable = new CompositeDisposable();
        iCovidApiClient = RetrofitClient.getInstance(Constanst.BASE_URL).create(ICovidApiClient.class);
        chart = (BarChart) findViewById(R.id.chart1);

        txtCase = findViewById(R.id.txtCase);
        txtRecovered = findViewById(R.id.txtRecovered);
        txtTodayCasesColor = findViewById(R.id.txtTodayCasesColor);
        txtDeaths = findViewById(R.id.txtDeaths);
        txtTodayDeathsColor = findViewById(R.id.txtTodayDeathsColor);
        txtCriticalColor = findViewById(R.id.txtCriticalColor);
    }
}
