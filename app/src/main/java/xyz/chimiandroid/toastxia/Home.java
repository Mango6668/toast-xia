package xyz.chimiandroid.toastxia;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import java.io.File;

import xyz.chimiandroid.toastxia.tool.FileTool;

public class Home  extends Activity {
    Button copy;
    public static final String FILE_NAME = "cmtoast.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        if (!FileTool.fileExists(FILE_NAME, this)){

            Toast.makeText(this, "您还没有设置弹窗内容！", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),Setting.class);
            startActivity(intent);
            return;
        }

        String filePath = getApplicationContext().getExternalFilesDir(null).getAbsolutePath();

        File file = new File(filePath, FILE_NAME);

        String text = FileTool.getFileContent(file);
        // 如果配置文件中为空文件
        if (text.isEmpty()){
            Toast.makeText(getApplicationContext(),"配置文件异常！即将跳转到设置页面！",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),Setting.class);
            startActivity(intent);
           return;
        }



        // 初始化
        INIT();







    }

    private void INIT() {
        copy = findViewById(R.id.copyButton);

        // 文件路径
        String filePath = getApplicationContext().getExternalFilesDir(null).getAbsolutePath();
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
