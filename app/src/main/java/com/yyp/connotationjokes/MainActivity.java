package com.yyp.connotationjokes;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yyp.baselibrary.dialog.AlertDialog;
import com.yyp.framelibrary.BaseSkinActivity;
import com.yyp.framelibrary.DefulterNavigationBar;

public class MainActivity extends BaseSkinActivity implements View.OnClickListener {
    
    
    @Override
    protected void initTitle() {
            DefulterNavigationBar defulterNavigationBar = new DefulterNavigationBar
                .Builder (this)
                .setTitle ("成功")
                .setRightText ("提交")
                .setRightClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText (MainActivity.this, "提交成功", Toast.LENGTH_SHORT).show ();
                    }
             })
               .builder ();
    }
    
    @Override
    protected void initView() {
        Button viewById = viewById (R.id.bt);
         viewById.setOnClickListener (this);
      
    }
    
    @Override
    protected void initData() {
        
    }
    
    @Override
    protected void setContentView() {
        setContentView (R.layout.activity_main);
    }
    
    
    @Override
    public void onClick(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder (this)
                .setLayoutView (R.layout.detail_comment_dialog)
                .setFormBottom (true)
                .setFullWidth ()
                .show ();
        
        
        final EditText editTextContent = alertDialog.getView (R.id.comment_editor);
        alertDialog.setOnClickListener (R.id.submit_btn, new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (MainActivity.this, editTextContent.getText ().toString ().trim ()
                        , Toast.LENGTH_SHORT).show ();
            }
        });
    }
    
    
}
