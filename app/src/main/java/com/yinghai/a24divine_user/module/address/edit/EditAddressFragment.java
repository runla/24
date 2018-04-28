package com.yinghai.a24divine_user.module.address.edit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.fansonlib.http.ThreadPool.ThreadPoolManager;
import com.example.fansonlib.utils.ShowToast;
import com.google.gson.Gson;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.bean.ChooseAddressBean;
import com.yinghai.a24divine_user.databinding.FragmentEditAddressBinding;
import com.yinghai.a24divine_user.utils.GetJsonDataUtil;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 13:39
 *         Describe：编辑地址
 */

public class EditAddressFragment extends MyBaseMvpFragment<EditAddressPresenter> implements ContractEditAddress.IView {

    private FragmentEditAddressBinding mBinding;
    private boolean mSex;
    private AddressListBean.DataBean.AddressBean mAddressBean;
    private ArrayList<ChooseAddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>(); //地址选择
    private int mAddressId = 0;
    private int mType = EditAddressModel.TYPE_DEFAULE;

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.edti_address));
    }

    public static EditAddressFragment newInstance(AddressListBean.DataBean.AddressBean bean) {
        EditAddressFragment fragment = new EditAddressFragment();
        if (bean != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("BEAN", bean);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected EditAddressPresenter createPresenter() {
        return new EditAddressPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_address,container,false);
        mBinding.rbSexBoy.setChecked(true);
        return mBinding.getRoot();
    }

    @Override
    protected void initData() {
        initJsonData();
        if (getArguments() == null) {
            return;
        }
        mAddressBean = (AddressListBean.DataBean.AddressBean) getArguments().get("BEAN");
        if (mAddressBean != null) {
            mBinding.setBean(mAddressBean);
            setSex(mAddressBean.isAsSex());
        }
    }

    private void setSex(boolean sex) {
        if (sex) {
            mBinding.rbSexBoy.setChecked(true);
            mSex = true;
        } else {
            mBinding.rbSexGirl.setChecked(true);
            mSex = false;
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                if (mAddressBean != null) {
                    mAddressId = mAddressBean.getAddressId();
                    mType = EditAddressModel.TYPE_UPDATE;
                }
                //TODO 区号写死了“86”
                mPresenter.onEditAddress(mAddressId, mType, mBinding.etName.getText().toString(), mSex, "86", mBinding.etTel.getText().toString(),
                        mBinding.etAddress.getText().toString(), mBinding.etDetail.getText().toString());
            }
        });

        mBinding.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickerView();
            }
        });

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
    }

    @Override
    public void showEditAddressSuccess(AddressListBean bean) {
        ShowToast.singleShort(getString(R.string.edit_success));
        hideLoading();
    }

    @Override
    public void showEditAddressFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    private void showPickerView() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(hostActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                mBinding.etAddress.setText(tx);
            }
        })
                .setTitleText("城市选择")
                .setSubmitColor(ContextCompat.getColor(hostActivity, R.color.primary_light))
                .setCancelColor(ContextCompat.getColor(hostActivity, R.color.primary_light))
                .setDividerColor(ContextCompat.getColor(hostActivity, R.color.grey_dark))
                //设置选中项文字颜色
                .setTextColorCenter(ContextCompat.getColor(hostActivity, R.color.primary_light))
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据
        ThreadPoolManager.getThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                /**
                 * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
                 * 关键逻辑在于循环体
                 *
                 * */
                String JsonData = new GetJsonDataUtil().getJson(hostActivity, "province.json");//获取assets目录下的json文件数据
                ArrayList<ChooseAddressBean> jsonBean = parseData(JsonData);//用Gson 转成实体
                /**
                 * 添加省份数据
                 *
                 * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
                 * PickerView会通过getPickerViewText方法获取字符串显示出来。
                 */
                options1Items = jsonBean;

                for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
                    ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                    ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                    for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                        String CityName = jsonBean.get(i).getCityList().get(c).getName();
                        CityList.add(CityName);//添加城市

                        ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                        if (jsonBean.get(i).getCityList().get(c).getArea() == null
                                || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                            City_AreaList.add("");
                        } else {

                            for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                                String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                                City_AreaList.add(AreaName);//添加该城市所有地区数据
                            }
                        }
                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                    }

                    /**
                     * 添加城市数据
                     */
                    options2Items.add(CityList);

                    /**
                     * 添加地区数据
                     */
                    options3Items.add(Province_AreaList);
                }
            }
        });
    }

    public ArrayList<ChooseAddressBean> parseData(String result) {//Gson 解析
        ArrayList<ChooseAddressBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ChooseAddressBean entity = gson.fromJson(data.optJSONObject(i).toString(), ChooseAddressBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

}
