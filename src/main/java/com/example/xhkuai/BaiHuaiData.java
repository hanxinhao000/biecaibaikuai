package com.example.xhkuai;

import java.util.ArrayList;

/**
 * Created by 14178 on 2018/1/4.
 */

public class BaiHuaiData {


    @Override
    public String toString() {
        return "BaiHuaiData{" +
                "arrayList=" + arrayList +
                '}';
    }

    private ArrayList<B> arrayList;

    public ArrayList<B> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<B> arrayList) {
        this.arrayList = arrayList;
    }

    /**
     * 白块存储信息
     */

    public static class B {
        private int left;
        private int top;
        private int right;
        private int bottom;
        private boolean isW = false;
        //是否点击过
        private boolean isclick = false;

        public boolean isIsclick() {
            return isclick;
        }

        public void setIsclick(boolean isclick) {
            this.isclick = isclick;
        }

        //点击过后的颜色
        private boolean clickColor = false;

        public boolean isClickColor() {
            return clickColor;
        }

        public void setClickColor(boolean clickColor) {
            this.clickColor = clickColor;
        }

        //是否是白块
        public boolean isW() {
            return isW;
        }

        public void setW(boolean w) {
            isW = w;
        }

        @Override
        public String toString() {
            return "B{" +
                    "left=" + left +
                    ", top=" + top +
                    ", right=" + right +
                    ", bottom=" + bottom +
                    '}';
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }
    }


}
