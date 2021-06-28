package io.hayjw916.tiktokshitcleaner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private Button tiktokButton;
    private VideoView videoView;
    private TextView decisionText;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiktokButton = findViewById(R.id.tiktokButton);
        videoView = findViewById(R.id.videoView);
        decisionText = findViewById(R.id.decisionText);

        tiktokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/mp4");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 && requestCode == RESULT_OK && data.getData() != null) {
            uri = data.getData();
            try {
                if (uri.toString().contains("image")) {
                    decisionText.setText("This video contains an image!");
                }
                videoView.setVideoPath(uri.toString());
                videoView.start();
            } catch (Exception e) {

            }
        }
    }
}