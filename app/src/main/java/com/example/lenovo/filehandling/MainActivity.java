package com.example.lenovo.filehandling;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
EditText user;
Button save,load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button)findViewById(R.id.Save);
        load = (Button)findViewById(R.id.load);
        user = (EditText)findViewById(R.id.editText);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    return;
                }
                else
                {
                    String data=user.getText().toString();
                    Toast.makeText(MainActivity.this, "SAVED FILED", Toast.LENGTH_SHORT).show();

                    try
                    {
                        File file=new File("/sdcard/"+"CODE.TXT");
                        file.createNewFile();
                        FileOutputStream fileOutputStream=new FileOutputStream(file);
                        OutputStreamWriter writer=new OutputStreamWriter(fileOutputStream);
                        writer.append(data);
                        fileOutputStream.close();
                        Toast.makeText(MainActivity.this, "FILE SAVED", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer stringbuffer=new StringBuffer();
                 String adatarow= "";
                 String aBuffer="";
                 try
                 {
                  File name=new File("/sdcard/","CODE.TXT");
                     FileInputStream fileInputStream=new FileInputStream(name);
                     BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fileInputStream));
                     while((adatarow=bufferedReader.readLine())!=null)
                     {
                         aBuffer+=adatarow+"\n";
                     }
                     bufferedReader.close();

                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                Toast.makeText(MainActivity.this, aBuffer+"", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
