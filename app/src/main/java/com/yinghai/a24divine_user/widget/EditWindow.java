package com.yinghai.a24divine_user.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.base.BaseDialogFragment;
import com.example.fansonlib.widget.dialogfragment.base.ViewHolder;
import com.yinghai.a24divine_user.R;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 10:26
 *         Describe：输入框的Window
 */

public class EditWindow extends BaseDialogFragment {

    //默认的提示语
    private String mHintContent = null;
    //初始内容
    private String mInitContent = null;
    private EditText mEtContent;
    private IEditListener mListener;

    public void setListener(IEditListener listener) {
        mListener = listener;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public EditWindow(){
        setAnimStyle(R.style.BottomEnterAnimation);
    }

    @Override
    public int intLayoutId() {
        return R.layout.window_edit;
    }

    @Override
    public void convertView(ViewHolder viewHolder, BaseDialogFragment baseDialogFragment) {
        mEtContent = viewHolder.getView(R.id.et_content);
        if (mHintContent != null) {
            mEtContent.setHint(mHintContent);
        }
        if (mInitContent != null) {
            mEtContent.setText(mInitContent);
        }
        viewHolder.setOnClickListener(R.id.btn_publish, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEtContent.getText().toString().length() == 0) {
                    ShowToast.singleShort(getContext().getString(R.string.input_is_null));
                } else {
                    mListener.getEditContent(mEtContent.getText().toString());
                }
            }
        });
    }



    /**
     * 设置默认数据
     *
     * @param content
     */
    public void setEtContent(String content) {
        mInitContent = content;
    }

    /**
     * 设置默认提示语
     *
     * @param content
     */
    public void setHintContent(String content) {
        mHintContent = content;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mListener = null;
    }

    /**
     * 监听输入的内容接口
     */
    public interface IEditListener {
        /**
         * 获取到输入的内容
         *
         * @param content 内容
         */
        void getEditContent(String content);
    }
}
