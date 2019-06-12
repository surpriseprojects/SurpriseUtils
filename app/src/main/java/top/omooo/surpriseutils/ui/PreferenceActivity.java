package top.omooo.surpriseutils.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import top.omooo.develop_tools.sp.Preferator;
import top.omooo.surpriseutils.BaseActivity;
import top.omooo.surpriseutils.R;

/**
 * Created by Omooo
 * Date:2019/6/11
 */
public class PreferenceActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_preference;
    }

    @Override
    public void initView() {

    }

    public void initData(View view) {
        SharedPreferences.Editor editor = getSharedPreferences("sp_file_1", Context.MODE_PRIVATE).edit();
        editor.putBoolean("booleanKey", true)
                .putFloat("floatKey", 1.0f)
                .putInt("intKey", 2333)
                .putLong("longKey", 2333L)
                .putString("stringKey", "Omooo")
                .apply();

        SharedPreferences.Editor editor2 = getSharedPreferences("sp_file_2", Context.MODE_PRIVATE).edit();
        editor2.putBoolean("booleanKey", false)
                .putFloat("floatKey", 2.0f)
                .putInt("intKey", 233333)
                .putLong("longKey", 2333L)
                .putString("stringKey", "Crazy")
                .apply();
    }

    public void manager(View view) {
        Preferator.launch(this);
    }
}
