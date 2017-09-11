package com.mostafaabdel_fatah.capturevideo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    final int requestCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CaptureVideo_btnClicked(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File file = getFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file) );
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1 );
        startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.requestCode == requestCode)
            if (resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(),"Video Successfully Recorded..."
                        ,Toast.LENGTH_SHORT).show();
                Uri vidFile = Uri.parse("sdcard/videos/video1.mp4");
                VideoView videoView = (VideoView) findViewById(R.id.videoView);
                videoView.setVideoURI(vidFile);
                videoView.setMediaController(new MediaController(this));
                videoView.start();
            }else {
                Toast.makeText(getApplicationContext(),"Video Capture Failed..."
                        ,Toast.LENGTH_SHORT).show();
            }
    }

    private File getFile(){
        File folder = new File("sdcard/videos");
        if (!folder.exists())
            folder.mkdir();
        File file = new File(folder,"video1.mp4");
        return file;
    }
}
