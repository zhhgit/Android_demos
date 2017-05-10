package cn.zhanghao90.demo21;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                requestImage();
                break;
            default:
                break;
        }
    }

    //判断是否有权限读取图片，并申请权限
    private void requestImage(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else{
            chooseImage();
        }
    }

    //确认读取图片权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    chooseImage();
                }
                else{
                    Toast.makeText(getApplicationContext(),"没有权限读取图片",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    //打开图片库
    private void chooseImage(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,2);
    }

    //选取图片完成
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 2:
                if(resultCode == RESULT_OK){
                    if(Build.VERSION.SDK_INT >= 19){
                        getImagePathAfter(data);
                    }
                    else{
                        getImagePathBefore(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    //4.4以前版本获取imagePath
    private void getImagePathBefore(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }


    //4.4以后版本获取imagePath
    private void getImagePathAfter(Intent data){
        Uri uri = data.getData();
        String path = null;
        //document类型
        if(DocumentsContract.isDocumentUri(MainActivity.this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equalsIgnoreCase(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                path = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.providers.downloads.documents".equalsIgnoreCase(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                path = getImagePath(contentUri,null);
            }
        }
        //content类型
        else if("content".equalsIgnoreCase(uri.getScheme())){
            path = getImagePath(uri,null);
        }
        //file类型
        else if("file".equalsIgnoreCase(uri.getScheme())){
            path = uri.getPath();
        }
        displayImage(path);
    }

    //获取具体的地址
    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //显示图片
    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView1.setImageBitmap(bitmap);
        }
    }
}
