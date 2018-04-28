package com.yinghai.a24divine_user.module.setting;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.fansonlib.function.imagepicker.ImagePicker;
import com.example.fansonlib.function.imagepicker.bean.ImageItem;
import com.example.fansonlib.function.imagepicker.ui.ImageGridActivity;
import com.example.fansonlib.function.imagepicker.view.CropImageView;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.DoubleDialog;
import com.example.fansonlib.widget.dialogfragment.base.IConfirmListener;
import com.yinghai.a24divine_user.Impl.UILImageLoader;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.bean.UploadPictureBean;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.databinding.FragmentSettingBinding;
import com.yinghai.a24divine_user.module.address.AddressManagerActivity;
import com.yinghai.a24divine_user.module.login.LoginActivity;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.login.state.LogoutState;
import com.yinghai.a24divine_user.module.setting.person.ContractEditPerson;
import com.yinghai.a24divine_user.module.setting.person.EditPersonPresenter;
import com.yinghai.a24divine_user.rongIm.IMLogin;
import com.yinghai.a24divine_user.utils.ConstellationUtils;
import com.yinghai.a24divine_user.widget.ConstellationWindow;
import com.yinghai.a24divine_user.widget.EditWindow;
import com.yinghai.a24divine_user.widget.PickDatePopuWindow;

import java.util.ArrayList;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 18:21
 *         Describe：个人设置Fragment
 */

public class SettingFragment extends MyBaseMvpFragment<EditPersonPresenter> implements PickDatePopuWindow.IPickTimeListener, ConstellationWindow.IConstellationListener,
        ContractEditPerson.IView {

    private static final String TAG = SettingFragment.class.getSimpleName();
    private FragmentSettingBinding mBinding;
    /**
     * 打开图片选择界面requestCode
     */
    public static final int IMAGE_PICKER = 100;
    private PickDatePopuWindow mPickDatePopuWindow;
    private boolean mSex;
    private String mPhotoUrl = null;
    private EditWindow mEditWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.setting));
    }

    @Override
    protected void initData() {
        mBinding.tvUserName.setText(SharePreferenceHelper.getString(ConstantPreference.S_USER_NAME, null));
        mBinding.tvTel.setText(SharePreferenceHelper.getString(ConstantPreference.S_PHONE, getString(R.string.not_set)));

        ImageLoaderUtils.loadCircleImage(hostActivity, mBinding.ivUserPhoto, ConHttp.BASE_URL + SharePreferenceHelper.getString(ConstantPreference.S_USER_PHOTO, null));
        setSex();
        setBirthDay();
        setConstellation();
    }

    private void setSex() {
        if (SharePreferenceHelper.getBoolean(ConstantPreference.B_USER_SEX, true)) {
            mBinding.rbSexBoy.setChecked(true);
            mSex = true;
        } else {
            mBinding.rbSexGirl.setChecked(true);
            mSex = false;
        }
    }

    private void setBirthDay() {
        String birthday;
        if (SharePreferenceHelper.getString(ConstantPreference.S_USER_BIRTHDAY, null) == null) {
            birthday = getString(R.string.not_set);
        } else {
            birthday = SharePreferenceHelper.getString(ConstantPreference.S_USER_BIRTHDAY, null);
        }
        mBinding.tvBirthday.setText(birthday.split(" ")[0]);
    }

    private void setConstellation() {
        String constellation;
        if (SharePreferenceHelper.getInt(ConstantPreference.I_USER_CONSTELLATION, 0) == 0) {
            constellation = getString(R.string.not_set);
        } else {
            constellation = ConstellationUtils.getString(SharePreferenceHelper.getInt(ConstantPreference.I_USER_CONSTELLATION, 0));
        }
        mBinding.tvConstellation.setText(constellation);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBinding.rgUserSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_sex_boy:
                        mSex = true;
                        break;
                    case R.id.rb_sex_girl:
                        mSex = false;
                        break;
                    default:
                        break;
                }
            }
        });

        mBinding.linearBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPickDatePopuWindow = new PickDatePopuWindow(hostActivity);
                mPickDatePopuWindow.setPickTimeListener(SettingFragment.this);
                mPickDatePopuWindow.showPopupWindow();
            }
        });

        mBinding.tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditWindow == null) {
                    mEditWindow = new EditWindow();
                }
                mEditWindow.setListener(new EditWindow.IEditListener() {
                    @Override
                    public void getEditContent(String content) {
                        mBinding.tvUserName.setText(content);
                        mEditWindow.dismiss();
                    }
                });
                mEditWindow.setEtContent(mBinding.tvUserName.getText().toString());
                mEditWindow.setOutCancel(true).show(getFragmentManager());
            }
        });

        mBinding.linearAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMyActivity(AddressManagerActivity.class);
            }
        });

        mBinding.tvConstellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConstellationWindow(hostActivity, SettingFragment.this).showPopupWindow();
            }
        });

        mBinding.linearHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoubleDialog.newInstance(getString(R.string.whether_to_replace_head)).setConfirmListener(new IConfirmListener() {
                    @Override
                    public void onConfirm() {
                        initImagePicker();
                        Intent intent = new Intent(hostActivity, ImageGridActivity.class);
                        startActivityForResult(intent, IMAGE_PICKER);
                    }
                }).show(getFragmentManager());
            }
        });

        mBinding.llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoubleDialog.newInstance(getString(R.string.sure_logout)).setConfirmListener(new IConfirmListener() {
                    @Override
                    public void onConfirm() {
                        showLoading();
                        mPresenter.logout();
                    }
                }).show(getFragmentManager());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ImagePicker.RESULT_CODE_ITEMS:
                showPicture(data);
                break;
            default:
                break;
        }
    }

    /**
     * 选择好图片后显示
     */
    private void showPicture(Intent data) {
        if (data != null) {
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            for (int i = 0; i < images.size(); i++) {
                ImageItem imageItem = images.get(i);
                if (imageItem != null) {
                    showLoading();
                    mPhotoUrl = imageItem.path;
                    mPresenter.uploadPicture(2, mPhotoUrl);
                }
            }
        }
    }

    /**
     * 初始化控件ImagePicker
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new UILImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    /**
     * 选择生日后，回调数据返回
     */
    @Override
    public void onPickTime(String year, String month, String day) {
        mBinding.tvBirthday.setText(year + "-" + month + "-" + day);
    }

    @Override
    public void getConstellation(String constellation) {
        mBinding.tvConstellation.setText(constellation);
    }

    @Override
    protected EditPersonPresenter createPresenter() {
        return new EditPersonPresenter(this);
    }

    /**
     * 更新个人信息
     */
    public void updatePersonInfo() {
        showLoading();
        mPresenter.editPerson(mBinding.tvUserName.getText().toString(), mBinding.tvBirthday.getText().toString(),
                ConstellationUtils.getCode(mBinding.tvConstellation.getText().toString()),
                mSex, mPhotoUrl);
    }

    @Override
    public void showEditPersonSuccess(PersonInfoBean.DataBean bean) {
        ShowToast.singleShort(getString(R.string.update_success));
        mMultiFragmentListener.onMultiFragment(ConFragment.INFO_CHANGE, bean.getTfUser().getUNick());
        hideLoading();
    }

    @Override
    public void showEditPersonFailure(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void showUploadSuccess(UploadPictureBean.DataBean bean) {
        ImageLoaderUtils.loadCircleImage(hostActivity, mBinding.ivUserPhoto, mPhotoUrl);
        mMultiFragmentListener.onMultiFragment(ConFragment.PHOTO_CHANGE, bean.getImgUrl());
        hideLoading();
    }


    @Override
    public void showUploadFailure(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void showLogoutSuccess() {
        //状态模式，设置为已登出
        LoginStateManager.getInstance().setState(new LogoutState());
        SharePreferenceHelper.clear();
        // IM 设置登出
        IMLogin.logout();
        hideLoading();
        startMyActivity(LoginActivity.class);
        hostActivity.finish();
    }

    @Override
    public void showLogoutFailure(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPickDatePopuWindow != null) {
            mPickDatePopuWindow.dismiss();
            mPickDatePopuWindow = null;
        }
    }
}
