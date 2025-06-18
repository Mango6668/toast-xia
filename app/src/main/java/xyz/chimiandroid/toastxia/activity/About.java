package xyz.chimiandroid.toastxia.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;

import androidx.annotation.Nullable;

import xyz.chimiandroid.toastxia.R;

/**
 * 关于页面
 * @author chimi
 * @version 1.0
 * @since JDK 17
 */

public class About extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        INIT();
        textView.setText("本软件由御坂网络技术服务开发\nQQ：\n2934301923\n本软件采用木兰公共许可证进行开源！~");
        textView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);

                // 设置类型，类型必须是安卓SDK Framework定义的类的全类名，否则类型设置无效。
                info.setClassName(TextView.class.getName());
                // 设置状态
                info.setCheckable(true);
                info.setChecked(host.isSelected());
                // 设置提示文字
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    info.setHintText("本软件由御坂网络技术服务开发\nQQ：\n2934301923\n本软件采用木兰公共许可证进行开源！~");
                }
            }
        });
    }

    private void INIT() {
        textView = findViewById(R.id.aboutText);

    }
}
