package com.sundy.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-19 12:53
 * 描述：binding adapter 基类
 */
public abstract class BaseQuickAdapter<M, DB extends ViewDataBinding> extends RecyclerView.Adapter<BaseBindViewHolder> {
    private Context mContext;
    List<M> datas;
    OnItemClickListener mOnItemClickListener;
    public BaseQuickAdapter() {

    }

    public BaseQuickAdapter(@NonNull List<M> datas) {
        this.datas = datas;
    }
    @NonNull
    @Override
    public BaseBindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        DB binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), onBindLayout(viewType), parent, false);
        return new BaseBindViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindViewHolder holder, int position) {
        DB binding = DataBindingUtil.getBinding(holder.itemView);
        if (mOnItemClickListener != null) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(BaseQuickAdapter.this, v, position);
                }
            });

        }
        onBindItem(binding, datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 绑定item
     * @param binding
     * @param item
     */
    protected abstract void onBindItem(DB binding, M item);

    /**
     * 绑定item视图
     * @param viewType
     * @return
     */
    protected abstract @LayoutRes int onBindLayout(int viewType);
    /**
     * 设置新的数据源
     *
     * @param data
     */
    public void setNewData(@Nullable List<M> data) {
        this.datas = data == null ? new ArrayList<>() : data;
        notifyDataSetChanged();
    }

    /**
     * 在指定的位置添加一条数据
     *
     * @param position
     * @param data
     */
    public void addData(@IntRange(from = 0) int position, @NonNull M data) {
        datas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 在末尾添加一条数据
     */
    public void addData(@NonNull M data) {
        datas.add(data);
        notifyItemInserted(datas.size());
    }
    /**
     * 在末尾添加集合
     */
    public void addDatas(@NonNull Collection<? extends M> newData) {
       if (datas==null){
           datas=new ArrayList<>();
       }
        datas.addAll(newData);
        notifyItemRangeInserted(datas.size(), newData.size());

    }
    /**
     * 移除指定位置的数据
     *
     * @param position
     */
    public void remove(@IntRange(from = 0) int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 改变指定位置的数据
     */
    public void setData(@IntRange(from = 0) int index, @NonNull M data) {
        datas.set(index, data);
        notifyItemChanged(index);
    }

    /**
     * 在指定的位置添加集合
     *
     * @param position
     * @param newData
     */
    public void addDatas(@IntRange(from = 0) int position, @NonNull Collection<? extends M> newData) {
        datas.addAll(position, newData);
        notifyItemRangeInserted(position, newData.size());

    }

    @NonNull
    public List<M> getDatas() {
        return datas;
    }

    @NonNull
    public M getItem(@IntRange(from = 0) int position) {
        if (position >= 0 && position < datas.size()) {
            return datas.get(position);
        }
        return null;
    }
    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(BaseQuickAdapter adapter, View view, int position);
    }
}
