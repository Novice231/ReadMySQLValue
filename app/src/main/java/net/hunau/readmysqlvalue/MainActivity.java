package net.hunau.readmysqlvalue;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import net.hunau.dblink.DBAdapter;
import net.hunau.entity.User;


public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            ((TextView) findViewById(R.id.display)).setText((String) message.obj);
            String str = "查询不存在";
            if (message.what == 1) str = "查询成功";
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBAdapter dl = new DBAdapter();
        Button btn1 = (Button) findViewById(R.id.btn1);
        TextView display = (TextView) findViewById(R.id.display);
        final EditText et_name = findViewById(R.id.et1);
        (findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = et_name.getText().toString().trim();
                Log.e(TAG, name);
                if (name == null || name.equals("")) {
                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv_result = findViewById(R.id.display);
                            List<User> mp = dl.getUserInfoByName(name);
                            Message msg = new Message();
                            if (mp==null) {
                                msg.what = 0;
                                msg.obj = "查询结果，空空如也";
                                //非UI线程不要试着去操作界面
                            } else {
                                String ss = "";
                                for(int i = 0; i < mp.size(); i++) {
                                    ss += mp.get(i).toString();
                                }
                                msg.what = 1;
                                msg.obj = ss;
                            }
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            }
        });
    }
}
