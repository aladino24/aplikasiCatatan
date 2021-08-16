package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class InsertAndViewActivity extends AppCompatActivity {
    private EditText editFileName,editContent;
    private Button btnSimpan;
    private Object OutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_and_view);
        editFileName=findViewById(R.id.editFileName);
        editContent=findViewById(R.id.editContent);
        btnSimpan=findViewById(R.id.buttonSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make();
            }
        });
    }

    private void make(){
        String state= Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }
        String path=Environment.getExternalStorageDirectory().toString()+"NoteApp";
        File parent=new File(path);
        if (parent.exists()){
            File file=new File(path,editFileName.getText().toString());
            FileOutputStream outputStream=null;
            try {
                file.createNewFile();
                OutputStream=new FileOutputStream(file);
                OutputStreamWriter streamWriter=new OutputStreamWriter(outputStream);
                streamWriter.append(editContent.getText().toString());
                streamWriter.flush();
                streamWriter.close();
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            parent.mkdir();
            File file=new File(path,editFileName.getText().toString());
            FileOutputStream outputStream=null;
            try {
                file.createNewFile();
                OutputStream=new FileOutputStream(file,false);
                outputStream.write(editContent.getContext().toString().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}