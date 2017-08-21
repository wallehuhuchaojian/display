package com.display.crazybaby.crazydisplay.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by walle on 2017/7/10.
 */

public class NetTools {
    public static String TAG="NetTools";
    public static  String dirName="savedHtml";
    public static String getFileStr(File file){
        String  line=null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream=new FileInputStream(file);

        InputStreamReader inputStreamReader =new  InputStreamReader(inputStream);
            BufferedReader bufferedReader =new  BufferedReader( inputStreamReader);


        while ((line = bufferedReader.readLine()) != null) stringBuilder.append(line);
        inputStreamReader.close();
        bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
      public static boolean isNetworkConnected(Context context) {
             if (context != null) {
                     ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                     NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                     if (mNetworkInfo != null) {
                             return mNetworkInfo.isAvailable();
                         }
                 }
             return false;
         }
         public  static void saveWebData(String url, final Context context){
             OkHttpClient client=new OkHttpClient();
             Request request = (new Request.Builder()).url(url).build();
             client.newCall(request).enqueue(new Callback() {
                 @Override
                 public void onFailure(Call call, IOException e) {
                     Log.d(TAG,"saveWebData failed="+e.getMessage());
                 }

                 @Override
                 public void onResponse(Call call, Response response) throws IOException {
                     Log.d(TAG,"saveWebData onResponse="+response.body().string());
                        if (response.code()==200){
                            File file=context.getFilesDir();
                            File dir=new File(file.getAbsolutePath()+"/"+dirName);

                            if (dir.exists()){
                                File[] files=dir.listFiles();
                                for (File f: files) {
                                    f.delete();
                                }

                            }else dir.mkdirs();
                            File htmlFile=new File(dir.getAbsolutePath()+"/"+getCurrentDay()+".html");
                            if (!htmlFile.exists())
                                htmlFile.createNewFile();
                            OutputStream outputStream=new FileOutputStream(htmlFile);
                            outputStream.write(response.body().bytes());
                            outputStream.flush();
                            outputStream.close();
                        }
                 }
             });
         }
    public static String getCurrentDay(){
        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("dd_HH_mm_ss");
        Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);
        return str;
    }
}
