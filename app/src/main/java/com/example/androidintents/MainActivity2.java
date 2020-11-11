package com.example.androidintents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    String name=null;
    TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameText=findViewById(R.id.textView2);

        Bundle myDataActivity2 = getIntent().getExtras();
        if(myDataActivity2 != null){
            name=myDataActivity2.getString("Name");
        }
        nameText.setText(name);
    }
}