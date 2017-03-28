package com.noteam.hvtable.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 说明：
 * Created by 李明杰 on 2016/12/15.
 */

public class MyTextView extends AppCompatTextView {
    private static final String TAG = "MyTextView";
    private boolean drawTable;
    String lastText;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (drawTable) {
            Paint paint = new Paint();
            paint.setColor(0xff00ff00);
            canvas.drawRect(0, getHeight() / 6, getWidth() * Integer.parseInt(lastText) / 2000, getHeight() * 5 / 6, paint);
        }

        super.onDraw(canvas);
    }

    public void setDrawTable(boolean drawTable, String s) {
        this.drawTable = drawTable;
        lastText = s;
        
        if (drawTable) {
            setTextColor(0xff555555);
            setTextSize(20);
        } else {
            setTextSize(14);
            setTextColor(0xff888888);
//            setText(lastText);
        }
        setText(lastText);
    }
}
