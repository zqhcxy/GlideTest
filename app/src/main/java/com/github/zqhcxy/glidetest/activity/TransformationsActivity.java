package com.github.zqhcxy.glidetest.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.github.zqhcxy.glidetest.R;

/**
 * Glide的图像转换
 */
public class TransformationsActivity extends AppCompatActivity {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private ImageView trf_img;
    private Button trf_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformations);

        findView();
    }

    private void findView() {
        trf_img = (ImageView) findViewById(R.id.trf_img);
        trf_btn = (Button) findViewById(R.id.trf_btn);

        trf_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(TransformationsActivity.this)
                        .load(resourceIdToUri(TransformationsActivity.this, R.mipmap.photo2))
                        .transform(new BlurTransformations(TransformationsActivity.this))
                        .into(trf_img);
            }
        });

    }

    public class BlurTransformations extends BitmapTransformation {
        private RenderScript rs;

        public BlurTransformations(Context context) {
            super(context);
            rs = RenderScript.create(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Bitmap blurredBitmap = toTransform.copy(Bitmap.Config.ARGB_8888, true);
            // 对于渲染脚本工作分配内存
            Allocation input = Allocation.createFromBitmap(
                    rs,
                    blurredBitmap,
                    Allocation.MipmapControl.MIPMAP_FULL,
                    Allocation.USAGE_SHARED
            );
            Allocation output = Allocation.createTyped(rs, input.getType());

            // 加载要使用的模糊的实例.
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);

            // 设置模糊半径
            script.setRadius(10);

            // Start the ScriptIntrinisicBlur
            script.forEach(output);

            // 将输出复制到模糊位图
            output.copyTo(blurredBitmap);

            toTransform.recycle();

            return blurredBitmap;
        }

        @Override
        public String getId() {
            return "blur";
        }
    }


    /**
     * 读取资源图片成Uri
     *
     * @param context
     * @param resourceId
     * @return
     */
    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
