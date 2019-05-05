package net.hunau.dblink;

import android.util.Log;
import android.widget.EditText;

import net.hunau.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBAdapter {
    private final String TAG = "DBAdapter";
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://110.53.162.165:3306/test?useSSL=false";
    private final String user = "root";
    private final String password = "sx123456AaBb";

    private Connection getConnection(String dbName) {
        Connection conn = null;
        try {
            Class.forName(driver);//加载驱动
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            Log.d(TAG, "数据库连接失败");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public List<User> getUserInfoByName(String name) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection("test");
        try {
            Statement st = conn.createStatement();
            String sql = "select id,name,pwd,sexy,isused from tb_user where name = '" + name + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                List<User> users = new ArrayList<>();
                if(res.next()==false)return null;
                do{
                    User user = new User();
                    user.setSexy(res.getString("sexy"));
                    user.setName(res.getString("name"));
                    user.setId(res.getString("id"));
                    user.setPwd(res.getString("pwd"));
                    user.setIsused(res.getString("isused"));
                    users.add(user);
                }while (res.next());
                res.close();
                st.close();
                conn.close();
                return users;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "数据库操作异常");
            return null;
        }
    }
}
