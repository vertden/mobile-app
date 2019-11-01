package com.example.laba6;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText surname;
    EditText group;
    EditText faculty;

    EditText w_file;
    EditText r_file;

    Button b_write, b_read;
    TextView tv_text;

//    String fileName = "file.txt";
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surname = (EditText) findViewById(R.id.surname);
        group = (EditText) findViewById(R.id.group);
        faculty = (EditText) findViewById(R.id.faculty);

        w_file = (EditText) findViewById(R.id.w_file);
        r_file = (EditText) findViewById(R.id.r_file);

        b_write = (Button) findViewById(R.id.b_write);
        b_read = (Button) findViewById(R.id.b_read);

        tv_text = (TextView) findViewById(R.id.tv_text);

        b_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName;
                fileName = r_file.getText().toString() + ".txt";
                tv_text.setText(readFile(fileName));
            }
        });


        b_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String record, fileName;
                record = surname.getText().toString() + "\n" + group.getText().toString() + "\n" + faculty.getText().toString();
                fileName = w_file.getText().toString() + ".txt";
                saveFile (fileName,record);
            }
        });


    }

    public void saveFile(String file,String text) {
        try {
            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(MainActivity.this,"Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Error saving file!",Toast.LENGTH_SHORT).show();
        }

    }

    public String readFile(String file) {
        String text = "";

        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (Exception e ) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error reading file!", Toast.LENGTH_SHORT).show();
        }
        return text;
    }
}
