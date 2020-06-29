package com.zy.mytest.gps;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zy.mytest.R;
import com.zy.mytest.bean.GpsPoint;
import com.zy.mytest.utils.ObjectBox;

import io.objectbox.Box;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class GpsSaveActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mGpsText;
    private LocationManager mLocationManager;
    private LocationListener locationListener;
    private Location mLocation = null;
    private EditText mPointDesc;
    private Box<GpsPoint> mPointBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_save);
        mGpsText = findViewById(R.id.gpsText);
        mPointDesc = findViewById(R.id.pointDesc);
        findViewById(R.id.pointBtn).setOnClickListener(this);
        findViewById(R.id.lineStartBtn).setOnClickListener(this);
        findViewById(R.id.lineEndBtn).setOnClickListener(this);
        init();
    }

    private void init() {
        mPointBox = ObjectBox.get().boxFor(GpsPoint.class);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //获取位置变化结果
                float accuracy = location.getAccuracy();//精确度，以密为单位
                double altitude = location.getAltitude();//获取海拔高度
                double longitude = location.getLongitude();//经度
                double latitude = location.getLatitude();//纬度
                float speed = location.getSpeed();//速度
                long time = location.getTime();//时间
                mLocation = location;
                mGpsText.setText("精确度：" + accuracy + "\n"
                        + "海拔：" + altitude + "\n"
                        + "经度：" + longitude + "\n"
                        + "维度：" + latitude + "\n"
                        + "速度：" + speed + "\n"
                        + "时间：" + time);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        requestLocation();
        showToast("requestLocation");
    }

    public static final int request_location_code = 100;

    @AfterPermissionGranted(request_location_code)
    private void requestLocation() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            showToast("hasPermissions");
            mLocationManager.requestLocationUpdates("gps", 0, 0, locationListener);
        } else {
            EasyPermissions.requestPermissions(this, "需要定位权限才能使用该功能",
                    request_location_code, perms);
            showToast("requestPermissions");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //将结果转发给 EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pointBtn:
                savePoint();
                break;
            case R.id.lineStartBtn:
                startSaveLine();
                break;
            case R.id.lineEndBtn:
                stopSaveLine();
                break;
        }
    }

    private void savePoint() {
        if (mLocation != null) {
            String desc = mPointDesc.getText().toString();
            if (TextUtils.isEmpty(desc)) {
                showToast("请输入点的含义或描述");
            } else {
                GpsPoint point = new GpsPoint();
                point.desc = desc;
                point.accuracy = mLocation.getAccuracy();//精确度，以密为单位
                point.altitude = mLocation.getAltitude();//获取海拔高度
                point.longitude = mLocation.getLongitude();//经度
                point.latitude = mLocation.getLatitude();//纬度
                point.speed = mLocation.getSpeed();//速度
                point.time = mLocation.getTime();//时间
                mPointBox.put(point);
                showToast("保存成功");
            }
        }else {
            showToast("定位不成功");
        }
    }


    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void startSaveLine() {

    }

    private void stopSaveLine() {

    }


}
