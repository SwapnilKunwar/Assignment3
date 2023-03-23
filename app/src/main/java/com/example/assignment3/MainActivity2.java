package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity2 extends AppCompatActivity {

    TextView t2;
    Button b2;

    String[] arr;
    String link;
    ArrayList<String> arr1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        t2 = findViewById(R.id.textView2);
        b2 = findViewById(R.id.button2);
        Intent intent = getIntent();
        String str = intent.getStringExtra("tg1");
        Log.d("tgg", str);
        t2.setText(str);
        String l = "";
        Task tsk = new Task( l , t2.getText().toString());
        tsk.execute();
        try {
            l= tsk.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Log.d("gg3" , l);
        int aud= l.indexOf("audio\":\"https");
        Log.d("aud" , String.valueOf(aud));
        int comma = l.indexOf("," , aud);
        Log.d("comma" , String.valueOf(comma));
        //Slicing link from the whole json string which is l in this case
        link = l.substring(aud+8 , comma-1);
        Log.d("link" , link);

        int index = 0;
        arr1 = new ArrayList<>();
        ArrayList<item> objarr=new ArrayList<item>();
        while(index!=-1){
            index = l.indexOf("partOfSpeech" , index+1);
            if(index==-1){
                break;
            }
            int comma2 = l.indexOf("," , index);
            arr1.add(l.substring(index +15 ,comma2-1 ));
            int definstart= l.indexOf("definition\":" , index+1 );
            int definend = l.indexOf("synonyms",definstart);
            Log.d("definstart", String.valueOf(definstart));
            Log.d("definend", String.valueOf(definend));
            String defin = l.substring(definstart+13  ,definend-3 );
            String example = "";
            String syn="";
            int synidx=  l.indexOf("synonyms" , index);
            if(l.charAt(synidx + 11)!=']'){
                syn = l.substring(synidx+10 , l.indexOf("," , synidx));
            }
            int antidx=  l.indexOf("antonyms" , index);
            if(l.charAt(antidx + 11)!=']'){
                syn = l.substring(antidx+10 , l.indexOf("," , antidx));
            }
            String ant="";
            item it = new item(l.substring(index +15 ,comma2-1 ) , defin , example,syn,ant);
            objarr.add(it);
            Log.d("pos", l.substring(index +15 ,comma2-1 ));
            Log.d("defin", defin);
            Log.d("syn", syn);
            Log.d("example", example);
            Log.d("ant", ant);

            Log.d("gg44", l.substring(index +15 ,comma2-1 ));
            Log.d("gg44", String.valueOf(index));
        }

        arr= new String[arr1.size()];
        for(int i = 0 ;i<arr1.size();i++){
            arr[i]=arr1.get(i);
        }



        ItemFragments myListFragment = new ItemFragments(arr, objarr);

        // Add the ListFragment to the container view using the FragmentManager
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, myListFragment)
                .commit();


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();

            }
        });


    }


    private void playAudio() {

        String audioUrl = link;

        // initializing media player
        MediaPlayer mediaPlayer = new MediaPlayer();

        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(audioUrl);
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // below line is use to display a toast message.
        Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show();
    }

}


class item{
    public String pof;
    public String def;
    public String example;
    public String syn;
    public String ant;

    item(String pof , String def , String example ,String syn , String ant){
        this.pof=pof;
        this.def=def;
        this.example=example;
        this.syn=syn;
        this.ant= ant;
    }


}
