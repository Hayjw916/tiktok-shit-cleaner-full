package io.hayjw916.tiktokshitcleaner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import io.hayjw916.tiktokshitcleaner.util.AudioExtractor;

public class MainActivity extends AppCompatActivity {

    private Button tiktokButton;
    private TextView decisionText;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiktokButton = findViewById(R.id.tiktokButton);
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
        if (resultCode == -1 && requestCode == 1 && data.getData() != null) {
            uri = data.getData();
            try {
                if (uri.toString().contains("image")) {
                    decisionText.setText("This video contains an image!");
                } else {
                    decisionText.setText("You got a video");
                    new AudioExtractor().genVideoUsingMuxer(RealPathUtil.getRealPath(getApplicationContext(), uri), "bruh.mp3", -1, -1, true, false);
                    System.out.println("Got video!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            decisionText.setText("Nothing");
        }
    }

    /**
     * Didn't want to make my own, so i kinda copied it.
     * apparently it's a headache for a lot of android developers*/
    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Video.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            Log.e(MainActivity.class.getCanonicalName(), "getRealPathFromURI Exception : " + e.toString());
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    
}