package cn.zhanghao90.demo20;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private ImageView imageView1;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(getExternalCacheDir(),"outputImage.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT > 24){
                    uri = FileProvider.getUriForFile(getApplicationContext(),"cn.zhanghao90.demo20.fileProvider",outputImage);
                }
                else{
                    uri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    try{
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView1.setImageBitmap(bitmap);
                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
