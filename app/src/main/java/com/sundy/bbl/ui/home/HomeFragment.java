package com.sundy.bbl.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sundy.bbl.R;
import com.sundy.bbl.adapter.CommonAdapter;
import com.sundy.bbl.databinding.FragmentHomeBinding;
import com.sundy.bbl.mvvm.model.CommonBean;
import com.sundy.common.base.BaseQuickAdapter;

import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;
    CommonAdapter commonAdapter;
    FragmentHomeBinding databing;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel=new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        databing = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        return databing.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        homeViewModel.getDatas().observe(getViewLifecycleOwner(), commonBeans -> {
           commonAdapter.addDatas(commonBeans);
        });
        setListener();

    }

    private void setListener() {
        commonAdapter.setOnItemClickListener((adapter, view, position) -> {
            CommonBean bean= (CommonBean) adapter.getItem(position);
            Intent intent=new Intent(getContext(),bean.getClazz());
            startActivity(intent);
        });
    }

    private void initRecyclerView() {
        commonAdapter=new CommonAdapter();
        databing.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databing.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        databing.recyclerView.setAdapter(commonAdapter);
    }
}