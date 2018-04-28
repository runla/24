package io.rong.callkit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fansonlib.widget.dialogfragment.base.BaseDialogFragment;
import com.example.fansonlib.widget.dialogfragment.base.Utils;
import com.example.fansonlib.widget.dialogfragment.base.ViewHolder;


/**
 * Created by chenjianrun on 2017/10/23.
 * 描述：简单输入对话框
 */

public class MyInputDialog extends BaseDialogFragment {
    public static final String CONTENT_PARAM = "InputContent";
    private String content;

    private EditText mEditInput;
    private TextView mTvSend;
    private TextView mTvPicture;

    private OnTextSendListener mOnTextSendListener;
    // 系统软键盘
    private InputMethodManager mInputMethodManager;
    @Override
    public int intLayoutId() {
        return R.layout.dialog_input;
    }

    /**
     * 创建对话框实例
     * @return
     */
    public static MyInputDialog newInstance(){
        MyInputDialog dialog = new MyInputDialog();
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowBottom(true);
        setOutCancel(true);
        if (savedInstanceState!=null){
            content = savedInstanceState.getString(CONTENT_PARAM);
        }
    }

    @Override
    public void convertView(ViewHolder holder, BaseDialogFragment dialog) {
        mEditInput = holder.getView(R.id.edit_input);
        mTvSend = holder.getView(R.id.tv_send);
        mTvPicture = holder.getView(R.id.tv_pic);

        // 弹出软键盘
        mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        // 发送按钮点击事件
        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTextSendListener != null) {
                    mOnTextSendListener.onTextSend(mEditInput.getText().toString().trim(),false);
                }
                mEditInput.setText("");
//                dismiss();
//                mInputMethodManager.hideSoftInputFromWindow(mEditInput.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTextSendListener != null) {
                    mOnTextSendListener.onTextSend(mEditInput.getText().toString().trim(),false);
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CONTENT_PARAM,mEditInput.getText().toString().trim());
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = Utils.getScreenWidth(getContext());
            window.setAttributes(lp);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        KeyBoardCancle();
//        hintKeyboard();
//        mInputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_IMPLICIT_ONLY);
//        mInputMethodManager.hideSoftInputFromWindow(mEditInput.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void hintKeyboard() {
        //切换软键盘的显示与隐藏
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Activity activity = (Activity) getContext();
        imm.toggleSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
        if(imm.isActive()&&activity.getCurrentFocus()!=null){
            if (activity.getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_HIDDEN);
            }
        }
    }

   // 强制隐藏软键盘
    public void KeyBoardCancle() {
        Activity activity = (Activity) getContext();
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 设置发送内容的结果回调
     * @param mOnTextSendListener
     */
    public MyInputDialog setmOnTextSendListener(OnTextSendListener mOnTextSendListener) {
        this.mOnTextSendListener = mOnTextSendListener;
        return this;
    }

    public interface OnTextSendListener {
        /**
         *
         * @param msg
         * @param askOpen   提问开关
         */
        void onTextSend(String msg, boolean askOpen);
    }
}
