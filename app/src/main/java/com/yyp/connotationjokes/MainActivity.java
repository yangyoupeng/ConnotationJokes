package com.yyp.connotationjokes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yyp.baselibrary.OnClick;
import com.yyp.baselibrary.ViewById;
import com.yyp.baselibrary.ViewUtils;

public class MainActivity extends AppCompatActivity {
    
    
    @ViewById(R.id.tv_text)
    private TextView mTvText;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ViewUtils.inject (this);
        mTvText.setText ("成功");
    }
    @OnClick ({R.id.tv_text})
    private void onClick(View view){
        Toast.makeText (this, "tv我被点击了", Toast.LENGTH_SHORT).show ();
        
    }
    
}
