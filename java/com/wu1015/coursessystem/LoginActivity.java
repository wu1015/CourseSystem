package com.wu1015.coursessystem;

import static com.wu1015.coursessystem.model.User.FLAG_ADMIN;
import static com.wu1015.coursessystem.model.User.FLAG_ERROR;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wu1015.coursessystem.controller.LoginUserController;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

public class LoginActivity extends AppCompatActivity {

    private ImageView imageViewHead;
    private EditText editTextId;
    private EditText editTextPwd;
    private CheckBox chkRemember;
    private Button btnLogin;
    private TextView textViewRegister;
    private TextView textViewForget;
    private SQLiteOpenHelper sqLiteOpenHelper;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageViewHead = findViewById(R.id.imageViewHead);
        editTextId = findViewById(R.id.editTextId);
        editTextPwd = findViewById(R.id.editTextPwd);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        textViewRegister = findViewById(R.id.register);
        textViewForget = findViewById(R.id.forgetPwd);


        imageViewHead.setImageResource(R.drawable.logo_cam);
        sqLiteOpenHelper = new DBOpenHelper(this, "Login.db", null, 1);
        initDate();

    }

    private void initDate() {
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        Boolean isRemember = prefs.getBoolean("rememberMe", false);
        if (isRemember) {
//            如果已经记住了密码那么填入文本框中
            String account = prefs.getString("U_Id", "");
            String password = prefs.getString("U_Pwd", "");
            chkRemember.setChecked(true);
            editTextId.setText(account);
            editTextPwd.setText(password);
        }

//        登陆按钮设置监听器
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                String U_Id = editTextId.getText().toString();
                String U_Pwd = editTextPwd.getText().toString();
                User user = new User();
                user.setU_Id(U_Id);
                user.setU_Pwd(U_Pwd);
                LoginUserController loginUserController = new LoginUserController(user, sqLiteOpenHelper);
                user = loginUserController.doController();
                try {
                    String flag = user.getU_Flag();
                    if (!flag.equals(FLAG_ERROR)) {
//                            验证账户密码是否正确
                        SharedPreferences.Editor editor = prefs.edit();
                        if (chkRemember.isChecked()) {
//                                “记住密码”是否被选中
                            editor.putBoolean("rememberMe", true);
                            editor.putString("U_Id", U_Id);
                            editor.putString("U_Pwd", U_Pwd);
                        } else {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent;
                        if (flag.equals(FLAG_ADMIN)){
                            intent=new Intent(LoginActivity.this,MainActivity2.class);
                        }else {
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("U_Id", user.getU_Id());
                        bundle.putString("U_No", user.getU_No());
                        bundle.putString("U_Name", user.getU_Name());
                        bundle.putString("U_Sex", user.getU_Sex());
                        bundle.putString("U_Mail", user.getU_Mail());
                        bundle.putString("U_Grade", user.getU_Grade());
                        bundle.putString("U_Major", user.getU_Major());
                        bundle.putByteArray("U_Img", user.getU_Img());
                        bundle.putString("U_Flag", user.getU_Flag());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        int tt = 1 / 0;
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "账户或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        忘记密码和注册新用户按钮监听
        textViewRegister.setClickable(true);
        textViewForget.setClickable(true);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
                startActivity(intent);
            }
        });
    }
}