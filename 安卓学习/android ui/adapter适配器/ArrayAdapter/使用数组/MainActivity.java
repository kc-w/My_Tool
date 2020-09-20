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

        //要显示的数据
        String[] strs = {"基神","B神","翔神","曹神","J神"};
        //创建ArrayAdapter(this,ListView显示样式,数组)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,R.layout.simple_expandable_list_item_1,strs);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView ListView1 = (ListView) findViewById(R.id.ListView1);
        ListView1.setAdapter(adapter);
    }



}
