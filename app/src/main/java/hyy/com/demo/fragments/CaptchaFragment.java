package hyy.com.demo.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.hyy.android.common.view.SuperCircleView;

import java.util.Random;

import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaptchaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaptchaFragment extends Fragment {

    private static final String TAG = CameraFragment.class.getSimpleName();

    private View mView;

    private ImageView captchaImageView;
    private SeekBar rotationSeekBar;


    private ImageView captchaImageViewMask;

    private SuperCircleView  mSuperCircleView;


    private float mMatchDeviation = 10f;

    private Random mRandom = new Random();;
    private int randomDegree;


    public CaptchaFragment() {
        // Required empty public constructor
    }

    public static CaptchaFragment newInstance() {
        CaptchaFragment fragment = new CaptchaFragment();
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
        mView = inflater.inflate(R.layout.fragment_captcha, container, false);

        initView();
        initData();
        initEvent();

        return mView;
    }

    private void initView() {
        captchaImageView = mView.findViewById(R.id.captchaImageView);
        rotationSeekBar = mView.findViewById(R.id.rotationSeekBar);
        captchaImageViewMask = mView.findViewById(R.id.captchaImageViewMask);
        mSuperCircleView = mView.findViewById(R.id.circleView);
    }

    private void initData() {
//        captchaImageView.setVisibility(View.GONE);

        randomDegree = mRandom.nextInt(240) + 60;

        rotationSeekBar.setMax(10000);


        captchaImageView.post(new Runnable() {
            @Override
            public void run() {

                int cIvWidth = captchaImageView.getWidth();
                Log.d(TAG, "cIvWidth = " + cIvWidth);
                int cIvHeight = captchaImageView.getHeight();
                Log.d(TAG, "cIvHeight = " + cIvHeight);

                Drawable drawable = captchaImageView.getDrawable();
                if (drawable instanceof BitmapDrawable) {


                    Bitmap originBitmap = ((BitmapDrawable) drawable).getBitmap();
                    int width = originBitmap.getWidth();
                    int height = originBitmap.getHeight();
                    int centerX = width / 2;
                    Log.d(TAG, "centerX = " + centerX);
                    int centerY = height / 2;
                    Log.d(TAG, "centerY = " + centerY);
                    int radius = Math.min(width, height) / 2;
                    Log.d(TAG, "radius = " + radius);

                    int dripX = -centerX + radius;
                    int dripY = -centerY + radius;


                    //------------------------handle captchaImageViewMask begin------------------------

//                    Bitmap circularBitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);
                    Bitmap circularBitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);

                    Canvas canvas = new Canvas(circularBitmap);
                    Paint paint = new Paint();

                    paint.setAntiAlias(true);

                    canvas.drawCircle(radius, radius, radius/2, paint);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

//                    float ratioHeight = (float) cIvHeight / (float) height;
//                    Log.d(TAG, "ratioHeight = " + ratioHeight);
//
//                    canvas.scale(ratioHeight,ratioHeight);

//            canvas.drawBitmap(originBitmap, centerX - radius, centerY - radius, paint);


                    canvas.drawBitmap(originBitmap, dripX, dripY, paint);

                    captchaImageViewMask.setImageBitmap(circularBitmap);

                    captchaImageViewMask.setRotation(randomDegree);

                    //------------------------handle captchaImageViewMask end--------------------------



                }

            }
        });


    }

    private void initEvent() {

        rotationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Rotate the image based on the SeekBar's progress

                int rDegree = progress *360/10000 + randomDegree;
                captchaImageViewMask.setRotation(rDegree);



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something when the user starts interacting with the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do something when the user stops interacting with the SeekBar
                int progress = seekBar.getProgress();
                int rDegree = progress *360/10000 + randomDegree;
                int gap = 360 - rDegree;
                if(Math.abs(gap) < mMatchDeviation){
                    onValidPass();
                }


            }
        });
    }


    private void onValidPass(){

        Toast.makeText(getActivity(), "ValidPassed", Toast.LENGTH_SHORT).show();

    }
}