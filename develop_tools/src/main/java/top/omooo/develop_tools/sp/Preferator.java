package top.omooo.develop_tools.sp;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Omooo
 * Date:2019/6/11
 */
public class Preferator {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, PreferenceManagerActivity.class));
    }
}
