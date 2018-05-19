package com.example.jiceng.a520typewriter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * 绘制爱心
 * Created by jichunzeng on 2018/5/19.
 */

public class MyLove extends View {
    Paint mPaint;
    Bitmap mBitmap;
    Canvas mCanvas;
    public MyLove(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);


        mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888); //生成一个bitmap
        mCanvas = new Canvas(mBitmap);

        new Thread() {
            @Override
            public void run() {
                super.run();
                drawLove(mCanvas);

            }
        }.start();
    }
    private void drawLove(Canvas canvas) {
        //(17*(x^2))-(16*abs(x)*y)+(17*(y^2))<255 x(-5,5) y(-5,5) (心形函数方程式)
        int loveWidth = 100;//心型宽度，必须是偶数
        int oneLine = loveWidth/2;//一条轴长度

        float scale = oneLine/5f;//实际坐标比上方程式坐标，倍数

        for (int i = 0; i < oneLine; i++) {
            for (int j = 0; j < oneLine; j++) {

                //根据表达式xy的范围，所以要把坐标系的范围也缩小
                float xf = i/scale;
                float yf = j/scale;

                if((17*Math.pow(xf, 2) - 16*Math.abs(xf)*yf+17*Math.pow(yf, 2))<255){

                    //右上1
//                  canvas.drawPoint(xf*scale+250,250+yf*scale, paint);
                    //左下2
                    canvas.drawPoint(250-xf*scale,250-yf*scale, mPaint);
//                  this.postInvalidateDelayed(10);


                    //右上1
//                  canvas.drawPoint(-xf*scale+250,250+yf*scale, paint);
                    //右下2
                    canvas.drawPoint(250+xf*scale,250-yf*scale, mPaint);
//                  this.postInvalidateDelayed(10);

//                  Log.i("TAG", "xf-->"+(xf*scale+250)+"yf-->"+(250-yf*scale));
                }

                if((17*Math.pow(xf, 2) - 16*Math.abs(xf)*(-yf)+17*Math.pow(yf, 2))<255){

                    //左上2
                    canvas.drawPoint(250-xf*scale, 250+yf*scale, mPaint);
                    //右下 1
//                  canvas.drawPoint(250+xf*scale,250-yf*scale, paint);

//                  this.postInvalidateDelayed(10);



                    //左上2
                    canvas.drawPoint(250+xf*scale, 250+yf*scale, mPaint);
                    //右下 1
//                  canvas.drawPoint(250-xf*scale,250-yf*scale, paint);

//                  this.postInvalidateDelayed(10);

                }

                //延时刷新，产生动画效果
//                delayedTime();
                this.postInvalidate();
            }
        }//end for

    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, new Matrix(), mPaint);

    }//end onDraw
    private void delayedTime() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
