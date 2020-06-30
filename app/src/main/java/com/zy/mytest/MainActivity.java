package com.zy.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zy.mytest.bean.MainListBean;
import com.zy.mytest.gps.GpsSaveActivity;
import com.zy.mytest.gps.ShowPointActivity;
import com.zy.mytest.install.InstallActivity;
import com.zy.mytest.media.MediaActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        initView();
    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        MainListAdapter mainListAdapter = new MainListAdapter(R.layout.main_list_adapter_layout);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mainListAdapter);
        mainListAdapter.setList(getListData());
    }

    private List<MainListBean> getListData() {
        List<MainListBean> list = new ArrayList<>();
        list.add(new MainListBean("GPS点和路径", GpsSaveActivity.class));
        list.add(new MainListBean("GPS点展示", ShowPointActivity.class));
        list.add(new MainListBean("相机", MediaActivity.class));
        list.add(new MainListBean("应用安装", InstallActivity.class));

        return list;
    }
}
