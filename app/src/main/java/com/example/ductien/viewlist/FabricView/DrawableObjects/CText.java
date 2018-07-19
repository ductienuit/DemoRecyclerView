package com.example.ductien.viewlist.FabricView.DrawableObjects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class CText implements CDrawable {
    private String mText;
    private Paint mPaint;
    private int x;
    private int y;
    private int mRotDegree;

    public CText(String s, int x, int y, Paint p) {
        this.setText(s);
        this.setYcoords(y);
        this.setXcoords(x);
        this.setPaint(p);
    }

    public void setText(String t) {
        this.mText = t;
    }

    public String getText() {
        return this.mText;
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
        canvas.drawText(this.getText(), (float)this.getXcoords(), (float)this.getYcoords(), this.mPaint);
    }

    public int getRotation() {
        return this.mRotDegree;
    }

    public void setRotation(int degree) {
        this.mRotDegree = degree;
    }
}
