package com.example.ductien.viewlist.FabricView.DrawableObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class CPath implements CDrawable {
    private int x;
    private int y;
    private Path mPath = new Path();
    private Paint mPaint;
    private int mRotDegree;

    public CPath() {
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    public int getXcoords() {
        return this.x;
    }

    public int getYcoords() {
        return this.y;
    }

    public void setXcoords(int x) {
        this.x = x;
    }

    public void setYcoords(int y) {
        this.y = y;
    }

    public void setPaint(Paint p) {
        this.mPaint = p;
    }

    public void draw(Canvas canvas) {
        canvas.drawPath(this.mPath, this.mPaint);
    }

    public int getRotation() {
        return this.mRotDegree;
    }

    public void setRotation(int degree) {
        this.mRotDegree = degree;
    }

    public void lineTo(float eventX, float eventY) {
        this.mPath.lineTo(eventX, eventY);
    }

    public void moveTo(float eventX, float eventY) {
        this.mPath.moveTo(eventX, eventY);
    }
}
