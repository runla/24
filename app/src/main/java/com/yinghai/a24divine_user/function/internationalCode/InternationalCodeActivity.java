package com.yinghai.a24divine_user.function.internationalCode;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.github.promeg.pinyinhelper.Pinyin;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.callback.OnAdapterListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/8 9:34
 *         Describe：
 */

public class InternationalCodeActivity extends AppCompatActivity implements OnAdapterListener {

    private RecyclerView mRecyclerView;
    private List<AreaCode> mCodeList;
    private List<String> mIndexList;
    public static final String AREA_CODE = "CODE";
    public static final int RESULT_CODE = 243;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_code);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        IndexView indexView = (IndexView) findViewById(R.id.index_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManagerWithSmoothScroller(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        AreaCodeGroupDecoration groupDecoration = new AreaCodeGroupDecoration(100, 0xffF1F4F6, 32, 0xffFF5252);
        //实现group显示
        mRecyclerView.addItemDecoration(groupDecoration);
        //实现分割线显示
        mRecyclerView.addItemDecoration(new AreaCodeDividerDecoration(new ColorDrawable(ContextCompat.getColor(this, R.color.grey_dark)), 24, 24));

        mCodeList = getAreaCodeList();
        indexView.setIndexList(mIndexList);
        AreaCodeAdapter areaCodeAdapter = new AreaCodeAdapter(mCodeList);
        areaCodeAdapter.setmOnAdapterListener(this);
        mRecyclerView.setAdapter(areaCodeAdapter);

        indexView.setOnSelectedListener(new IndexView.OnSelectedListener() {
            @Override
            public void onSelected(String index) {
                selectedIndex(index);
            }
        });
    }

    /**
     * 搜索
     * @param name
     * @return
     */
    private int searchGroupIndex(String name) {
        int index = 0;
        for (int i = 0; i < mIndexList.size(); i++) {
            if (mIndexList.get(i).equals(name)) {
                index = i;
            }
        }
        return index + 1;
    }

    private void selectedIndex(String index) {
        int position = -1;
        for (int i = 0; i < mCodeList.size(); i++) {
            if (mCodeList.get(i).getGroupName().equals(index)) {
                position = i ;
                break;
            }
        }

        if (-1 == position) {
            return;
        }
        mRecyclerView.smoothScrollToPosition(position);
    }


    private List<AreaCode> getAreaCodeList() {
        ArrayList<AreaCode> list = new ArrayList<>();
        String[] areaCodeList = getResources().getStringArray(R.array.area_code);

        for (int i = 0; i < areaCodeList.length; i++) {
            String[] info = areaCodeList[i].split(" ");
            if (0 == i) {
                list.add(new AreaCode(info[0], info[1]));
            } else {
                list.add(new AreaCode(info[0], info[1]));
            }
        }

        //按照字母排序，中文转换为拼音后按字母排序
        Collections.sort(list, new Comparator<AreaCode>() {
            @Override
            public int compare(AreaCode o1, AreaCode o2) {
                if (Pinyin.isChinese(o1.getArea().charAt(0))) {
                    return Pinyin.toPinyin(o1.getArea().charAt(0)).compareTo(Pinyin.toPinyin(o2.getArea().charAt(0)));
                } else {
                    return o1.getArea().compareTo(o2.getArea());
                }
            }
        });

        //填充groupName
        mIndexList = new ArrayList<>();
        String groupName;
        String oldName = null;
        for (int j = 0; j < list.size(); j++) {
            AreaCode code = list.get(j);
            if (Pinyin.isChinese(code.getArea().charAt(0))) {
                groupName = String.valueOf(Pinyin.toPinyin(code.getArea().charAt(0)).charAt(0));
            } else {
                groupName = code.getArea().substring(0, 1);
            }

            code.setGroupName(groupName);
            if (!TextUtils.equals(groupName, oldName)) {
                mIndexList.add(groupName);
                code.setTop(true);
            }
            oldName = groupName;
        }
        return list;
    }

    /**
     * 选择好区号的返回
     * @param object 序号/Model
     */
    @Override
    public void clickItem(Object... object) {
        Intent intent = new Intent();
        intent.putExtra(AREA_CODE, (String) object[0]);
        setResult(RESULT_CODE,intent);
        this.finish();
        overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
    }
}
