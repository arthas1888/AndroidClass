package com.example.a68.viewapplication.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.a68.viewapplication.R;

public class CustomView extends View {
    //circle and text colors
    private int squareCol, labelCol;
    //label text
    private String squareText;
    //paint for drawing custom view
    private Paint squarePaint;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);


        //paint object for drawing in onDraw
        squarePaint = new Paint();
        //get the attributes specified in attrs.xml
        //using the name you included
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CustomView, 0, 0);
        try {
            //get the text and colors specified using
            // the names in attrs.xml
            squareText = a.getString(R.styleable.CustomView_squareLabel);
            squareCol = a.getInteger(R.styleable.CustomView_squareColor, 0);//0 is default
            labelCol = a.getInteger(R.styleable.CustomView_labelColor, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //set drawing properties
        squarePaint.setStyle(Paint.Style.FILL);
        squarePaint.setAntiAlias(true);

//set the paint color using the circle color specified
        squarePaint.setColor(squareCol);

//draw the Square using the properties defined
        canvas.drawRect(0, 0, this.getMeasuredWidth(),
                this.getMeasuredHeight(), squarePaint);

//set the text color using the color specified
        squarePaint.setColor(labelCol);

//set text properties
        squarePaint.setTextAlign(Paint.Align.CENTER);
        squarePaint.setTextSize(50);

//draw the text using the string attribute and chosen properties
        canvas.drawText(squareText, this.getMeasuredWidth() / 2, this.getMeasuredHeight() / 2, squarePaint);
    }

    public int getSquareColor(){
        return squareCol;
    }
    public void setSquareColor(int newColor){
        //update the instance variable
        squareCol=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }
    public int getLabelColor(){
        return labelCol;
    }
    public void setLabelColor(int newColor){
        //update the instance variable
        labelCol=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }
    public String getLabelText(){
        return squareText;
    }
    public void setLabelText(String newLabel){
        //update the instance variable
        squareText=newLabel;
        //redraw the view
        invalidate();
        requestLayout();
    }
}
