package com.eloneth.copypasteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;





public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1,b2;

    private ClipboardManager myClipboard;//ClipBoarManager class is responsible for copy and paste. you need to instantiate an object of ClipboardManager by calling the getSystemService() method
    private ClipData myClip;// ClipData class has methods to copy data. calling the respective type of data method of the ClipData class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);

        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);

        //Init the clipBoardClass
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        //When b1 btn is clicked, perform the below action. Save user input in clipBoardManager class
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = ed1.getText().toString(); //Get user input, convert it to string and save in text

                myClip = ClipData.newPlainText("text", text);//Call newPlainText method from ClipData class and pass in the user input
                myClipboard.setPrimaryClip(myClip);//Save the user input in ClipBoardManage class

                Toast.makeText(getApplicationContext(), "Text Copied",Toast.LENGTH_SHORT).show();//Make a toast
            }
        });

        //Retrieve user input from clipBoardManager class when b2 btn is clicked
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData abc = myClipboard.getPrimaryClip();//Use getPrimaryClip to get the data from clipBoardManager class and save it in clipData class thu it object abc
                ClipData.Item item = abc.getItemAt(0);//use getItemAt method to get the data from abc and save it in clipDate.Item object

                String text = item.getText().toString();//Get the data and convert it to string
                ed2.setText(text);//Display/paste the data in ed2

                Toast.makeText(getApplicationContext(), "Text Pasted",Toast.LENGTH_SHORT).show();//Make a toast to show it has been pasted
            }
        });
    }
}