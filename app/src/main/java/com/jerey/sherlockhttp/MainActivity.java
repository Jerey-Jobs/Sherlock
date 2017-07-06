package com.jerey.sherlockhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jerey.dblib.DaoHelper;
import com.jerey.dblib.SherlockDatabase;
import com.jerey.sherlockhttp.dbtest.User;

public class MainActivity extends AppCompatActivity {

    Button mInsertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.name = "xxx";
                user.password = "1234";
                user.money = 2.222;
                user.score = 1.2f;
                DaoHelper<User> userDao = SherlockDatabase.getInstance().getDaoHelper(User.class);
                userDao.insert(user);
            }
        });


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ContentValues values = new ContentValues();
//                values.put("user_name", "xiamin1");
//                values.put("user_password", "xpasw1");
//                values.put("user_score", 1.11f);
//                values.put("user_s", true);
//                values.put("user_double", 2.22222);
//                SherlockDatabase.getInstance().getSQLiteDatabase().insert("tb_xiamin2", null,
//                        values);
            }
        });

    }
}
