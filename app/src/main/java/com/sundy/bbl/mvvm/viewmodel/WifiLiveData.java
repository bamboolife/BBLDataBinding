package com.sundy.bbl.mvvm.viewmodel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.lang.ref.WeakReference;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-31 20:39
 * 描述：检测wifi信号强度
 */
public class WifiLiveData extends LiveData<Integer> {
    private static final String TAG = "WifiLiveData";
    private volatile static WifiLiveData mWifiLiveData;
    private WeakReference<Context> mContextWeakReference;

    public static WifiLiveData getInstance(Context context) {
        if (mWifiLiveData == null) {
            synchronized (WifiLiveData.class) {
                if (mWifiLiveData == null) {
                    mWifiLiveData = new WifiLiveData(context);
                }
            }
        }
        return mWifiLiveData;
    }

    private WifiLiveData(Context context) {
        mContextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected void onActive() {
        super.onActive();
        registerReceiver();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
       unregisterReceiver();
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        mContextWeakReference.get().registerReceiver(mReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        mContextWeakReference.get().unregisterReceiver(mReceiver);
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "action = " + action);
            if (WifiManager.RSSI_CHANGED_ACTION.equals(action)) {
                int wifiRssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI, -200);
                int wifiLevel = WifiManager.calculateSignalLevel(
                        wifiRssi, 4);
                mWifiLiveData.setValue(wifiLevel);
            }
        }
    };
}
