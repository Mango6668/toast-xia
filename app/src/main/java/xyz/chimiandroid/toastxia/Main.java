package xyz.chimiandroid.toastxia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import xyz.chimiandroid.toastxia.tool.FileTool;
public class Main  extends Activity {
    Button toHomeButton;
    Button toSettingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 请求权限
        FileTool.verifyStoragePermissions(this);
        permission();
        Toast.makeText(getApplicationContext(), "请授权权限", Toast.LENGTH_SHORT).show();
        INIT();

    }

    private void permission () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 100);
            }
        }
    }

    private void INIT(){
        toHomeButton = findViewById(R.id.toHomeButton);
        toSettingButton = findViewById(R.id.toSettingButton);
        toHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }

        }



        );

        toSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Setting.class);
                startActivity(intent);
            }
        });



    }
}
