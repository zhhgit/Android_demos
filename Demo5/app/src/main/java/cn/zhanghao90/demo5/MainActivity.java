package cn.zhanghao90.demo5;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView1;
    private Button button1,button2;
    private File currentImageFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir = new File(Environment.getExternalStorageDirectory(),"newPicFolder");
                if(!dir.exists()){
                    dir.mkdirs();
                }
                currentImageFile = new File(dir,System.currentTimeMillis() + ".jpg");
                if(!currentImageFile.exists()){
                    try {
                        if(!currentImageFile.createNewFile()){
                            Toast.makeText(getApplicationContext(),"新建文件未成功",Toast.LENGTH_SHORT).show();
                        };
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
                startActivityForResult(it,2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imageView1.setImageBitmap(bitmap);
        }
        else if(requestCode == 2){
            imageView1.setImageURI(Uri.fromFile(currentImageFile));
        }
    }
}