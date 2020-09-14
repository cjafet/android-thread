package br.com.cjafet.messagehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mDownloadButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DownloadThread thread = new DownloadThread();
        thread.setName("DownloadThread");
        thread.start();

        mDownloadButton = findViewById(R.id.downloadButton);

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
