package com.example.lenovo.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.R;

public class AddMinusNum extends LinearLayout {

    private TextView minus;
    private EditText ed_num;
    private TextView add;
    private int num;

    public AddMinusNum(Context context) {
        this(context,null);
    }

    public AddMinusNum(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddMinusNum(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_minus_item, this);
        minus = view.findViewById(R.id.minus);
        ed_num = view.findViewById(R.id.ed_num);
        add = view.findViewById(R.id.add);
        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num=Integer.parseInt(ed_num.getText().toString());
                num--;
                if (num==0){
                    num=1;
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }
                ed_num.setText(num+"");
                addMinusNums.addMinusNum(num);
            }
        });
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num=Integer.parseInt(ed_num.getText().toString());
                num++;
                ed_num.setText(num+"");
                addMinusNums.addMinusNum(num);
            }
        });
    }

    public void setNum(int num) {
        ed_num.setText(num+"");
    }

    private AddMinusNums addMinusNums;

    public void setAddMinusNums(AddMinusNums addMinusNums) {
        this.addMinusNums = addMinusNums;
    }

    public interface AddMinusNums{
        void addMinusNum(int num);
    }
}
