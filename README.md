# biecaibaikuai
![1515047030551mzbau.gif](http://upload-images.jianshu.io/upload_images/5337239-7413878bec71e8a6.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



使用方法


XML布局

```

        <com.example.xhkuai.XHbaiKuai


            android:id="@+id/xhbaobaikuai"
            android:layout_width="match_parent"
            android:layout_height="match_parent"

            ></com.example.xhkuai.XHbaiKuai>
```

//JAVA代码
```
 xhbaobaikuai = findViewById(R.id.xhbaobaikuai);

        //设置本地Activity
        xhbaobaikuai.setActivity(this);
        //开始游戏  注意:不调用该方法游戏不会启动
        xhbaobaikuai.startMessage();
```
