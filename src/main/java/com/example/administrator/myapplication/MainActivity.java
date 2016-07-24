package com.example.administrator.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

/**
 * 扫描其他蓝牙设备
 *获取配对设备列表
 *连接到通过服务发现其他设备
 */
public class MainActivity extends AppCompatActivity {
  private Button On,Off,Visible,list;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        On= (Button) findViewById(R.id.button1);
        Off= (Button) findViewById(R.id.button2);
        Visible= (Button) findViewById(R.id.button3);
        list= (Button) findViewById(R.id.button4);
        lv= (ListView) findViewById(R.id.listView1);
        //1.获得本地蓝牙适配器
        BA=BluetoothAdapter.getDefaultAdapter();
    }
    //2.打开蓝牙
    public void on(View view){
        if (!BA.isEnabled()){
            Intent turnOn=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn,0);
            Toast.makeText(getApplicationContext(),"Turned on",Toast.LENGTH_LONG).show();;
        }else{
            Toast.makeText(getApplicationContext(),"Already on",Toast.LENGTH_LONG).show();;
        }
    }
    //3.获取配对设备列表
    public void list(View view){
       pairedDevices= BA.getBondedDevices();
        ArrayList list=new ArrayList();
        for (BluetoothDevice bt:pairedDevices){
            list.add(bt.getName());
        }
        Toast.makeText(getApplicationContext(),"Showing Paired Devices",Toast.LENGTH_LONG).show();;
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
    }
    //4.关闭蓝牙
public void off(View view){
    BA.disable();
    Toast.makeText(getApplicationContext(),"Turned off",Toast.LENGTH_LONG).show();
}
public void visible(View view){
    Intent getVisible=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
    startActivityForResult(getVisible,0);
}
}
