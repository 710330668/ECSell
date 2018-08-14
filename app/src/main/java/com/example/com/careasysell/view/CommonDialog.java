package com.example.com.careasysell.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.com.careasysell.R;

/**
 * Created by 71033 on 2018/7/30.
 */
public class CommonDialog extends Dialog {

    private Context context;
    private String title;
    private String confirmButtonText;
    private String cacelButtonText;
    private String content;
    private EditText etContent;
    private ClickListenerInterface clickListenerInterface;
    private boolean hasEdit = false;

    public interface ClickListenerInterface {

        public void doConfirm();

        public void doConfirm(String etContent);

        public void doCancel();
    }

    public CommonDialog(Context context, String title, String content, String confirmButtonText, String cacelButtonText) {
        super(context, 0);
        hasEdit = false;
        this.context = context;
        this.title = title;
        this.content = content;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
    }


    public CommonDialog(Context context, String title,String confirmButtonText, String cacelButtonText) {
        super(context, 0);
        hasEdit = true;
        this.context = context;
        this.title = title;
        this.content = content;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_two_btn, null);
        setContentView(view);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        Button btnConfirm = (Button) view.findViewById(R.id.btn_confirm);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        etContent = view.findViewById(R.id.et_content);

        if(hasEdit){
            etContent.setVisibility(View.VISIBLE);
            tvContent.setVisibility(View.INVISIBLE);
        }else{
            etContent.setVisibility(View.INVISIBLE);
            tvContent.setVisibility(View.VISIBLE);
        }

        tvTitle.setText(title);
        tvContent.setText(content);
        btnConfirm.setText(confirmButtonText);
        btnCancel.setText(cacelButtonText);

        btnConfirm.setOnClickListener(new clickListener());
        btnCancel.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            switch (id) {
                case R.id.btn_confirm:
                    if(hasEdit){
                        clickListenerInterface.doConfirm(etContent.getText().toString());
                    }else{
                        clickListenerInterface.doConfirm();
                    }
                    break;
                case R.id.btn_cancel:
                    clickListenerInterface.doCancel();
                    break;
            }
        }

    };


}
