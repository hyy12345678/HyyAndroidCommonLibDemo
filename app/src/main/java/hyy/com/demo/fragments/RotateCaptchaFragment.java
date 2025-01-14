package hyy.com.demo.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hyy.android.common.view.RotateCaptchaViewGroup;
import com.hyy.android.common.view.SuperCircleView;
import com.hyy.android.common.view.callback.RotateCaptchaCallback;

import java.util.Random;

import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RotateCaptchaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RotateCaptchaFragment extends Fragment {

    private static final String TAG = RotateCaptchaFragment.class.getSimpleName();

    private View mView;

    private RotateCaptchaViewGroup rotateCaptchaViewGroup;


    public RotateCaptchaFragment() {
        // Required empty public constructor
    }

    public static RotateCaptchaFragment newInstance() {
        RotateCaptchaFragment fragment = new RotateCaptchaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_rotate_captcha, container, false);

        initView();
        initData();
        initEvent();

        return mView;
    }

    private void initView() {
        rotateCaptchaViewGroup = mView.findViewById(R.id.rotateCaptchaViewGroup);

        //测试本地加载图片
//        rotateCaptchaViewGroup.setImageResAndCallback2Show(
//                R.drawable.banner_firstpage_family,
//                new RotateCaptchaCallback() {
//                    @Override
//                    public void onPass() {
//                        onValidPass();
//                    }
//
//                    @Override
//                    public void onUnPass() {
//                        onValidUnPass();
//                    }
//                });

        //测试网络加载图片
        Glide.with(getActivity())
                .load("http://hyylj.cn/wp-content/uploads/2024/04/7cf5fb8c273a0cd950e0481e0cd1f77c.jpeg")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        rotateCaptchaViewGroup.setImageBitmapAndCallback2Show(resource, new RotateCaptchaCallback() {
                            @Override
                            public void onPass() {
                                onValidPass();
                            }

                            @Override
                            public void onUnPass() {
                                onValidUnPass();
                            }
                        });
                    }
                });

    }

    private void initData() {


    }

    private void initEvent() {


    }


    private void onValidPass() {

        Toast.makeText(getActivity(), "ValidPassed", Toast.LENGTH_SHORT).show();
        rotateCaptchaViewGroup.restart();
    }

    private void onValidUnPass() {
        Toast.makeText(getActivity(), "onValidUnPass", Toast.LENGTH_SHORT).show();
        rotateCaptchaViewGroup.restart();
    }
}