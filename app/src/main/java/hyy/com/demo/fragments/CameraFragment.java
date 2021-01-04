package hyy.com.demo.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hyy.android.common.camera.CameraActivity;
import com.hyy.android.common.manager.IDCameraInterface;
import com.hyy.android.common.manager.IDCameraManager;
import com.hyy.android.common.view.spinner.SpinnerItem;
import com.hyy.android.common.view.spinner.adapter.MyHintSpinnerArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hyy.com.demo.data.PepoleStateCodeMapHelper;
import hyy.com.demo.util.FileUtil;
import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment {

    private static final String TAG = CameraFragment.class.getSimpleName();

    public static final int REQUEST_CODE_CAMERA = 102;

    Button btn_camera_front;
    Button btn_camera_back;
    ImageView iv_pic;


    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment AutoExtVGFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();

        return fragment;
    }

    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        btn_camera_front = (Button) view.findViewById(R.id.btn_camera_front);
        btn_camera_back = (Button) view.findViewById(R.id.btn_camera_back);
        iv_pic = (ImageView) view.findViewById(R.id.iv_pic);


        btn_camera_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), CameraActivity.class);
//                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
//                        FileUtil.getSaveFile(getActivity()).getAbsolutePath());
//                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);

                IDCameraManager.getInstance(getActivity()).
                        takeIdCardPhoto(CameraActivity.CONTENT_TYPE_ID_CARD_FRONT,
                                FileUtil.getSaveFile(getActivity()).getAbsolutePath(),
                                new IDCameraInterface() {
                                    @Override
                                    public void onResult(String CONTENT_TYPE) {
                                        String filePath = FileUtil.getSaveFile(getActivity()).getAbsolutePath();
                                        Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                                        iv_pic.setImageBitmap(myBitmap);
                                        Log.d(TAG, "onResutl:" + CONTENT_TYPE);
                                    }

                                    @Override
                                    public void onCancel() {
                                        iv_pic.setImageBitmap(null);
                                        Log.d(TAG,"onCancel");
                                    }
                                });


            }
        });


        btn_camera_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDCameraManager.getInstance(getActivity()).
                        takeIdCardPhoto(CameraActivity.CONTENT_TYPE_ID_CARD_BACK,
                                FileUtil.getSaveFile(getActivity()).getAbsolutePath(),
                                new IDCameraInterface() {
                                    @Override
                                    public void onResult(String CONTENT_TYPE) {
                                        String filePath = FileUtil.getSaveFile(getActivity()).getAbsolutePath();
                                        Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                                        iv_pic.setImageBitmap(myBitmap);
                                        Log.d(TAG, "onResutl:" + CONTENT_TYPE);
                                    }

                                    public void onCancel() {
                                        iv_pic.setImageBitmap(null);
                                        Log.d(TAG,"onCancel");
                                    }
                                });
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getActivity()).getAbsolutePath();
                if (!TextUtils.isEmpty(contentType)) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                    iv_pic.setImageBitmap(myBitmap);
//                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
//
//                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
//
//                    }
                }
            }
        }
    }
}
