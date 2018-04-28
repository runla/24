package com.yinghai.a24divine_user.widget;

import android.content.DialogInterface;
import android.view.KeyEvent;

import com.example.fansonlib.widget.dialogfragment.base.BaseDialogFragment;
import com.example.fansonlib.widget.dialogfragment.base.ViewHolder;
import com.wang.avi.AVLoadingIndicatorView;
import com.yinghai.a24divine_user.R;

/**
 * Created by：fanson
 * Created on：2017/10/20 13:23
 * Describe：LoadingView Dialog
 */

public class LoadingDialog extends BaseDialogFragment implements DialogInterface.OnKeyListener{

    private AVLoadingIndicatorView mLoadingView;

    public LoadingDialog(){
        this.setWidth(120);
        this.setHeight(120);
        this.setOutCancel(true);
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    public void convertView(ViewHolder holder, BaseDialogFragment baseDialogFragment) {
        mLoadingView = holder.getView(R.id.loadingView);
        mLoadingView.show();
    }



    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mLoadingView!=null){
            mLoadingView.hide();
            mLoadingView = null;
        }
    }

    @Override
    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            dismiss();
            return true;
        }else {
            return false;
        }
    }
}
