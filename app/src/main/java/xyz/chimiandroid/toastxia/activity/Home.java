package xyz.chimiandroid.toastxia.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Toast;



import java.io.File;

import xyz.chimiandroid.toastxia.R;
import xyz.chimiandroid.toastxia.tool.FileTool;

public class Home  extends Activity {
    Button copy;
    private static final String FILE_NAME = "cmtoast.txt";
  private  String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        filePath =   getApplicationContext().getExternalFilesDir(null).getAbsolutePath();


        if (!FileTool.fileExists(FILE_NAME, this)){

            Toast.makeText(this, "您还没有设置弹窗内容！", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),Setting.class);
            startActivity(intent);
            return;
        }



        File file = new File(filePath, FILE_NAME);
        // 若配置文件不存在
        if (!file.exists()) {
            Toast.makeText(getApplicationContext(),"配置文件不存在！~\n已为您跳转到设置页面！~",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
            return;

        }


        String text = FileTool.getFileContent(file);


        // 若获取到的是空字符串
        if ("".equals(text)||text == null) {
            Toast.makeText(getApplicationContext(),"配置文件异常！~已为您跳转到设置页面！~",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
            return;

        }



        // 初始化
        INIT();







    }

    private void INIT() {
        copy = findViewById(R.id.copyButton);
        copy.setAccessibilityDelegate(new View.AccessibilityDelegate() {
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
                    info.setHintText("点击复制弹窗");
                }
            }
        });

        // 文件路径

        // 文件的对象
        File file = new File(filePath, FILE_NAME);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = FileTool.getFileContent(file);
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", f);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);

                Toast.makeText(Home.this, "已经复制！", Toast.LENGTH_LONG).show();
            }
        });

    }


}
