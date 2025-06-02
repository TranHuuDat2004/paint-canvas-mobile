package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {
    private Paint currentPaint; // Paint hiện tại (mới nhất)
    private Path currentPath;   // Path hiện tại (mới nhất)

    // Lưu danh sách nét vẽ (mỗi nét vẽ có Path và Paint riêng)
    private List<Stroke> strokes = new ArrayList<>();

    private int currentColor = Color.BLACK;
    private float currentStrokeWidth = 10f;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        currentPaint = new Paint();
        currentPaint.setColor(currentColor);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(currentStrokeWidth);
        currentPaint.setAntiAlias(true);

        currentPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Vẽ tất cả các nét vẽ đã lưu trong danh sách
        for (Stroke stroke : strokes) {
            canvas.drawPath(stroke.path, stroke.paint);
        }
        // Vẽ nét vẽ hiện tại
        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Tạo path mới cho nét vẽ mới
                currentPath = new Path();
                currentPath.moveTo(x, y);
                // Tạo bản sao của Paint để lưu lại trạng thái riêng cho nét vẽ
                Paint newPaint = new Paint(currentPaint);
                strokes.add(new Stroke(currentPath, newPaint));
                invalidate();
                return true;

            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(x, y);
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                performClick();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    // Cập nhật màu sắc (cho nét vẽ tiếp theo)
    public void setColor(int color) {
        currentColor = color;
        currentPaint.setColor(currentColor);
    }

    // Cập nhật độ dày (cho nét vẽ tiếp theo)
    public void setStrokeWidth(float width) {
        currentStrokeWidth = width;
        currentPaint.setStrokeWidth(currentStrokeWidth);
    }

// Xóa tất cả nét vẽ
public void clearCanvas() {
    strokes.clear();
    currentPath.reset(); // Xóa nội dung của currentPath thay vì tạo mới
    invalidate();
}

    // Class lưu trữ mỗi nét vẽ gồm Path + Paint
    private static class Stroke {
        Path path;
        Paint paint;

        Stroke(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
    }
}