package com.example.ductien.viewlist.FabricView.DrawableObjects;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface CDrawable {
    Paint getPaint();

    int getXcoords();

    int getYcoords();

    void setXcoords(int var1);

    void setYcoords(int var1);

    void setPaint(Paint var1);

    void draw(Canvas var1);

    int getRotation();

    void setRotation(int var1);
}
