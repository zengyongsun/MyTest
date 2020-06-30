package com.zy.mytest.install;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zy.mytest.R;

import static com.zy.mytest.utils.AppUtils.installApp;

public class InstallActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);
        findViewById(R.id.installApp).setOnClickListener(this);
        findViewById(R.id.btPath).setOnClickListener(this);
        tvPath = findViewById(R.id.tvPath);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.installApp:
                Log.d("test", installApp(getFilesDir().getPath() + "/test.apk") + "");
                break;
            case R.id.btPath:
                showPath();
                break;
            default:
                break;
        }
    }

    private void showPath() {
        tvPath.setText(
                "老版本"+
                "[context.getFilesDir()] ==> " + this.getFilesDir().getPath() + "\n" +
                        "[context.getFilesDir()] ==> " + this.getFilesDir().getAbsolutePath() + "\n" +
                        "[Context.getCacheDir()] ==> " + this.getCacheDir().getPath() + "\n" +
                        "[Context.getExternalFilesDir(DIRECTORY_PICTURES)] ==> " + this.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "\n" +
                        "[Context.getExternalCacheDir()] ==> " + this.getExternalCacheDir() + "\n" +
                        "[Environment.getExternalStorageDirectory()] ==> " + Environment.getExternalStorageDirectory() + "\n" +
                        "[Environment.getExternalStoragePublicDirectory()] ==> " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "\n");
    }
}
