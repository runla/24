package com.yinghai.a24divine_user.function.internationalCode;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.callback.OnAdapterListener;

import java.util.List;

/**
 * adapter of area code list
 */

public class AreaCodeAdapter extends RecyclerView.Adapter<AreaCodeAdapter.ViewHolder> {

    private List<AreaCode> mAreaCodeList;
    private OnAdapterListener mOnAdapterListener;
    public void setmOnAdapterListener(OnAdapterListener listener){
        mOnAdapterListener = listener;
    }

    public AreaCodeAdapter(List<AreaCode> areaCodeList) {
        mAreaCodeList = areaCodeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area_code, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       final AreaCode areaCode = mAreaCodeList.get(position);
        holder.textArea.setText(areaCode.getArea());
        holder.textCode.setText(areaCode.getCode());
        holder.itemView.setTag(areaCode);
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAdapterListener.clickItem(areaCode.getCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAreaCodeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textArea;
        TextView textCode;
        RelativeLayout mRootView;

        public ViewHolder(View itemView) {
            super(itemView);
            textArea = (TextView) itemView.findViewById(R.id.text_area);
            textCode = (TextView) itemView.findViewById(R.id.text_code);
            mRootView = (RelativeLayout)itemView.findViewById(R.id.root_view);
        }
    }
}
