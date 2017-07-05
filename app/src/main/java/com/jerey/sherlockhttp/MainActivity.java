package com.jerey.sherlockhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jerey.dblib.SherlockDatabase;
import com.jerey.sherlockhttp.dbtest.User;
import com.jerey.sherlockhttp.dbtest.UserDao;

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
                UserDao userDao =
                        SherlockDatabase.getInstance()
                                .getDataBaseHelper(UserDao.class, User.class);
                userDao.insert(user);
            }
        });
    }
}
