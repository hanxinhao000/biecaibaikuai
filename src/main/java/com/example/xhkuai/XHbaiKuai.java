package com.example.xhkuai;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * 信号别踩白块
 */

public class XHbaiKuai extends View {

    private Context context;
    private Activity activity;
    //屏幕宽
    private int h;
    //屏幕高
    private int w;
    //每个方块的宽
    private int cw;
    //每个方块的高
    private int ch;

    //控制多少行
    private int conut = 0;

    //画笔
    private Paint paint;

    //控制滚动
    private int upLoad = -2;

    //主线程切换标记
    private Handler handler;

    //随机数
    private Random random;

    private Paint textPaint;

    //遗漏的方块

    private int rect = 0;
    private int rectR = 0;

    //得分

    private int fNum = 0;
    ArrayList<BaiHuaiData> arrayList;
    private float xClick = -1;
    private float yClick = -1;

    public XHbaiKuai(Context context) {
        super(context);
        initView(context);
    }

    public XHbaiKuai(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XHbaiKuai(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        /**
         * 初始化信息
         */
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        handler = new Handler();
        arrayList = new ArrayList<>();
        //最开始默认添加4个
        textPaint = new Paint();
        random = new Random();
        Log.e("-------", "initView: " + "初始化完成" + arrayList.toString());
        textPaint.setColor(Color.parseColor("#f25843"));
        textPaint.setTextSize(50);
        textPaint.setAntiAlias(true);
        invalidate();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 这块保留方便你理解
         */

       /* for (int i = 0; i < 4; i++) {
            //横向画4个
            for (int j = 0; j < 4; j++) {
                drawR(canvas, j * cw, (i * ch) + upLoad, (j * cw) + cw, ((i * ch) + ch) + upLoad);

            }
        }*/

        try {

            if (arrayList != null)

                for (int i = 0; i < arrayList.size(); i++) {

                    for (int j = 0; j < arrayList.get(i).getArrayList().size(); j++) {


                        drawR(canvas, arrayList.get(i).getArrayList().get(j).getLeft(),
                                arrayList.get(i).getArrayList().get(j).getTop(),
                                arrayList.get(i).getArrayList().get(j).getRight(),
                                arrayList.get(i).getArrayList().get(j).getBottom(),
                                arrayList.get(i).getArrayList().get(j).isW(),
                                arrayList.get(i).getArrayList().get(j).isClickColor()
                        );


                    }


                }

            //画数字
            canvas.drawText("当前得分:" + fNum, 20, 100, textPaint);
            canvas.drawText("最小化得分(越小越好):" + rect, 20, 150, textPaint);
            canvas.drawText("XINHAO_HAN", 20, 50, textPaint);
            canvas.drawText("分数计时器" + rectR, 20, 200, textPaint);
            canvas.drawText("点击次数" + onClickR, 20, 250, textPaint);

        } catch (Exception e) {

        }

    }

    //判断是否有遗漏的地方

    private void yiLou() {

        for (int i = 0; i < arrayList.size(); i++) {

            ArrayList<BaiHuaiData.B> arrayListB = this.arrayList.get(i).getArrayList();

            for (int j = 0; j < arrayListB.size(); j++) {

                if (arrayListB.get(j).isW()) {

                    if (!(arrayListB.get(j).isIsclick())) {
                        rect++;
                    }


                }


            }

        }


    }


    private void yiLou_min() {

        for (int i = 0; i < arrayList.size(); i++) {

            ArrayList<BaiHuaiData.B> arrayListB = this.arrayList.get(i).getArrayList();

            for (int j = 0; j < arrayListB.size(); j++) {

                if (arrayListB.get(j).isW()) {

                    if (!(arrayListB.get(j).isIsclick())) {
                        rectR++;
                    }


                }


            }

        }


    }


    //画有边缘方块[有细线]
    private void drawR(Canvas canvas, int left, int top, int right, int bottom, boolean isT, boolean isClick) {

        //防止沾在一起,不好看
        paint.setColor(Color.parseColor("#ffffff"));
        //画白的
        canvas.drawRect(left, top, right, bottom, paint);
        //----------------------------------------------------------
        if (isT) {
            paint.setColor(Color.parseColor(isClick ? "#f0f0f0" : "#000000"));
        } else {
            paint.setColor(Color.parseColor("#ffffff"));
        }

        //画黑的
        canvas.drawRect(left + 4, top + 4, right - 4, bottom - 4, paint);


    }

    /**
     * 设置Activity,并且获取相对应的长宽高
     *
     * @param activity
     */
    public void setActivity(Activity activity) {

        this.activity = activity;
    }

    //开始初始化

    public void startMessage() {
        w = getW(activity);
        h = getH(activity);
        startW();
        initData();
        invalidate();
        newThread();
    }

    //初始化数据

    private void initData() {

        for (int i = 0; i < 5; i++) {
            BaiHuaiData baiHuaiData = new BaiHuaiData();
            ArrayList<BaiHuaiData.B> arrayListB = new ArrayList<>();
            baiHuaiData.setArrayList(arrayListB);
            int i1 = random.nextInt(4);
            for (int j = 0; j < 5; j++) {
                BaiHuaiData.B b = new BaiHuaiData.B();
                b.setLeft(j * cw);
                b.setTop(i * ch);
                b.setRight((j * cw) + cw);
                b.setBottom((i * ch) + ch);

                if (i == i1) {
                    b.setW(true);
                } else {
                    b.setW(false);
                }

                arrayListB.add(b);
            }

            arrayList.add(baiHuaiData);
        }
    }

    //如果数据超出屏幕边缘就移除第一个,添加最后一个


    private void addDataOrRomeData() {

        if (arrayList.get(0).getArrayList().get(0).getTop() < -ch) {
            yiLou_min();
            Log.e("顶部", "addDataOrRomeData: " + arrayList.get(0).getArrayList().get(0).getTop());

            //第一排
            BaiHuaiData baiHuaiData1 = this.arrayList.get(0);

            ArrayList<BaiHuaiData.B> arrayListB = baiHuaiData1.getArrayList();
            //最后一排
            BaiHuaiData baiHuaiData = this.arrayList.get(this.arrayList.size() - 1);

            ArrayList<BaiHuaiData.B> arrayListBLast = baiHuaiData.getArrayList();
            int i1 = random.nextInt(4);
            for (int i = 0; i < arrayListB.size(); i++) {


                arrayListB.get(i).setTop(arrayListBLast.get(i).getTop() + ch);
                arrayListB.get(i).setBottom(arrayListBLast.get(i).getBottom() + ch);

                arrayListB.get(i).setClickColor(false);
                if (i1 == i) {
                    arrayListB.get(i).setW(true);
                } else {
                    arrayListB.get(i).setW(false);
                }

            }


            this.arrayList.remove(0);
            this.arrayList.add(baiHuaiData1);
        }


    }

    //更改数值

    private void changeData() {


        for (int i = 0; i < arrayList.size(); i++) {

            ArrayList<BaiHuaiData.B> arrayList = this.arrayList.get(i).getArrayList();
            for (int j = 0; j < arrayList.size(); j++) {


                arrayList.get(j).setBottom(arrayList.get(j).getBottom() + upLoad);
                arrayList.get(j).setTop(arrayList.get(j).getTop() + upLoad);


                if (arrayList.get(j).isW()) {
                    //必须是系统刷出来的黑块
                    int top = arrayList.get(j).getTop();
                    int bottom = arrayList.get(j).getBottom();
                    int left = arrayList.get(j).getLeft();
                    int right = arrayList.get(j).getRight();


                    if (xClick > left && xClick < right && yClick > top && yClick < bottom) {
                        arrayList.get(j).setClickColor(true);
                        arrayList.get(j).setIsclick(true);
                        fNum++;
                        xClick = -1;
                        yClick = -1;
                    }


                }

            }

        }


    }


    //获取屏幕的宽高

    //启动线程
    public void newThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    isClick();
                    addDataOrRomeData();
                    changeData();
                    yiLou();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            invalidate();
                        }
                    });
                }
            }
        }).start();

    }

    //判断点击
    private void isClick() {


    }

    private int onClickR = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:


                xClick = event.getX();
                yClick = event.getY();
                onClickR++;

                break;

        }

        return true;
    }

    //子线程判断


    //宽
    private int getW(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics.widthPixels;

    }

    //高
    private int getH(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics.heightPixels;
    }

    //开始计算屏幕的宽度,并且如何去画相对应的的线条与方块

    private void startW() {
        //一个屏幕分为4个方块
        cw = w / 4;
        ch = h / 4;


    }
}
