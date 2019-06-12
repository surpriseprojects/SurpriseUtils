package top.omooo.surpriseutils.ui;

import android.graphics.Color;
import android.view.View;
import android.view.Window;

import top.omooo.base_library.utils.StatusBarUtil;
import top.omooo.surpriseutils.BaseActivity;
import top.omooo.surpriseutils.R;

/**
 * Created by Omooo
 * Date:2019/6/5
 */
public class StatusBarActivity extends BaseActivity {

    @Override
    protected boolean hideActionBar() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_status_bar;
    }

    @Override
    public void initView() {
    }

    public void switchToBlack(View view) {
        findViewById(R.id.view).setBackgroundColor(Color.WHITE);
        StatusBarUtil.setStatusBarBlackMode(this);
    }

    public void switchToWhite(View view) {
        findViewById(R.id.view).setBackgroundColor(Color.DKGRAY);
        StatusBarUtil.setStatusBarWhiteMode(this);
    }
}
