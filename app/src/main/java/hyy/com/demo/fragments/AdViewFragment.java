package hyy.com.demo.fragments;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hyy.android.common.view.AdViewLayout;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hyy.com.demo.constants.Constants;
import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdViewFragment extends Fragment {

    private View mView;
    private Activity mActivity;

    @Bind(R.id.layoutAd)
    AdViewLayout layoutAd;

    //UIL
    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;  // DisplayImageOptions是用于设置图片显示的类
    ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    List<ImageView> list_iv_ads_main;

    public AdViewFragment() {
        // Required empty public constructor
    }

    public static AdViewFragment newInstance(){
        AdViewFragment adViewFragment = new AdViewFragment();
        return adViewFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (mView == null) {
            View mView = inflater.inflate(R.layout.fragment_ad_view, container, false);
            ButterKnife.bind(this,mView);
            
            init();
        }

        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;
    }

    private void init() {
        
        initAdByLocalDrawable();
        
        initAdByLocalImageView();
        
        initAdByUIL();
        
    }

    private void initAdByUIL() {

        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_stub)			// 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_empty)	// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_error)		// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)							// 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20))	// 设置成圆角图片
                .build();									// 创建配置过得DisplayImageOption对象


        list_iv_ads_main = new ArrayList<ImageView>();

        for(String imageUrl: Constants.IMAGES){
            ImageView tempIv = new ImageView(getActivity());
            list_iv_ads_main.add(tempIv);

            imageLoader.displayImage(imageUrl, tempIv, options, animateFirstListener);
        }

        layoutAd.setAdsImageViewList(list_iv_ads_main);

    }

    private void initAdByLocalImageView() {

    }

    private void initAdByLocalDrawable() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * 图片加载第一次显示监听器
     * @author Administrator
     *
     */
    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                // 是否第一次显示
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // 图片淡入效果
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
