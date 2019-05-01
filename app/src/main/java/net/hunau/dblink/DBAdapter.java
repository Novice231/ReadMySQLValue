package net.hunau.dblink;

import android.util.Log;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBAdapter {
    private final String TAG="DBAdapter";
    private final String driver="com.mysql.jdbc.Driver";
    private final String url="jdbc:mysql://110.53.162.165:3306/test?useSSL=false";
    private final String user="root";
    private final String password="sx123456AaBb";

    private Connection getConnection(String dbName){
        Connection conn=null;
        try{
            Class.forName(driver);//加载驱动
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            Log.d(TAG,"数据库连接失败");
            e.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return conn;
    }

    public HashMap<String, String> getUserInfoByName(String name){
        HashMap<String ,String> map = new HashMap<>();
        Connection conn =getConnection("test");
        try{
            Statement st = conn.createStatement();
            String sql = "select id,name,pwd,sexy,isused from tb_user where name = '"+ name +"'";
            ResultSet res = st.executeQuery(sql);
            if(res == null){
                return null;
            }else{
                int cnt =res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1;i <= cnt ; i++){
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field,res.getString(field));
                }
                res.close();
                st.close();
                conn.close();
                return map;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG,"数据库操作异常");
            return null;
        }
    }
}
