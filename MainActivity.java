package com.example.dell.mycamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView view;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reqPer();
        view=findViewById(R.id.iv);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,100);

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100)
        {
            Bundle b=data.getExtras();
            Bitmap bmp=(Bitmap)b.get("data");
            view.setImageBitmap(bmp);
        }
    }

    public void reqPer(){
        String[] permisions=new String[]{Manifest.permission.CAMERA,Manifest.permission.CAMERA,Manifest.permission.CAMERA};

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(permisions,1001);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1001:
                for(int gres:grantResults){
                    gres=PackageManager.PERMISSION_GRANTED;
                }
                break;
            default:
                break;
        }
    }
}
