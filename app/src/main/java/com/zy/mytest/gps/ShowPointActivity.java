package com.zy.mytest.gps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zy.mytest.R;
import com.zy.mytest.bean.GpsPoint;
import com.zy.mytest.utils.ObjectBox;

import java.util.List;

import io.objectbox.Box;

public class ShowPointActivity extends AppCompatActivity {

    private RecyclerView mShowPointView;
    private Box<GpsPoint> mGpsPointBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_point);
        mShowPointView = findViewById(R.id.pointList);
        initView();
    }

    private void initView() {
        mGpsPointBox = ObjectBox.get().boxFor(GpsPoint.class);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ShowPointAdapter showPointAdapter = new ShowPointAdapter(R.layout.show_point_adapter_layout);
        mShowPointView.setLayoutManager(manager);
        mShowPointView.setAdapter(showPointAdapter);
        showPointAdapter.setList(getListData());
    }

    private List<GpsPoint> getListData() {
        return mGpsPointBox.getAll();
    }

}
