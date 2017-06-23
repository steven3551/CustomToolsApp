package com.wuwg.app.module.compress;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.wuwg.app.R;
import com.wuwg.custom.base.BaseActivity;
import com.wuwg.custom.tools.PhoneUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by wuwengao on 2017/6/23.
 */
public class PhotoCompressActivity extends BaseActivity implements View.OnClickListener{

    private static final int REQUEST_TAKE_PICTURE = 1;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_photo_compress);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.btn_compress).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_compress:
                PhoneUtil.toTakePicture(REQUEST_TAKE_PICTURE, mActivity);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_TAKE_PICTURE:
                Bitmap bitmap = null;
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File saveFile = new File(Environment.getExternalStorageDirectory(), "终极压缩.jpg");
                compressBitmap(bitmap, saveFile);
                break;
        }
    }

    /**
     * 压缩图片
     * @param bitmap    待压缩图片的Bitmap
     * @param saveFile      压缩后的图片文件
     */
    public void compressBitmap(Bitmap bitmap, File saveFile){
//        PhotoCompressHelper.getInstance().nativeCompressBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), 20, saveFile.getAbsolutePath().getBytes());
    }
}
