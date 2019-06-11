package top.omooo.surpriseutils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import top.omooo.base_library.utils.StatusBarUtil;

/**
 * Created by Omooo
 * Date:2019/6/5
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        StatusBarUtil.init(this);

        initView();
    }

    public abstract int getLayoutId();

    public abstract void initView();

}
