package br.com.cjafet.messagehandler;

import android.os.Looper;

public class DownloadThread extends Thread {

    public DownloadHandler mHandler;

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new DownloadHandler();
        Looper.loop();
    }
}