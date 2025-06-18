package xyz.chimiandroid.toastxia.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Toast;

import xyz.chimiandroid.toastxia.R;
import xyz.chimiandroid.toastxia.tool.FileTool;
public class Main  extends Activity {
    /**
     * 去主页的按钮对象
     */
    Button toHomeButton;
    /**
     * 去设置的按钮对象
     */
    Button toSettingButton;
    Button toAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 请求权限
        FileTool.verifyStoragePermissions(this);
       // permission();
        Toast.makeText(getApplicationContext(), "请授权权限", Toast.LENGTH_SHORT).show();
        INIT();



    }

    /**
     * 此方法用于获取全部文件权限
     * @version 1.0
     */
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

    /**
     * 此方法用于初始化Activity
     */
    private void INIT(){
        toHomeButton = findViewById(R.id.toHomeButton);
        // 将控件做无障碍化处理
       toHomeButton.setAccessibilityDelegate(new View.AccessibilityDelegate() {
           @Override
           public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
               super.onInitializeAccessibilityNodeInfo(host, info);

               // 设置类型，类型必须是安卓SDK Framework定义的类的全类名，否则类型设置无效。
               info.setClassName(Button.class.getName());
               // 设置状态
               info.setCheckable(true);
               info.setChecked(host.isSelected());
               // 设置提示文字
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   info.setHintText("点击进入软件主页");
               }
           }
       });

        toSettingButton = findViewById(R.id.toSettingButton);
        toSettingButton.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);

                // 设置类型，类型必须是安卓SDK Framework定义的类的全类名，否则类型设置无效。
                info.setClassName(Button.class.getName());
                // 设置状态
                info.setCheckable(true);
                info.setChecked(host.isSelected());

                // 设置提示文字
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    info.setHintText("点击去设置内容");
                }
            }
        });
        toAboutButton = findViewById(R.id.toAbout);
        toAboutButton.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);

                // 设置类型，类型必须是安卓SDK Framework定义的类的全类名，否则类型设置无效。
                info.setClassName(Button.class.getName());
                // 设置状态
                info.setCheckable(true);
                info.setChecked(host.isSelected());

                // 设置提示文字
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    info.setHintText("点击去往关于页面");
                }
            }
        });
        toAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),About.class);
                startActivity(intent);
            }
        });
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
