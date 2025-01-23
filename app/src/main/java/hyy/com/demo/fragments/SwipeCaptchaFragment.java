package hyy.com.demo.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hyy.android.common.view.SwipeCaptchaView;

import hyy.com.demo.MainActivity;
import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SwipeCaptchaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwipeCaptchaFragment extends Fragment {

    private View mView;

    SwipeCaptchaView mSwipeCaptchaView;
    SeekBar mSeekBar;
    Button mBtnChange;

    public SwipeCaptchaFragment() {
        // Required empty public constructor
    }


    public static SwipeCaptchaFragment newInstance() {
        SwipeCaptchaFragment fragment = new SwipeCaptchaFragment();
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
        mView = inflater.inflate(R.layout.fragment_swipe_captcha, container, false);

        initView();
        initData();
        initEvent();
        

        return mView;
    }

    private void initView() {
        mSwipeCaptchaView = mView.findViewById(R.id.swipeCaptchaView);
        mSeekBar = mView.findViewById(R.id.dragBar);
        mBtnChange = mView.findViewById(R.id.btnChange);

    }

    private void initData() {
        Glide.with(getActivity())
                .load("http://hyylj.cn/wp-content/uploads/2024/04/7cf5fb8c273a0cd950e0481e0cd1f77c.jpeg")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mSwipeCaptchaView.setImageBitmap(resource);
                        mSwipeCaptchaView.createCaptcha();
                    }
                });
    }

    private void initEvent() {

        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeCaptchaView.createCaptcha();
                mSeekBar.setEnabled(true);
                mSeekBar.setProgress(0);
            }
        });

        mSwipeCaptchaView.setOnCaptchaMatchCallback(new SwipeCaptchaView.OnCaptchaMatchCallback() {
            @Override
            public void matchSuccess(SwipeCaptchaView swipeCaptchaView) {
                Toast.makeText(getActivity(), "恭喜你啊 验证成功 可以搞事情了", Toast.LENGTH_SHORT).show();
                //swipeCaptcha.createCaptcha();
                mSeekBar.setEnabled(false);
            }

            @Override
            public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
                Log.d("zxt", "matchFailed() called with: swipeCaptchaView = [" + swipeCaptchaView + "]");
                Toast.makeText(getActivity(), "你有80%的可能是机器人，现在走还来得及", Toast.LENGTH_SHORT).show();
                swipeCaptchaView.resetCaptcha();
                mSeekBar.setProgress(0);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSwipeCaptchaView.setCurrentSwipeValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //随便放这里是因为控件
                mSeekBar.setMax(mSwipeCaptchaView.getMaxSwipeValue());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("zxt", "onStopTrackingTouch() called with: seekBar = [" + seekBar + "]");
                mSwipeCaptchaView.matchCaptcha();
            }
        });

    }
}