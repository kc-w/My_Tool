package com.example.myapplication;


import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;



//活动
public class MainActivity extends AppCompatActivity {


    /** 当活动第一次被创建时调用 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载UI组件
        setContentView(R.layout.activity_main);

        //创建ArrayAdapter(this,xml中定义的数组,ListView显示样式)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.myarray,android.R.layout.simple_expandable_list_item_1 );
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView ListView1 = (ListView) findViewById(R.id.ListView1);
        ListView1.setAdapter(adapter);
    }



}
