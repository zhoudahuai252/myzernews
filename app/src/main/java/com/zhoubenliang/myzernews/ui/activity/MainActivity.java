package com.zhoubenliang.myzernews.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.common.view.base.BaseActivity;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.boxview.ui.BoxViewFragment;
import com.zhoubenliang.myzernews.module.hot.ui.HotFragment;
import com.zhoubenliang.myzernews.module.life.ui.LifeFragment;
import com.zhoubenliang.myzernews.module.topic.ui.TopicFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;


public class MainActivity extends BaseActivity {
    @Bind(R.id.rg_tab)
    RadioGroup mRadioGroup;
    private Map<Integer, Fragment> mFragmentList;
    Fragment lastFragment;//当前显示的fragment

    public static void start(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        context.finish();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
    }

    @Override
    protected void onInitEvent() {
        mFragmentList = new HashMap<>();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId) {
                    case R.id.rb_boxview:
                        onSwichFragment(R.id.rb_boxview);
                        break;
                    case R.id.rb_hot:
                        onSwichFragment(R.id.rb_hot);
                        break;
                    case R.id.rb_life:
                        onSwichFragment(R.id.rb_life);
                        break;
                    case R.id.rb_topic:
                        onSwichFragment(R.id.rb_topic);
                        break;
                }
            }
        });
        mRadioGroup.check(R.id.rb_boxview);
    }

    private void onSwichFragment(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment showFragment = null;//需要显示出来的fragment
        if (mFragmentList.containsKey(id)) {
            //如果map中已经存在此fragment，把此fragment显示出来
            //并且隐藏前一个fragment
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
            showFragment = mFragmentList.get(id);
            lastFragment = showFragment;
            transaction.show(showFragment);
        } else {
            //如果map里面没有此fragment，需要创建，然后add到activity中
            switch (id) {
                case R.id.rb_boxview:
                    showFragment = new BoxViewFragment();
                    break;
                case R.id.rb_hot:
                    showFragment = new HotFragment();
                    break;
                case R.id.rb_life:
                    showFragment = new LifeFragment();
                    break;
                case R.id.rb_topic:
                    showFragment = new TopicFragment();
                    break;
                default:
                    break;
            }
            transaction.add(R.id.frame_layout, showFragment);
            mFragmentList.put(id, showFragment);
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
            lastFragment = showFragment;
        }
        transaction.commit();
    }


    @Override
    protected void onLoadData() {

    }

    void startFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }


}
