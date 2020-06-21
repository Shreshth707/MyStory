package com.example.mystory;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {
    public MyViewPager(@NonNull Context context) {
        super(context);
        depthPageTransform();
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        depthPageTransform();
    }
    public void   depthPageTransform(){
        setPageTransformer(true,new ViewPagerTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
    private MotionEvent SwipeXY(MotionEvent motionEvent){
        float x=getWidth();
        float y=getHeight();

       /**
        float NewX=(motionEvent.getY()/y)*y;
        float NewY=(motionEvent.getX()/x)*x;
        motionEvent.setLocation(NewX,NewY);
        **/
        return motionEvent;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept=super.onInterceptTouchEvent(SwipeXY(ev));
        SwipeXY(ev);
        return intercept;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(SwipeXY(ev));
    }
    public class ViewPagerTransformer implements ViewPager.PageTransformer{

        private static final float MIN_SCALE = 0.75f;
        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}
