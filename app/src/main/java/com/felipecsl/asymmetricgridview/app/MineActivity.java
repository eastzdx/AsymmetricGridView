package com.felipecsl.asymmetricgridview.app;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangdongxu01 on 2016/3/20.
 */
public class MineActivity extends AppCompatActivity {
    private Context mContext;

    // TabLayout与ViewPager适配
    private List<View> mViewList;
    private List<String> mTitleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_main);

        mContext = this;

        initToolbar();

        initTextInput();

        initFloatActionButton();

        initTabLayout();
    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);

        setTitle("My TestActivity");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "This is a menu", Toast.LENGTH_SHORT).show();
            }
        });

        setSupportActionBar(toolbar);
    }

    /**
     * 初始化TextInput
     */
    private void initTextInput() {
        TextInputLayout usernameLayout = (TextInputLayout) findViewById(R.id.my_username_layout);
        usernameLayout.setHint("输入用户名字");

        TextInputLayout passwordLayout = (TextInputLayout) findViewById(R.id.my_password_layout);
        passwordLayout.setHint("输入密码");

    }

    /**
     * 初始化ActionButton
     */
    private void initFloatActionButton() {
        final View rootView = findViewById(android.R.id.content);

       FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.my_fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootView, "你好", Snackbar.LENGTH_LONG)
                        .setAction("deleteaabb", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "delete toast", Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });
    }

    /**
     * 初始化TabLayout+ViewPager
     */
    private void initTabLayout() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.my_tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.my_viewpager);

        mViewList = new ArrayList<View>();

        for(int i = 0; i < 3; i++) {
            View view = getLayoutInflater().inflate(R.layout.pager_item, null);
            TextView textView = (TextView) view.findViewById(R.id.pager_item_text);
            textView.setText("Item" + i);

            mViewList.add(view);
        }

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(mViewList);
        viewPager.setAdapter(myPagerAdapter);

        mTitleList = new ArrayList<String>();
        mTitleList.add("第一个");
        mTitleList.add("第二个");
        mTitleList.add("第三个");

        tabLayout.setTabTextColors(Color.WHITE, Color.GRAY);
      /*  tabLayout.addTab(tabLayout.newTab().setText("第一个"), true);
        tabLayout.addTab(tabLayout.newTab().setText("第二个"), false);
        tabLayout.addTab(tabLayout.newTab().setText("第三个"), false);*/

        tabLayout.setupWithViewPager(viewPager); // TabLayout与ViewPager的关联
        tabLayout.setTabsFromPagerAdapter(myPagerAdapter); // 在PagerAdapter中设置Tab的Title
    }

    class MyPagerAdapter extends PagerAdapter {

        private List<View> mViewList;

        public MyPagerAdapter(List<View> viewList) {
            this.mViewList = viewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
