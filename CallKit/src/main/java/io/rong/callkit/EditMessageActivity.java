package io.rong.callkit;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fansonlib.base.BaseActivity;
import com.example.fansonlib.function.imagepicker.ImagePicker;
import com.example.fansonlib.function.imagepicker.bean.ImageItem;
import com.example.fansonlib.function.imagepicker.ui.ImageGridActivity;
import com.example.fansonlib.function.imagepicker.view.CropImageView;

import java.util.ArrayList;

/**
 * 编辑消息的 Activity
 */
public class EditMessageActivity extends BaseActivity {
    public static final int IMAGE_PICKER = 100; //打开图片选择界面requestCode
    public static final int RESULT_TEXT = 1111;
    public static final int RESULT_PIC = 1112;
    private TextView mTvTextSend;
    private TextView mTvPicture;
    private TextView mTvCancel;
    private EditText mEtText;

    @Override
    protected int getContentView() {
        return R.layout.activity_edit_message;
    }

    @Override
    protected void initView() {
        mTvCancel = (TextView) findViewById(R.id.tv_cancel);
        mTvPicture = (TextView) findViewById(R.id.tv_pic);
        mTvTextSend = (TextView) findViewById(R.id.tv_send);
        mEtText = (EditText) findViewById(R.id.edit_input);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void listenEvent() {
        mTvTextSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("text",mEtText.getText().toString());
                setResult(RESULT_TEXT,data);
                finish();
            }
        });

        mTvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initImagePicker();
                Intent intent = new Intent(EditMessageActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
            }
        });

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ImagePicker.RESULT_CODE_ITEMS:
                if (data != null) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    for (int i = 0; i < images.size(); i++) {
                        ImageItem imageItem = images.get(i);
                        if (imageItem != null) {
                            Intent intent = new Intent();
                            intent.putExtra("picture",imageItem.path);
                            setResult(RESULT_PIC,intent);
                            finish();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 初始化控件ImagePicker
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new UILImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(false);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }
}
