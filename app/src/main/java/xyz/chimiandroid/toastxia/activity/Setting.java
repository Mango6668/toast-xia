package xyz.chimiandroid.toastxia.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.io.File;

import xyz.chimiandroid.toastxia.R;
import xyz.chimiandroid.toastxia.tool.FileTool;

public class Setting  extends Activity {
    EditText settingEditText;
    Button settingOk;
    public static final String FILE_NAME = "cmtoast.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        INIT();
        if (FileTool.fileExists(FILE_NAME,this)) {
            // 文件路径
            String filePath = this.getExternalFilesDir(null).getAbsolutePath();
            // 文件的对象
            File file = new File(filePath, FILE_NAME);
            // 读取文件内容
            String f =FileTool.getFileContent(file);
            settingEditText.setText(f);

        }



    }



    private void INIT () {
        settingEditText =  findViewById(R.id.settingText);
        settingEditText.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);

                // 设置类型，类型必须是安卓SDK Framework定义的类的全类名，否则类型设置无效。
                info.setClassName(EditText.class.getName());
                // 设置状态
                info.setCheckable(true);
                info.setChecked(host.isSelected());
                // 设置提示文字
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    info.setHintText("请输入要设置的内容");
                }
            }
        });
        settingOk =    findViewById(R.id.settingOk);
        settingOk.setAccessibilityDelegate(new View.AccessibilityDelegate() {
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
                    info.setHintText("确定当前设置");
                }
            }
        });
        settingOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取输入框中的内容
                String text = settingEditText.getText().toString();
                Toast.makeText(getApplicationContext(),"输入框中的内容是："+text+".",Toast.LENGTH_LONG).show();
                if (text.isEmpty()){
                    Toast.makeText(Setting.this, "您还没有输入内容！", Toast.LENGTH_LONG).show();
                    return;
                }else {
                   boolean writeStatus = FileTool.writeFile(FILE_NAME,getApplicationContext(),text);
                   if (!writeStatus){
                       Toast.makeText(getApplicationContext(),"写入失败！",Toast.LENGTH_LONG).show();
                   }else {
                       Toast.makeText(getApplicationContext(),"写入成功！欢迎使用！",Toast.LENGTH_LONG).show();
                   }

                }



            }
        });

    }
}
