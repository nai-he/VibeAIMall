package com.asyyy.shixun;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
//显示一个带有标题、消息、取消按钮和确认按钮的对话框。
//这个对话框类继承自Dialog并实现了View.OnClickListener接口，以便处理按钮点击事件。

public class MyDialog extends Dialog implements View.OnClickListener {

//    MyDialog 类继承了 Dialog 并实现了 View.OnClickListener 接口。
//    定义了一些私有成员变量来存储对话框的标题、消息、取消按钮文本、确认按钮文本以及相应的监听器。
//    还定义了两个 TextView 和两个 View 对象用于显示标题、消息以及处理按钮点击事件。!
    private TextView mTitle,mMessage,mCancel,mConfirm;
    private String title,message,cancel,confirm;

    private MyOnCancelListener cancelListener;
    private MyOnConfirmListener confirmListener;

//    public CustomerDialog(@NonNull Context context) {
//        super(context);
//    }


    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog);

        //设置宽度
        WindowManager manager = getWindow().getWindowManager();
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        Point point = new Point();
        display.getSize(point);
        attributes.width = (int)(point.x * 0.8);//设置Dialog的宽度为当前手机屏幕宽度的0.8
        getWindow().setAttributes(attributes);

        //getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置Dialog背景透明
        //getWindow().setDimAmount(0f);//设置Dialog窗口后面的透明度
//        查找并初始化对话框中的视图元素（标题、消息、取消按钮、确认按钮）。!
        mTitle = findViewById(R.id.dialog_title);
        mMessage = findViewById(R.id.dialog_message);
        mCancel = findViewById(R.id.dialog_cancel);
        mConfirm = findViewById(R.id.dialog_confirm);

       // 根据传入的值设置这些视图元素的文本内容。!
        if (!title.isEmpty()){
            mTitle.setText(title);
        }
        if (!message.isEmpty()){
            mMessage.setText(message);
        }
        if (!cancel.isEmpty()){
            mCancel.setText(cancel);
        }
        if (!confirm.isEmpty()){
            mConfirm.setText(confirm);
        }

        mCancel.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
    }

    public MyDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public MyDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public MyDialog setCancel(String cancel, MyOnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener = listener;
        return this;
    }

//    提供链式调用的方法来设置取消按钮文本及其监听器。!
    public MyDialog setConfirm(String confirm, MyOnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener = listener;
        return this;
    }


//    实现 View.OnClickListener 接口的 onClick 方法，根据点击的视图 ID
//        执行相应的操作。如果点击的是取消按钮且有设置监听器，则调用 onCancel 方法；
//    如果点击的是确认按钮且有设置监听器，则调用 onConfirm 方法。!
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_cancel:
                if (cancelListener != null){
                    cancelListener.onCancel(this);
                }
                break;
            case R.id.dialog_confirm:
                if (confirmListener != null){
                    confirmListener.onConfirm(this);
                }
                break;
        }
    }

    //点击事件接口

//    定义一个函数式接口 MyOnCancelListener，用于处理取消按钮的点击事件。!
    @FunctionalInterface
    public interface MyOnCancelListener{
        void onCancel(MyDialog dialog);
    }


//    定义另一个函数式接口 MyOnConfirmListener，用于处理确认按钮的点击事件。!
    @FunctionalInterface
    public interface MyOnConfirmListener{
        void onConfirm(MyDialog dialog);
    }
}
