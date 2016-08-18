package com.github.zqhcxy.glidetest.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.github.zqhcxy.glidetest.R;
import com.github.zqhcxy.glidetest.Utils.CommonUtil;

/**
 * 动画-Glide的过渡动画，默认动画、系统动画、自定义动画。
 */
public class AnimateActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView animate_iv;
    private Button animate_clear;
    private Button animate_loading1;
    private Button animate_loading2;
    private Button animate_loading3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        findView();

    }

    private void findView() {
        animate_iv = (ImageView) findViewById(R.id.animate_iv);
        animate_clear = (Button) findViewById(R.id.animate_clear);
        animate_loading1 = (Button) findViewById(R.id.animate_loading1);
        animate_loading2 = (Button) findViewById(R.id.animate_loading2);
        animate_loading3 = (Button) findViewById(R.id.animate_loading3);
        animate_clear.setOnClickListener(this);
        animate_loading1.setOnClickListener(this);
        animate_loading2.setOnClickListener(this);
        animate_loading3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animate_clear:
                if (animate_iv != null) {
                    animate_iv.setImageDrawable(null);
                }
                break;
            case R.id.animate_loading1://系统动画
                Glide.with(AnimateActivity.this)
                        .load(CommonUtil.resourceIdToUri(AnimateActivity.this, R.mipmap.photo2))
                        .animate(android.R.anim.slide_in_left) // or R.anim.zoom_in
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(animate_iv);
                break;
            case R.id.animate_loading2://自定义动画
                Glide.with(AnimateActivity.this)
                        .load(CommonUtil.resourceIdToUri(AnimateActivity.this, R.mipmap.photo2))
                        .animate(animationObject) // or R.anim.zoom_in
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(animate_iv);
                break;
            case R.id.animate_loading3://默认过渡动画
                Glide.with(AnimateActivity.this)
                        .load(CommonUtil.resourceIdToUri(AnimateActivity.this, R.mipmap.photo2))
                        .crossFade()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(animate_iv);
                break;
        }
    }

    /**
     * 渐变动画，从透明到完全不透明渐变
     */
    ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            // if it's a custom view class, cast it here
            // then find subviews and do the animations
            // here, we just use the entire view for the fade animation
            view.setAlpha(0f);
            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(2000);
            fadeAnim.start();
        }
    };
}
