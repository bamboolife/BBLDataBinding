package com.sundy.common.event;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 原子操作
 * 一个生命周期感知的观察对象，在订阅后只发送新的更新，用于类似事件
 * 导航和Snackbar消息。
 * < p >
 * 这避免了事件的一个常见问题:关于配置更改(如旋转)的更新
 * 如果观察者是活动的，则可以发出。LiveData只在有一个可观察对象时才调用
 * 显式调用setValue()或call()。
 * < p >
 * 注意，只有一个观察者将被通知更改。
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private static final String TAG = "SingleLiveEvent";

    private final AtomicBoolean mPending = new AtomicBoolean(false);


    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        super.observe(owner, t -> {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T t) {
        mPending.set(true);
        super.setValue(t);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    public void call() {
        setValue(null);
    }
}
