package com.bawei.twotext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Bonnenu1t丶 on 2017/6/11.
 */

public class SecondActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView tv = (TextView) findViewById(R.id.tabber_title);
        ImageView iv = (ImageView) findViewById(R.id.detatil_iv);
        TextView content = (TextView) findViewById(R.id.detatil_content);
        Intent intent = getIntent();
        String content1 = intent.getStringExtra("content");
        content.setText(content1);
        String name = intent.getStringExtra("name");
        tv.setText(name + "的信息");
        Glide.with(this).load(intent.getStringExtra("image")).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(iv);

    }
}
