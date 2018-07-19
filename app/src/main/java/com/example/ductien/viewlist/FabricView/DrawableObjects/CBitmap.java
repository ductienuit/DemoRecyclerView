//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.ductien.viewlist.FabricView.DrawableObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class CBitmap implements CDrawable {
    private int x;
    private int y;
    private int height;
    private int width;
    private Bitmap mBitmap;
    private Paint mPaint;
    private int mRotDegree;

    public CBitmap(Bitmap src, int x, int y) {
        this(src, x, y, (Paint)null);
    }

    public CBitmap(Bitmap src, int x, int y, Paint p) {
        this.mBitmap = src;
        this.setXcoords(x);
        this.setYcoords(y);
        this.setPaint(p);
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    public void setPaint(Paint p) {
        this.mPaint = p;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public void draw(Canvas canvas) {
        Bitmap nsrc = Bitmap.createScaledBitmap(this.mBitmap, this.width, this.height, true);
        canvas.drawBitmap(nsrc, (float)this.x, (float)this.y, this.mPaint);
    }

    public int getRotation() {
        return this.mRotDegree;
    }

    public void setRotation(int degree) {
        this.mRotDegree = degree;
    }
}
