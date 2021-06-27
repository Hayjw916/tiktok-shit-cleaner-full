package io.hayjw916.tiktokshitcleaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button tiktokButton;
    private Button viewTiktokButton;
    private TextView decisionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiktokButton = findViewById(R.id.tiktokButton);
        viewTiktokButton = findViewById(R.id.viewTiktokButton);
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


}