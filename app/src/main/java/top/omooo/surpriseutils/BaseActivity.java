package top.omooo.surpriseutils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import top.omooo.base_library.utils.StatusBarUtil;

/**
 * Created by Omooo
 * Date:2019/6/5
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hideActionBar()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        setContentView(getLayoutId());
        StatusBarUtil.init(this);

        initView();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    protected boolean hideActionBar() {
        return false;
    }
}
