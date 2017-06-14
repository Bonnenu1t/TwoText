package com.bawei.twotext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bonnenu1t丶 on 2017/6/11.
 */

public class MyActivity extends Activity{

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Bean.StudentsBean.StudentBean> list=new ArrayList<Bean.StudentsBean.StudentBean>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);

        progressBar = (ProgressBar) findViewById(R.id.bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        adapter = new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(MyActivity.this, SecondActivity.class);
                intent.putExtra("content", list.get(postion).getContent());
                intent.putExtra("name", list.get(postion).getName());
                intent.putExtra("image", list.get(postion).getImg());
                startActivity(intent);
            }
        });

        //设置布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        OkHttpUtils.getInstance().getAsyn("http://result.eolinker.com/Ikx4fDc398b93bfd0c8a146468e1f8cddfe9166719cba13?uri=zhoukao", null, Bean.class, new OkHttpUtils.HttpCallBack<Bean>() {

            @Override
            public void onSuccess(Bean bean) {
                List<Bean.StudentsBean.StudentBean> data = bean.getStudents().getStudent();
                list.addAll(data);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(IOException e) {

            }
        });
    }
}
