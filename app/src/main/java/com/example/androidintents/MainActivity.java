package com.example.androidintents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button ok;
    EditText name;
    private final int PICK_CONTACT=1;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ok=(Button) findViewById(R.id.ok);
        name=(EditText) findViewById(R.id.name);

        listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Open Website Example");
        arrayList.add("Open Contacts");
        arrayList.add("Open Phone Dialer");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    openWeb();
                }
                if(i==1){
                    openContact();
                }
                if(i==2){
                    openDial();
                }
            }
        });



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }

   private void openDial(){
       Intent out = new Intent();
       out.setAction(Intent.ACTION_DIAL);
       out.setData(Uri.parse("tel:"+Uri.encode("0541671553")));
       startActivity(out);
   }

    private void openWeb(){
        Intent out = new Intent();
        out.setAction(Intent.ACTION_VIEW);
        out.setData(Uri.parse("https://www.iau.edu.sa/ar"));
        startActivity(out);
    }

    private void openContact(){
        Intent out = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(out,PICK_CONTACT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_CONTACT){
            if(resultCode== RESULT_OK){
                Uri contactData=data.getData();
                Cursor c= getContentResolver().query(contactData, null,null,null,null);
                if(c.moveToFirst()){
                    String name =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                }
            }
        }
    }

    private void openActivity2(){
        String userName= name.getText().toString();

        Intent out = new Intent(this, MainActivity2.class);

        Bundle myData = new Bundle();
        myData.putString("Name",userName );

        out.putExtras(myData);
        startActivity(out);
    }
    }