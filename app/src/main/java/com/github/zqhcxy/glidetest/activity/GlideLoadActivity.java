package com.github.zqhcxy.glidetest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.zqhcxy.glidetest.R;
import com.github.zqhcxy.glidetest.Utils.CommonUtil;

/**
 * 简单的Glide图片加载
 */
public class GlideLoadActivity extends AppCompatActivity {
    private ImageView msgitem_mms_imgmms;
    private Button load_bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_load);

        msgitem_mms_imgmms = (ImageView) findViewById(R.id.msgitem_mms_imgmms);
        load_bt1=(Button)findViewById(R.id.load_bt1);
        load_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uri.parse("content://mms/part/3691")
                Glide.with(GlideLoadActivity.this).
                        load(CommonUtil.resourceIdToUri(GlideLoadActivity.this,R.mipmap.photo2))
                        .placeholder(R.drawable.empty_photo)
                        .dontAnimate()
                        .override(800, 800)
                        .into(msgitem_mms_imgmms);
            }
        });
    }
}
