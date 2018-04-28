package com.yinghai.a24divine_user.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yinghai.a24divine_user.R;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/14 9:37
 *         Describe：购物车商品数量加减的View
 */

public class ShopAmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = ShopAmountView.class.getSimpleName();
    private int amount = 1; //购买数量
    private int goodsStorage = 1; //商品库存
    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;
    private String mCurrentEditContent; //记录当前的内容，防止多次回调
    private String mLastEditContent; //记录上一个的内容


    private OnAmountChangeListener mListener;
    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public ShopAmountView(Context context) {
        this(context, null);
    }

    public ShopAmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_shop_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.ShopAmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShopAmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShopAmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShopAmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShopAmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                setAmount(amount);
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goodsStorage) {
                amount++;
                setAmount(amount);
            }
        }

        etAmount.clearFocus();

//        if (mListener != null) {
//            mListener.onAmountChange(this, amount);
//        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mLastEditContent = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()){
            return;
        }

        //防止死循环回调
        if ((s.toString()).equals(mCurrentEditContent)){
            return;
        }

        amount = Integer.valueOf(s.toString());
        if (amount > goodsStorage) {
            setAmount(amount);
            return;
        }

        if (mListener != null) {
            mCurrentEditContent = s.toString();
            mListener.onAmountChange(this, amount);
        }
    }

    /**
     * 设置库存最大值
     * @param goodStorage
     */
    public void setGoodStorage(int goodStorage) {
        this.goodsStorage = goodStorage;
    }

    /**
     * 设置数量值
     * @param amount
     */
    public void setAmount(int amount){
        etAmount.setText(String.valueOf(amount));
        etAmount.setSelection(etAmount.getText().length());
    }

    /**
     * 获取数量值
     */
    public int getAmount( ){
        return Integer.valueOf(etAmount.getText().toString());
    }

    /**
     * 获取前一个数量值
     */
    public int getLastAmount( ){
        return Integer.valueOf(mLastEditContent);
    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }
}
