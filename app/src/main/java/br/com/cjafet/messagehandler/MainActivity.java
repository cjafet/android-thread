package br.com.cjafet.messagehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_SONG = "song";
    private Button mDownloadButton;
    private Button mServiceButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadThread thread = new DownloadThread();
        thread.setName("DownloadThread");
        thread.start();

        mDownloadButton = findViewById(R.id.downloadButton);
        mServiceButton = findViewById(R.id.serviceButton);

        mServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String song : PlayList.songs ) {
                    Log.d(TAG, song);
                    Intent intent = new Intent(MainActivity.this, DownloadService.class);
                    intent.putExtra(KEY_SONG, song);
                    startService(intent);
                }
            }
        });

        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (String song : PlayList.songs ) {
                    Log.d(TAG, song);

                    // Get a message object from pool
                    Message message = Message.obtain();

                    // Add any object to the message obj property
                    message.obj = song;

                    // Send the message to our handler
                    // So it can be added to the message queue
                    thread.mHandler.sendMessage(message);

                }
            }
        });


    }
}
