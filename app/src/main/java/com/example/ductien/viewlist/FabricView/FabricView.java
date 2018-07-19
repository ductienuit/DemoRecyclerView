//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.ductien.viewlist.FabricView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.ductien.viewlist.FabricView.DrawableObjects.CBitmap;
import com.example.ductien.viewlist.FabricView.DrawableObjects.CDrawable;
import com.example.ductien.viewlist.FabricView.DrawableObjects.CPath;
import com.example.ductien.viewlist.FabricView.DrawableObjects.CText;

import java.util.ArrayList;

public class FabricView extends View {
    private ArrayList<CDrawable> mDrawableList = new ArrayList();
    private int mColor = -16777216;
    private int mInteractionMode = 0;
    private int mBackgroundColor = -1;
    private Style mStyle;
    private float mSize;
    private boolean mRedrawBackground;
    private int mBackgroundMode;
    public static final int NOTEBOOK_LEFT_LINE_COLOR = -65536;
    private boolean mTextExpectTouch;
    private float lastTouchX;
    private float lastTouchY;
    private final RectF dirtyRect;
    CPath currentPath;
    Paint currentPaint;
    public static final int BACKGROUND_STYLE_BLANK = 0;
    public static final int BACKGROUND_STYLE_NOTEBOOK_PAPER = 1;
    public static final int BACKGROUND_STYLE_GRAPH_PAPER = 2;
    public static final int DRAW_MODE = 0;
    public static final int SELECT_MODE = 1;
    public static final int ROTATE_MODE = 2;
    public static final int LOCKED_MODE = 3;
    public static final int NOTEBOOK_LEFT_LINE_PADDING = 120;
    private float mZoomLevel;
    private float mHorizontalOffset;
    private float mVerticalOffset;
    public int mAutoscrollDistance;

    public FabricView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mStyle = Style.STROKE;
        this.mSize = 5.0F;
        this.mBackgroundMode = 0;
        this.dirtyRect = new RectF();
        this.mZoomLevel = 1.0F;
        this.mHorizontalOffset = 1.0F;
        this.mVerticalOffset = 1.0F;
        this.mAutoscrollDistance = 100;
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setBackgroundColor(this.mBackgroundColor);
        this.mTextExpectTouch = false;
    }

    protected void onDraw(Canvas canvas) {
        this.drawBackground(canvas, this.mBackgroundMode);

        for(int i = 0; i < this.mDrawableList.size(); ++i) {
            try {
                ((CDrawable)this.mDrawableList.get(i)).draw(canvas);
            } catch (Exception var4) {
                ;
            }
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.getInteractionMode() == 0?this.onTouchDrawMode(event):(this.getInteractionMode() == 1?this.onTouchSelectMode(event):(this.getInteractionMode() == 2?this.onTouchRotateMode(event):this.onTouchLockedMode(event)));
    }

    private boolean onTouchLockedMode(MotionEvent event) {
        return false;
    }

    private boolean onTouchRotateMode(MotionEvent event) {
        return false;
    }

    public boolean onTouchDrawMode(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch(event.getAction()) {
        case 0:
            this.currentPath = new CPath();
            this.currentPaint = new Paint();
            this.currentPaint.setAntiAlias(true);
            this.currentPaint.setColor(this.mColor);
            this.currentPaint.setStyle(this.mStyle);
            this.currentPaint.setStrokeJoin(Join.ROUND);
            this.currentPaint.setStrokeWidth(this.mSize);
            this.currentPath.moveTo(eventX, eventY);
            this.currentPath.setPaint(this.currentPaint);
            this.lastTouchX = eventX;
            this.lastTouchY = eventY;
            this.mDrawableList.add(this.currentPath);
            return true;
        case 1:
        case 2:
            this.currentPath.lineTo(eventX, eventY);
            int historySize = event.getHistorySize();

            for(int i = 0; i < historySize; ++i) {
                float historicalX = event.getHistoricalX(i);
                float historicalY = event.getHistoricalY(i);
                if(historicalX < this.dirtyRect.left) {
                    this.dirtyRect.left = historicalX;
                } else if(historicalX > this.dirtyRect.right) {
                    this.dirtyRect.right = historicalX;
                }

                if(historicalY < this.dirtyRect.top) {
                    this.dirtyRect.top = historicalY;
                } else if(historicalY > this.dirtyRect.bottom) {
                    this.dirtyRect.bottom = historicalY;
                }

                this.currentPath.lineTo(historicalX, historicalY);
            }

            this.currentPath.lineTo(eventX, eventY);
            this.cleanDirtyRegion(eventX, eventY);
            this.invalidate((int)(this.dirtyRect.left - 20.0F), (int)(this.dirtyRect.top - 20.0F), (int)(this.dirtyRect.right + 20.0F), (int)(this.dirtyRect.bottom + 20.0F));
            this.lastTouchX = eventX;
            this.lastTouchY = eventY;
            return true;
        default:
            return false;
        }
    }

    private boolean onTouchSelectMode(MotionEvent event) {
        return false;
    }

    public void drawBackground(Canvas canvas, int backgroundMode) {
        canvas.drawColor(this.mBackgroundColor);
        if(backgroundMode != 0) {
            Paint linePaint = new Paint();
            linePaint.setColor(Color.argb(50, 0, 0, 0));
            linePaint.setStyle(this.mStyle);
            linePaint.setStrokeJoin(Join.ROUND);
            linePaint.setStrokeWidth(this.mSize - 2.0F);
            switch(backgroundMode) {
            case 1:
                this.drawNotebookPaperBackground(canvas, linePaint);
                break;
            case 2:
                this.drawGraphPaperBackground(canvas, linePaint);
            }
        }

        this.mRedrawBackground = false;
    }

    private void drawGraphPaperBackground(Canvas canvas, Paint paint) {
        int i = 0;
        boolean doneH = false;

        for(boolean doneV = false; !doneH || !doneV; i += 75) {
            if(i < canvas.getHeight()) {
                canvas.drawLine(0.0F, (float)i, (float)canvas.getWidth(), (float)i, paint);
            } else {
                doneH = true;
            }

            if(i < canvas.getWidth()) {
                canvas.drawLine((float)i, 0.0F, (float)i, (float)canvas.getHeight(), paint);
            } else {
                doneV = true;
            }
        }

    }

    private void drawNotebookPaperBackground(Canvas canvas, Paint paint) {
        int i = 0;

        for(boolean doneV = false; !doneV; i += 75) {
            if(i < canvas.getHeight()) {
                canvas.drawLine(0.0F, (float)i, (float)canvas.getWidth(), (float)i, paint);
            } else {
                doneV = true;
            }
        }

        paint.setColor(-65536);
        canvas.drawLine(120.0F, 0.0F, 120.0F, (float)canvas.getHeight(), paint);
    }

    public void drawText(String text, int x, int y, Paint p) {
        this.mDrawableList.add(new CText(text, x, y, p));
        this.invalidate();
    }

    private void drawTextFromKeyboard() {
        Toast.makeText(this.getContext(), "Touch where you want the text to be", Toast.LENGTH_LONG).show();
        this.mTextExpectTouch = true;
    }

    private void cleanDirtyRegion(float eventX, float eventY) {
        this.dirtyRect.left = Math.min(this.lastTouchX, eventX);
        this.dirtyRect.right = Math.max(this.lastTouchX, eventX);
        this.dirtyRect.top = Math.min(this.lastTouchY, eventY);
        this.dirtyRect.bottom = Math.max(this.lastTouchY, eventY);
    }

    public void cleanPage() {
        while(!this.mDrawableList.isEmpty()) {
            this.mDrawableList.remove(0);
        }

        this.invalidate();
    }

    public void drawImage(int x, int y, int width, int height, Bitmap pic) {
        CBitmap bitmap = new CBitmap(pic, x, y);
        bitmap.setWidth(width);
        bitmap.setHeight(height);
        this.mDrawableList.add(bitmap);
        this.invalidate();
    }

    public Bitmap getCanvasBitmap() {
        this.buildDrawingCache();
        Bitmap mCanvasBitmap = Bitmap.createBitmap(this.getDrawingCache());
        this.destroyDrawingCache();
        return mCanvasBitmap;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public int getBackgroundMode() {
        return this.mBackgroundMode;
    }

    public void setBackgroundMode(int mBackgroundMode) {
        this.mBackgroundMode = mBackgroundMode;
        this.invalidate();
    }

    public void setBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }

    public Style getStyle() {
        return this.mStyle;
    }

    public void setStyle(Style mStyle) {
        this.mStyle = mStyle;
    }

    public float getSize() {
        return this.mSize;
    }

    public void setSize(float mSize) {
        this.mSize = mSize;
    }

    public int getInteractionMode() {
        return this.mInteractionMode;
    }

    public void setInteractionMode(int interactionMode) {
        if(interactionMode > 3) {
            interactionMode = 3;
        } else if(interactionMode < 0) {
            interactionMode = 3;
        }

        this.mInteractionMode = interactionMode;
    }
}

