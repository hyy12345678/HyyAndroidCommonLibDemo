package com.hyy.android.common.manager;

import android.content.Context;
import android.content.Intent;

import com.hyy.android.common.camera.CameraActivity;

public class IDCameraManager {

    private static IDCameraManager instance;

    private IDCameraInterface mIDCameraInterface;

    private Context mContext;


    private IDCameraManager(Context context) {

        this.mContext = context;

    }

    public static IDCameraManager getInstance(Context context) {
        if(null == instance){
            instance = new IDCameraManager(context);
        }

        return instance;
    }

    public void takeIdCardPhoto(String CONTENT_TYPE, String OUTPUT_FILE_PATH, IDCameraInterface IDCameraInterface) {

        this.mIDCameraInterface = IDCameraInterface;

        Intent intent = new Intent(mContext, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,OUTPUT_FILE_PATH);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CONTENT_TYPE);
        mContext.startActivity(intent);

    }

    public IDCameraInterface getmIDCameraInterface() {
        return mIDCameraInterface;
    }

    public void setmIDCameraInterface(IDCameraInterface mIDCameraInterface) {
        this.mIDCameraInterface = mIDCameraInterface;
    }
}
