package com.example.myapplication;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    //名称数组
    private String[] names = new String[]{"B神", "基神", "曹神"};
    //简介数组
    private String[] says = new String[]{"无形被黑，最为致命", "大神好厉害~", "我将带头日狗~"};
    //头像数组
    private int[] imgIds = new int[]{R.drawable.image1, R.drawable.image1, R.drawable.image1};

    //键数组
    private  String[] list1 = new String[]{"img", "name", "says"};
    //值位置
    private  int[] list2 = new int[]{R.id.img, R.id.name, R.id.says};


    /** 当活动第一次被创建时调用 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载UI组件
        setContentView(R.layout.activity_main);


        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("img", imgIds[i]);
            showitem.put("name", names[i]);
            showitem.put("says", says[i]);
            listitem.add(showitem);
        }
        //创建一个simpleAdapter(应用接口,键值列表,单个框组样式,键数组,值数组)
        SimpleAdapter myAdapter = new SimpleAdapter(this, listitem, R.layout.layout, list1, list2);
        ListView listView = (ListView) findViewById(R.id.ListView1);
        listView.setAdapter(myAdapter);


    }
}
