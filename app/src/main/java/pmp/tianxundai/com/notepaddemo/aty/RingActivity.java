package pmp.tianxundai.com.notepaddemo.aty;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pmp.tianxundai.com.notepaddemo.R;

public class RingActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        mediaPlayer = MediaPlayer.create(this, R.raw.rooster);
        mediaPlayer.start();
    }
    public void stop(View view){
        mediaPlayer.stop();
        finish();
    }
}
