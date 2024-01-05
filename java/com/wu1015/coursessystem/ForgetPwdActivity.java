package com.wu1015.coursessystem;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wu1015.coursessystem.controller.ForgetPwdController;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

public class ForgetPwdActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextMail;
    private EditText editTextPwd;
    private ImageButton imageButton;
    private Button btnForget;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

        editTextId=findViewById(R.id.editTextId2);
        editTextMail=findViewById(R.id.editTextTextEmailAddress2);
        editTextPwd=findViewById(R.id.editTextPwd2);
        imageButton=findViewById(R.id.imageButton2);
        btnForget=findViewById(R.id.btnForget);

        initData();
    }
    public void initData(){
        sqLiteOpenHelper = new DBOpenHelper(this, "Login.db", null, 1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               user=new User();
               user.setU_Id(editTextId.getText().toString());
               user.setU_Mail(editTextMail.getText().toString());
               user.setU_Pwd(editTextPwd.getText().toString());
               user.setU_Flag(User.FLAG_USER);
               ForgetPwdController forgetPwdController=new ForgetPwdController(user,sqLiteOpenHelper);
               user= forgetPwdController.doController();
                if (user.getU_Flag().equals(User.FLAG_SUCCESS)) {
                    Toast.makeText(ForgetPwdActivity.this, "忘记密码成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgetPwdActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ForgetPwdActivity.this, "忘记密码失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}