package xyz.chimiandroid.toastxia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.io.File;

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
        settingOk =    findViewById(R.id.settingOk);
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
