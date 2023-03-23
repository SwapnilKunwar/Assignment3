package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText t1;
    Button b1;
    TextView text1;

    public boolean containsOnlyCharacters(String str) {
        return str.matches("[a-zA-Z]+");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1= (EditText) findViewById(R.id.editTextTextPersonName);
        b1= (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("tg1" , t1.getText().toString());
                Log.d("tg", t1.getText().toString());
                String l = "";
                Task tsk = new Task( l , t1.getText().toString());
                tsk.execute();

                try {
                    l= tsk.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Log.d("l", l);
                if(!containsOnlyCharacters(t1.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
                else if(l=="nulle"){
                    Toast.makeText(getApplicationContext(), "Word Not found in the Dictionary", Toast.LENGTH_SHORT).show();

                }
                else{
                    startActivity(intent);
                }


            }
        });

    }


}