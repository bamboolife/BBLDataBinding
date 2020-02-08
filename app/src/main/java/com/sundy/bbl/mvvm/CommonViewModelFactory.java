//package com.sundy.bbl.mvvm;
//
//import android.annotation.SuppressLint;
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.ViewModel;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.sundy.bbl.mvvm.model.CommonBean;
//import com.sundy.bbl.mvvm.viewmodel.CommonViewModel;
//
//import java.lang.reflect.InvocationTargetException;
//
//public class CommonViewModelFactory extends ViewModelProvider.NewInstanceFactory {
//
//    @SuppressLint("StaticFieldLeak")
//    private static volatile CommonViewModelFactory INSTANCE;
//    private final Application mApplication;
//
//    public static CommonViewModelFactory getInstance(Application application) {
//        if (INSTANCE == null) {
//            synchronized (CommonViewModelFactory.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new CommonViewModelFactory(application);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//    private CommonViewModelFactory(Application application) {
//        this.mApplication = application;
//    }
//
//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        //  if (HotViewModel.class.isAssignableFrom(modelClass)) {
//        //noinspection TryWithIdenticalCatches
//        try {
//            return modelClass.getConstructor(Application.class, CommonBean.class).newInstance(mApplication,new CommonBean(mApplication));
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
//        }
//        // }
//    }
//}
