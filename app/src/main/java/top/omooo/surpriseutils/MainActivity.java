package top.omooo.surpriseutils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import top.omooo.base_library.utils.StatusBarUtil;
import top.omooo.surpriseutils.ui.PreferenceActivity;
import top.omooo.surpriseutils.ui.StatusBarActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStatusBar;
    private Button mBtnSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView() {
        mBtnStatusBar = findViewById(R.id.btn_status_bar);
        mBtnSp = findViewById(R.id.btn_sp);
    }

    private void initListener() {
        mBtnStatusBar.setOnClickListener(this);
        mBtnSp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_status_bar:
                startActivity(new Intent(this, StatusBarActivity.class));
                break;
            case R.id.btn_sp:
                startActivity(new Intent(this, PreferenceActivity.class));
                break;
            default:
                break;
        }
    }
}
