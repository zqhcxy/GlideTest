package com.github.zqhcxy.glidetest.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.zqhcxy.glidetest.R;
import com.github.zqhcxy.glidetest.Utils.BlurBuilder;

/**
 * 图片模糊
 */
public class BlurImageActivity extends AppCompatActivity {

    private ImageView blur_img_2;
    private ImageView blur_img_1;
    private Button blur_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_image);

        findView();
    }

    private void findView() {
        blur_img_1 = (ImageView) findViewById(R.id.blur_img_1);
        blur_img_2 = (ImageView) findViewById(R.id.blur_img_2);
        blur_btn = (Button) findViewById(R.id.blur_btn);

        blur_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                blur_img_1.setDrawingCacheEnabled(true);
//                Bitmap bitmap = blur_img_1.getDrawingCache();
//                blur_img_1.setDrawingCacheEnabled(false);
                Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.photo2);
//                Bitmap bitmap = ContextCompat.getDrawable(BlurImageActivity.this,R.mipmap.photo2);
                Bitmap bitmap_result = BlurBuilder.blur(BlurImageActivity.this, bitmap);
                if (bitmap_result != null) {
                    blur_img_2.setImageBitmap(bitmap_result);
                }
            }
        });
    }
}
