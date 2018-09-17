package com.example.panzq.myproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.annotation.view.ViewInject;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class FinalDBActivity extends FinalActivity {

    @ViewInject(id = R.id.tv_result_db)
    TextView tv_result_db;
    @ViewInject(id = R.id.btn_add_db, click = "add_db")
    Button btn_add_db;

    public void add_db(View v) {
        tv_result_db.setText("add_db()...");
        User user = new User();
        user.setEmail("afinal@tsz.net");
        user.setName("Android探索者");
        user.setRegisterDate(new Date());
        user.setMoney(25.5);
        db.save(user);
        user.setEmail("javabcys@jbcys.com");
        user.setName("java编程艺术");
        user.setRegisterDate(new Date());
        user.setMoney(35.5);
        db.save(user);
        user.setEmail("android fkjy@fkjy.com");
        user.setName("Android 疯狂讲义");
        user.setRegisterDate(new Date());
        user.setMoney(38.5);
        db.save(user);
    }

    @ViewInject(id = R.id.btn_del_db, click = "del_db")
    Button btn_del_db;

    public void del_db(View v) {
        tv_result_db.setText("del_db()...");
        // db.delete(user); //根据对象主键进行删除
        // db.deleteById(user, 1); //根据主键删除数据
        //db.deleteByWhere(User.class, "money<30.0"); //自定义where条件删除
        db.deleteById(User.class,11);
       //db.deleteAll(User.class); //删除Bean对应的数据表中的所有数据
    }

    @ViewInject(id = R.id.btn_update_db, click = "update_db")
    Button btn_update_db;

    public void update_db(View v) {
        tv_result_db.setText("update_db()...");
//        User user = new User();
//        user.setMoney(111.0);
//        db.update(user,"id = 1");//根据where条件更新
        User user = new User();
        user.setId(2); /////这个属性必须要有，表示 update user set ....where id =2;
        user.setMoney(43.5);
        user.setName("Java疯狂讲义");
        user.setEmail("javafkjy@fkjy.com");
        user.setRegisterDate(new Date());
        db.update(user);
    }

    @ViewInject(id = R.id.btn_query_db, click = "query_db")
    Button btn_query_db;

    public void query_db(View v) {
        tv_result_db.setText("query_db()...\r\n");
        List<User> userList = db.findAll(User.class,"name like '%m%'");//查询所有的用户

        tv_result_db.append("用户数量："+ (userList!=null?userList.size():0)+"\r\n");
        if (userList != null && userList.size()>0) {
            for (int i = 0;i<userList.size();i++)
                tv_result_db.append(userList.get(i).getId()+"——"+userList.get(i).getName() + ":" + userList.get(i).getRegisterDate() + "\r\n");
        }
    }

    private Context mContext ;
    private FinalDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_db);
        mContext = this;
        db = FinalDb.create(mContext, "mytest.db", true);



    }
}
