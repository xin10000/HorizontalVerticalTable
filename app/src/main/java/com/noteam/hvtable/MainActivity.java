package com.noteam.hvtable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.noteam.hvtable.adapter.LvInfoAdapter;
import com.noteam.hvtable.adapter.LvNameAdapter;
import com.noteam.hvtable.view.LinkedHorizontalScrollView;
import com.noteam.hvtable.view.NoScrollHorizontalScrollView;


/**
 * 说明：
 * Created by 李明杰 on 2016/12/14.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private NoScrollHorizontalScrollView mGoodsNameSV;//不可滑动的顶部右侧的ScrollView
    private LinkedHorizontalScrollView mInfoContainer;//底部右侧的ScrollView
    private ListView mListViewName;//底部左侧的ListView
    private ListView mListViewInfo;//底部右侧的ListView

    boolean isLeftListEnabled = false;
    boolean isRightListEnabled = false;

    int lastPosition = -1;
    int firstVisiblePosition = -1;
    boolean selfChange;


    private LvNameAdapter mLvNormalNameAdapter;
    private LvInfoAdapter mLvNormalInfoAdapter;
    private LinearLayout llTitle;
    private View temp;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
    }


    private void initView() {
        llTitle = (LinearLayout) findViewById(R.id.ll_Title);
        mGoodsNameSV = (NoScrollHorizontalScrollView) findViewById(R.id.sv_title);
        mInfoContainer = (LinkedHorizontalScrollView) findViewById(R.id.sv_good_detail);
        mListViewName = (ListView) findViewById(R.id.lv_goods_name);
        mListViewInfo = (ListView) findViewById(R.id.lv_good_info);
        //联动控件
        combination(mListViewName, mListViewInfo, mGoodsNameSV, mInfoContainer);
    }

    private void initAdapter() {
        mLvNormalNameAdapter = new LvNameAdapter(this);
        mLvNormalInfoAdapter = new LvInfoAdapter(this);
        mListViewName.setAdapter(mLvNormalNameAdapter);
        mListViewInfo.setAdapter(mLvNormalInfoAdapter);

//        mListViewInfo.setFriction(ViewConfiguration.getScrollFriction() * 1.5f);
//        mListViewName.setFriction(ViewConfiguration.getScrollFriction() * 1.5f);
        for (int i = 0; i < 50; i++) {
            TextView textView = new TextView(this);
            textView.setId(i);
            textView.setHeight(100);
            textView.setMinWidth(200);
            textView.setBackgroundColor(0xFFEFE4E7);
            textView.setText("Title" + (i + 1));
            textView.setGravity(Gravity.CENTER);
            textView.setOnClickListener(this);
            llTitle.addView(textView);
        }

        mListViewName.setOnItemClickListener(this);
        mListViewInfo.setOnItemClickListener(this);

    }


    private void combination(final ListView lvName, final ListView lvDetail, final HorizontalScrollView title, LinkedHorizontalScrollView content) {
        /**
         * 左右滑动同步
         */
        content.setMyScrollChangeListener(new LinkedHorizontalScrollView.LinkScrollChangeListener() {
            @Override
            public void onscroll(LinkedHorizontalScrollView view, int x, int y, int oldx, int oldy) {
                title.scrollTo(x, y);
            }
        });

        /**
         * 上下滑动同步
         */
        // 禁止快速滑动
        lvName.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        lvDetail.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        //左侧ListView滚动时，控制右侧ListView滚动
        lvName.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //这两个enable标志位是为了避免死循环
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    isRightListEnabled = false;
                    isLeftListEnabled = true;
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    isRightListEnabled = true;
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                View child = view.getChildAt(0);
//                Log.e(TAG, "lvName onScroll: ");
                if (child != null && isLeftListEnabled && !isRightListEnabled) {
                    lvDetail.setSelectionFromTop(firstVisibleItem, child.getTop());
                    if (firstVisibleItem >= visibleItemCount)
                        mLvNormalInfoAdapter.setFirstVisibleItem(firstVisibleItem - 5);
                    else
                        mLvNormalInfoAdapter.setFirstVisibleItem(-1);

                }
            }
        });
//
//        //右侧ListView滚动时，控制左侧ListView滚动
        lvDetail.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    isLeftListEnabled = false;
                    isRightListEnabled = true;
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    isLeftListEnabled = true;
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                View c = view.getChildAt(0);
//                Log.e(TAG, "lvDetail onScroll: ");
                if (c != null && isRightListEnabled && !isLeftListEnabled) {
                    lvName.setSelectionFromTop(firstVisibleItem, c.getTop());
                    mLvNormalInfoAdapter.setFirstVisibleItem(-1);
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        mLvNormalInfoAdapter.setColomn(v.getId());
        v.setBackgroundColor(0xff00ff00);
        if (temp == null) {
            temp = v;
            return;
        }
        if (v != temp) {
            temp.setBackgroundColor(0xFFEFE4E7);
            temp = v;
        } else {
            v.setBackgroundColor(0xFFEFE4E7);
            temp = null;
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        firstVisiblePosition = mListViewName.getFirstVisiblePosition();


        for (int i = 0; i < mListViewName.getChildCount(); i++) {

            mListViewName.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
            mListViewInfo.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
            if (position - firstVisiblePosition == i) {
//                mListViewName.getChildAt(i).setBackgroundColor(mListViewName.getChildAt(i).isEnabled() ? 0xFFEFE4E7 : 0xFFFFFFFF);
//                mListViewInfo.getChildAt(i).setBackgroundColor(mListViewName.getChildAt(i).isEnabled() ? 0xFFEFE4E7 : 0xFFFFFFFF);   
                mListViewName.getChildAt(i).setBackgroundColor(0xFFEFE4E7);
                mListViewInfo.getChildAt(i).setBackgroundColor(0xFFEFE4E7);
//                mListViewName.getChildAt(i).setEnabled(!mListViewName.getChildAt(i).isEnabled());
                lastPosition = position;

            }
        }

        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
}


}
