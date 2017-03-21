package com.yyp.connotationjokes;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yyp.baselibrary.dialog.AlertDialog;
import com.yyp.baselibrary.ioc.OnClick;
import com.yyp.baselibrary.ioc.ViewById;
import com.yyp.framelibrary.BaseSkinActivity;

public class MainActivity extends BaseSkinActivity {
    @ViewById(R.id.bt)
    private Button bt;
    @ViewById(R.id.iv_dialog)
    private ImageView mIvDialog;
    /****dialog显示成功****/
    @ViewById(R.id.tv_dialog)
    private TextView mTvDialog;
    
    
    @Override
    protected void initTitle() {
        
    }
    
    @Override
    protected void initView() {
        
    }
    
    @Override
    protected void initData() {
        
    }
    
    @Override
    protected void setContentView() {
        setContentView (R.layout.activity_main);
        //        startActivity (MainActivity.class);
    }
    
    
    @OnClick(R.id.bt)
    public void onClick(View v) {
        new AlertDialog.Builder (this).setContentView (R.layout.dialog_test_layout).setText (R.id
                .tv_dialog, "显示修改成功").setOnClickListener (R.id.iv_dialog, new View
                .OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (MainActivity.this, "嗨，点击成功了", Toast.LENGTH_LONG).show ();
            }
        }).show ();
    }
}
