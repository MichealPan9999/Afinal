package com.example.panzq.myproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends FinalActivity {

    @ViewInject(id = R.id.iv_bitmap)
    ImageView iv_bitmap;

    @ViewInject(id = R.id.tv_html)
    TextView tv_html;

    @ViewInject(id = R.id.btn_db, click = "start_db")
    Button btn_db;

    public void start_db(View v) {
        tv_html.setText("start_db()...");
        Intent dbIntent = new Intent(MainActivity.this, FinalDBActivity.class);
        startActivity(dbIntent);
    }

    @ViewInject(id = R.id.btn_bitmap, click = "start_bitmap")
    Button btn_bitmap;

    public void start_bitmap(View v) {
        tv_html.setText("start_bitmap()...");
        String pic_url = "http://imgq.duitang.com/uploads/item/201403/05/20140305105955_5mhet.jpeg";
        FinalBitmap finalBitmap = FinalBitmap.create(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //普通加载
        //        finalBitmap.display(iv_pic,pic_url);
        //带加载动画
        //        finalBitmap.display(iv_pic,pic_url,bitmap);
        //带加载动画以及加载失败显示的图片
        finalBitmap.display(iv_bitmap, pic_url, bitmap, bitmap);

    }

    @ViewInject(id = R.id.btn_gethtml, click = "start_gethtml")
    Button btn_gethtml;

    public void start_gethtml(View v) {
        tv_html.setText("start_gethtml()...");
        FinalHttp fHttp = new FinalHttp();
        String url = "http://www.baidu.com";
        fHttp.get(url, new AjaxCallBack<String>() {

            @Override
            public void onStart() {
                super.onStart();
                tv_html.setText("----------start----------\r\n");
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                tv_html.append("--------"+current+"---------"+count+"\r\n");
            }

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                tv_html.append("-------end---------\r\n");
                if (!TextUtils.isEmpty(t))
                {
                    tv_html.append(t);
                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                tv_html.append("-------end---------");
                tv_html.append("错误码 ："+errorNo+" 错误信息："+strMsg);
            }
        });
    }

    @ViewInject(id = R.id.btn_download, click = "start_download")
    Button btn_download;

    public void start_download(View v) {
        tv_html.setText("start_download()...");
        FinalHttp finalHttp = new FinalHttp();
        String url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        String target = getFilesDir()+"/afinalmusic.mp4";
        finalHttp.download(url, target, new AjaxCallBack<File>() {
            @Override
            public void onStart() {
                super.onStart();
                tv_html.setText("--------- start---------\r\n");
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                tv_html.append("---------" +current+" --- "+count+"---------\r\n");
            }

            @Override
            public void onSuccess(File file) {
                super.onSuccess(file);
                tv_html.append("---------end---------\r\n");
                if (file!=null && file.exists())
                {
                    tv_html.append("file path : "+file.getAbsolutePath());
                    Intent mp4Intent = new Intent(MainActivity.this,PlayMp4Activity.class);
                    mp4Intent.putExtra("file_path",file.getAbsolutePath());
                    startActivity(mp4Intent);
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                tv_html.append("错误码："+errorNo+", 错误信息 :"+strMsg);
            }
        });
    }

    @ViewInject(id = R.id.btn_upload, click = "start_upload")
    Button btn_upload;

    public void start_upload(View v) {
        tv_html.setText("start_upload()...");
        FinalHttp finalHttp = new FinalHttp();
        String upload_path = "http://192.168.12.80:8080/FileUpload/FileUploadServlet";
        AjaxParams params = new AjaxParams();
        try {
            File file = new File(getFilesDir()+"/afinalmusic.mp4");
            if (file!=null && file.exists()) {
                params.put("File", new File(getFilesDir() + "/afinalmusic.mp4"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finalHttp.post(upload_path, params, new AjaxCallBack<Object>() {
            @Override
            public void onStart() {
                super.onStart();
                tv_html.append("开始上传...\r\n");
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                tv_html.append("正在上传-----"+current+" ----- "+count+"\r\n");

            }

            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                tv_html.append("上传成功----\r\n");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                tv_html.append("上传失败----错误码："+errorNo+"/ 错误信息："+strMsg+"\r\n");
            }
        });
    }

    //FinalBitmap
    FinalBitmap fBitMap = null;
    FinalHttp fHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}
