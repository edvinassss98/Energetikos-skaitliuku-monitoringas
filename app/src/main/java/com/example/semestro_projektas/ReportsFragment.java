package com.example.semestro_projektas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReportsFragment extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reports);
        BarChart barChart = (BarChart) findViewById(R.id.bargraph); // tried before getview

        Bundle bundle = getIntent().getExtras();


        String id = bundle.getString("id");
        String geguze = bundle.getString("geguze");
        String balandis = bundle.getString("balandis");
        String kovas = bundle.getString("kovas");
        String sausis = bundle.getString("sausis");
        String vasaris = bundle.getString("vasaris");




        float geguze1 = Float.parseFloat(geguze);
        float balandis1 = Float.parseFloat(balandis);
        float kovas1 = Float.parseFloat(kovas);
        float sausis1 = Float.parseFloat(sausis);
        float vasaris1 = Float.parseFloat(vasaris);


        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
        bargroup1.add(new BarEntry(0f, sausis1));
        bargroup1.add(new BarEntry(1f, vasaris1));
        bargroup1.add(new BarEntry(2f, kovas1));
        bargroup1.add(new BarEntry(3f, balandis1));
        bargroup1.add(new BarEntry(4f, geguze1));

        BarDataSet barDataSet1 = new BarDataSet(bargroup1, id+" Skaitliuko ataskaita");

        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList xValues = new ArrayList();
        xValues.add("Sausis");
        xValues.add("Vasaris");
        xValues.add("Kovas");
        xValues.add("Balandis");
        xValues.add("Geguze");

        ArrayList<BarDataSet> dataSets = new ArrayList<>();  // combined all dataset into an arraylist
        dataSets.add(barDataSet1);

        XAxis xAxis1 = barChart.getXAxis();
        xAxis1.setGranularityEnabled(true);

        xAxis1.setDrawGridLines(false);
        xAxis1.setAxisMaximum(6);
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis1.setValueFormatter((new IndexAxisValueFormatter(xValues)));


        BarData data = new BarData(barDataSet1);
        barChart.setData(data);
        barChart.setScaleEnabled(false);

    }


}
