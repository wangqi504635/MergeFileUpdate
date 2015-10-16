package com.mergefileupdate;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends Activity {
    
    String rootPath = Environment.getExternalStorageDirectory().toString() + "/";
    String oldfile = rootPath + "old.apk";
    String newfile = rootPath + "new.apk";
    String patchfile = rootPath + "test.patch";
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Toast.makeText(MainActivity.this, "result is :" + msg.what, Toast.LENGTH_LONG)
                    .show();
        };
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File old = new File(oldfile);
        File patch = new File(patchfile);
        if (!old.exists() || !patch.exists()) {
            Toast.makeText(MainActivity.this, "file not exist", Toast.LENGTH_LONG).show();
        }
        else {
            new Thread() {
                public void run() {
                    int result = new MergeUtil().mergeFile(oldfile, newfile, patchfile);
                    mHandler.sendEmptyMessage(result);
                };
            }.start();
        }
    }
}
