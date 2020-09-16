package br.com.cjafet.messagehandler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DownloadHandler extends Handler {

    private static final String TAG = DownloadHandler.class.getSimpleName();
    private DownloadService mService;

    @Override
    public void handleMessage(Message msg) {

        downloadSong(msg.obj.toString());

        if (mService != null) {
            Log.d(TAG, "stoping service " + msg.arg1);
            mService.stopSelf(msg.arg1);
        }

    }

    private void downloadSong(String song) {
        long endTime = System.currentTimeMillis() + 10*1000;
        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, song + " downloaded!");
    }

    public void setService(DownloadService downloadService) {
        mService = downloadService;
    }
}