package com.sundy.bbl.fgt;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sundy.bbl.R;
import com.sundy.bbl.mvvm.NameViewModel;
import com.sundy.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-31 17:47
 * 描述：liveData测试类
 */
public class LiveDataFragment extends Fragment {
    private static final String TAG = "LiveDataFragment";
    private NameViewModel mNameViewModel;
    TextView mTextView;

    public static LiveDataFragment getInstance(){
        return new LiveDataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.bbl_livedata_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView=view.findViewById(R.id.textView);
        ViewModelProvider.AndroidViewModelFactory factory=new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication());
        mNameViewModel= new ViewModelProvider(this,factory).get(NameViewModel.class);
        mNameViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextView.setText(s);
            }
        });
        mNameViewModel.getNameListData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                for (String item : strings) {
                    Log.d(TAG, "name: " + item);
                }
            }
        });
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameViewModel.getCurrentName().setValue("Jane");
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> nameList = new ArrayList<>();
                for (int i = 0; i < 10; i++){
                    nameList.add("Jane<" + i + ">");
                }
                mNameViewModel.getNameListData().setValue(nameList);
            }
        });
    }


}
