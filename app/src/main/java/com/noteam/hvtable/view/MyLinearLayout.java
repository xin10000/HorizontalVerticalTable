package com.noteam.hvtable.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 说明：
 * Created by 李明杰 on 2016/12/13.
 */

public class MyLinearLayout extends LinearLayout {
    private static final String TAG = "MyLinearLayout";
    private float firstX;
    private float firstY;
    private float motionY;
    private float motionX;
    private int cmd;
    private float xDistance;
    private float yDistance;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 判断是否需要中断事件的传递
     * 默认返回false 意思是，不中断
     * 返回true 意思是，中断
     */
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }
//
//    float xLast;
//    float yLast;
//
//    boolean upDown = true;
//    boolean once;
//    boolean init;
//
//    public boolean onTouchEvent(MotionEvent event) {
//        MotionEvent ev = event;
//        double degree = 0;
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                firstX = event.getX();
//                firstY = event.getY();
////                for (int i = 0; i < 2; i++) {
////                    View child = getChildAt(i);
////                    try {
////
////                        child.dispatchTouchEvent(event);
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                motionY = firstY - event.getY();
//                motionX = event.getX() - firstX;
//                degree = 180 * Math.atan(motionX != 0 ? motionY / motionX : 0) / Math.PI;
//
////                        cmd = getPtzCommand(degree);
//
//                break;
//            case MotionEvent.ACTION_UP:
////                for (int i = 0; i < 2; i++) {
////                    View child = getChildAt(i);
////                    try {
////
////                        child.dispatchTouchEvent(event);
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
//                break;
//        }
//
//        Log.e(TAG, "onTouchEvent:Math.abs(degree)<40 " + degree+" "+(Math.abs(degree) < 50) );
//        //获得listview的个数
//        int count = getChildCount();
////        if (Math.abs(degree) < 50&&Math.abs(degree)!=0) {
////            View child = getChildAt(1);
////            try {
////
////                child.dispatchTouchEvent(event);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////
//////            Log.e(TAG, "onTouchEvent:Math.abs(degree)<40 " + degree);
////        } else if (Math.abs(degree) > 50&&Math.abs(degree)!=0) 
////        
////        {
////            for (int i = 0; i < count; i++) {
////                View child = getChildAt(i);
////                try {
////
////                    child.dispatchTouchEvent(event);
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        }

//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                xDistance = yDistance = 0f;
//                xLast = ev.getX();
//                yLast = ev.getY();
//                upDown = true;
//                once = true;
//                Log.e(TAG, "onTouchEvent:ACTION_DOWN ");
//                for (int i = 0; i < 2; i++) {
//                    View child = getChildAt(i);
//                    child.dispatchTouchEvent(event);
//
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float curX = ev.getX();
//                float curY = ev.getY();
//
//                xDistance = Math.abs(curX - xLast);
//                yDistance = Math.abs(curY - yLast);
////                xLast = curX;
////                yLast = curY;
//                Log.e(TAG, "onTouchEvent:Change" + once + "  " + (xDistance >= yDistance));
//                if (once) {
//                    once = false;
//                    if (xDistance >= yDistance) {
//                        upDown = false;
//
//                    }else {
//                        upDown = true; 
//                    }
//                }
//                if (upDown) {
//
//                    for (int i = 0; i < 2; i++) {
//                        View child = getChildAt(i);
//                        child.dispatchTouchEvent(event);
//
//                    }
//                } else {
//                    View child = getChildAt(1);
//                    child.dispatchTouchEvent(event);
//                }
//
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e(TAG, "onTouchEvent:ACTION_UP ");
//                upDown = true;
//                once = true;
//                for (int i = 0; i < 2; i++) {
//                    View child = getChildAt(i);
//                    child.dispatchTouchEvent(event);
//
//                }
//                break;
////                return true;
//
//        }
//        Log.e(TAG, "onTouchEvent: " + upDown + "  " + ev.getAction());
//
//
//        return true;
//    }
//
//    public int getPtzCommand(double degree) {
//        /** 9: 上,10: 下 ,11: 左 ,12: 右, 13: 左上 ,14: 向上 ,15: 左下, 16: 右下*/
//        int iPtzCmd = -1;
//        Log.e(TAG, "getPtzCommand: " + degree);
//        if (0 == degree) {
//            iPtzCmd = motionX < 0 ? 11 : 12;
//        } else if (degree > 0) {
//            /***
//             * motionX<0 && motionY<0 ?第三区间:第一区间
//             */
//            iPtzCmd = motionX < 0 && motionY < 0 ? (degree > 0
//                    && degree <= 22.5 ? 11
//                    : (degree > 22.5 && degree <= 67.5 ? 15 : 10))
//                    : (degree > 0 && degree <= 22.5 ? 12 : (degree > 22.5
//                    && degree <= 67.5 ? 14 : 9));
//        } else {
//            /***
//             * motionX<0 && motionY>=0 ?第二区间:第四区间
//             */
//            iPtzCmd = motionX < 0 && motionY >= 0 ? (degree < 0
//                    && degree >= -22.5 ? 11 : (degree < -22.5
//                    && degree >= -67.5 ? 13 : 9)) : (degree < 0
//                    && degree >= -22.5 ? 12 : (degree < -22.5
//                    && degree >= -67.5 ? 16 : 10));
//        }
//
//        return iPtzCmd;
//    }


}
