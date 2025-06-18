package xyz.chimiandroid.toastxia.tool;

import android.app.Activity;
import android.content.Context;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * 文件工具类
 * @author chimi
 * @version 1.0
 * @since JDK 17
 * @see java.io.InputStreamReader
 * @see java.io.FileInputStream
 */
public final class FileTool {

    /**
     * 请求权限的集合
     */
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    /**
     * 权限请求码
     */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 请求相关权限
     * @param activity Activity对象
     */
    public static void verifyStoragePermissions(Activity activity) {
        try {
            if (ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于判断文件是否存在
     * @param fileName 文件名
     * @param context 上下文
     * @return 文件存在返回true，否则返回false
     */

    /**
     * 此方法用于判断文件是否存在
     * @param fileName 文件名
     * @param context 上下文
     * @return 判断结果布尔值
     */
    public static boolean fileExists(String fileName,Context context){
        String filePath = context.getExternalFilesDir(null).getAbsolutePath();
        File file  = new File(filePath,fileName);
        if (file.exists()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 此方法用于写入文本文件到应用专属目录下的file文件夹内
     *
     * @param fileName 文件名
     * @param context  上下文，具体参见安卓API文档
     * @param text     写入文件的内容
     * @return 写入是否成功，写入成功返回true，否则返回false
     */
    public static boolean writeFile(String fileName, Context context, String text) {
        // 文件路径
        String filePath = context.getExternalFilesDir(null).getAbsolutePath();
        // 文件的对象
        File file = new File(filePath, fileName);
        // 写入结果，默认为false
        boolean writeFileResult = false;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            outputStreamWriter.write(text);
            // 写入后记得刷新输出流！！
            outputStreamWriter.flush();
            // 最后后关闭输出流
            outputStreamWriter.close();
            fileOutputStream.close();
            // 走到这没出现异常代表写入成功，将写入结果改为true，并返回
            writeFileResult = true;
            return writeFileResult;

        } catch (IOException e) {
           return writeFileResult;
        } finally {
            return writeFileResult;
        }




    }

    /**
     * 此方法用于读取txt文件当中的内容
     * @param file 文件对象
     * @return 文件内容的字符串
     */
    public static String getFileContent(File file) {
        String content = "";
        if (!file.isDirectory() && file.getName().endsWith(".txt")) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    content = content + readLine + "\n";
                }
                fileInputStream.close();
            } catch (FileNotFoundException unused) {
                 // Log.d("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
               // Log.d("TestFile", e.getMessage());
            }
        }
        return content;
    }




}