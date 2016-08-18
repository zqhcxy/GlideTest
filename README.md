# GlideTest
Glide 功能demo
=======
> 功能
>> 过渡动画
       两张方式来实现Glide的过渡动画：.animate();
  加载系统动画显示，如从左到右过渡：
  ```java
  Glide.with(AnimateActivity.this)
                        .load(CommonUtil.resourceIdToUri(AnimateActivity.this, R.mipmap.photo2))
                        .animate(android.R.anim.slide_in_left) // or R.anim.zoom_in
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(animate_iv);
  ```
  自定义动画类的过渡动画
  ```java
    Glide.with(AnimateActivity.this)
                        .load(CommonUtil.resourceIdToUri(AnimateActivity.this, R.mipmap.photo2))
                        .animate(animationObject) // or R.anim.zoom_in
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(animate_iv);
                        
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
  ```
  或者可以直接使用默认的.crossFade()
附：如果需要更多的动画，可以参考：
              [glide-transformations](https://github.com/wasabeef/glide-transformations)
  
  
  
  
