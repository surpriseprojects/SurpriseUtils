package top.omooo.develop_tools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class DevelopToolActivity extends AppCompatActivity {

    private RecyclerView mRvTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        setTitle("开发者工具");
        mRvTools = findViewById(R.id.rv_tools);
    }

    public static void startDevelopToolActivity(Context context) {
        context.startActivity(new Intent(context, DevelopToolActivity.class));
    }
}
