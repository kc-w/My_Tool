package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.*;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;


    /** 当活动第一次被创建时调用 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载UI组件
        setContentView(R.layout.activity_main);


        mContext = MainActivity.this;
        list_animal = (ListView) findViewById(R.id.ListView1);


        //动态加载顶部View和底部View
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.view_header, null, false);
        View footView = inflater.inflate(R.layout.view_footer, null, false);


        mData = new LinkedList<Animal>();
        mData.add(new Animal("狗说", "你是狗么?", R.drawable.image1));
        mData.add(new Animal("牛说", "你是牛么?", R.drawable.image1));
        mData.add(new Animal("鸭说", "你是鸭么?", R.drawable.image1));
        mData.add(new Animal("鱼说", "你是鱼么?", R.drawable.image1));
        mData.add(new Animal("马说", "你是马么?", R.drawable.image1));
        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);


        //添加表头和表尾需要写在setAdapter方法调用之前！！！
        list_animal.addHeaderView(headView);
        list_animal.addFooterView(footView);
        //将数据装入ListView中
        list_animal.setAdapter(mAdapter);

        //为ListView设置点击监听
        list_animal.setOnItemClickListener( this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext,"你点击了第" + position + "项",Toast.LENGTH_SHORT).show();
    }

}
