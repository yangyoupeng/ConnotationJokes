package com.yyp.connotationjokes;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yyp.baselibrary.dialog.AlertDialog;
import com.yyp.framelibrary.BaseSkinActivity;

public class MainActivity extends BaseSkinActivity implements View.OnClickListener {
    
    
    @Override
    protected void initTitle() {
        
    }
    
    @Override
    protected void initView() {
        Button button = viewById (R.id.bt);
        button.setOnClickListener (this);
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
        AlertDialog alertDialog = new AlertDialog.Builder (this).setLayoutView (R.layout
                .detail_comment_dialog).setFormBottom (true).setFullWidth ().show ();
        
        
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
