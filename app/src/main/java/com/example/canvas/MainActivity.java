package com.example.canvas;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canvas.DrawingView;

public class MainActivity extends AppCompatActivity {
    private DrawingView drawingView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);

        Button btnBlack = findViewById(R.id.btnBlack);
        Button btnRed = findViewById(R.id.btnRed);
        Button btnBlue = findViewById(R.id.btnBlue);
        Button btnYellow = findViewById(R.id.btnYellow);
        Button btnGreen = findViewById(R.id.btnGreen);
        Button btnPink = findViewById(R.id.btnPink);
        Button btnThin = findViewById(R.id.btnThin);
        Button btnMedium = findViewById(R.id.btnMedium);
        Button btnThick = findViewById(R.id.btnThick);
        Button btnClear = findViewById(R.id.btnClear);

        // Đổi màu
        btnBlack.setOnClickListener(v -> drawingView.setColor(Color.BLACK));
        btnRed.setOnClickListener(v -> drawingView.setColor(Color.RED));
        btnBlue.setOnClickListener(v -> drawingView.setColor(Color.BLUE));
        btnYellow.setOnClickListener(v -> drawingView.setColor(Color.YELLOW));
        btnGreen.setOnClickListener(v -> drawingView.setColor(Color.GREEN));
        btnPink.setOnClickListener(v -> drawingView.setColor(Color.MAGENTA));

        // Đổi độ dày
        btnThin.setOnClickListener(v -> drawingView.setStrokeWidth(5f));
        btnMedium.setOnClickListener(v -> drawingView.setStrokeWidth(10f));
        btnThick.setOnClickListener(v -> drawingView.setStrokeWidth(20f));

        // Xóa canvas
        btnClear.setOnClickListener(v -> drawingView.clearCanvas());
    }
}
