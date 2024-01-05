package com.wu1015.coursessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.wu1015.coursessystem.controller.user.UserAddController;
import com.wu1015.coursessystem.controller.user.UserEditController;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeEditActivity extends AppCompatActivity {
    public static Pattern p =
            Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private ImageButton imageButton;
    private EditText editTextId;
    private EditText editTextPwd;
    private EditText editTextNo;
    private EditText editTextName;
    private EditText editTextMail;
    private Spinner spinnerSex;
    private Spinner spinnerMajor;
    private Spinner spinnerGrade;
    private Button btnRegister;
    private User user;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_edit);

        imageButton = findViewById(R.id.imageButton);
        editTextId = findViewById(R.id.editTextId);
        editTextPwd = findViewById(R.id.editTextPwd);
        editTextNo = findViewById(R.id.editTextNo);
        editTextName = findViewById(R.id.editTextName);
        editTextMail = findViewById(R.id.editTextTextEmailAddress);
        spinnerSex = findViewById(R.id.spinnerSex);
        spinnerMajor = findViewById(R.id.spinnerMajor);
        spinnerGrade = findViewById(R.id.spinnerGrade);
        btnRegister = findViewById(R.id.btnRegister);

        intent=getIntent();
        userSet();
        editTextId.setText(user.getU_Id());
        editTextNo.setText(user.getU_No());
        editTextName.setText(user.getU_Name());
        editTextMail.setText(user.getU_Mail());
        initData();
    }

    public void initData() {

        sqLiteOpenHelper = new DBOpenHelper(this, "Login.db", null, 1);
        sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
        try {
            Cursor resultMajor = sqLiteDatabase.rawQuery("select * from major", null);
            Cursor resultGrade = sqLiteDatabase.rawQuery("select * from grade", null);
            List<String> listGrade = new ArrayList<>();
            List<String> listMajor = new ArrayList<>();
            resultGrade.moveToFirst();
            resultMajor.moveToFirst();
            for (int i = 0; i < resultGrade.getCount(); i++) {
                listGrade.add(resultGrade.getString(1));
                resultGrade.moveToNext();
            }
            for (int i = 0; i < resultMajor.getCount(); i++) {
                listMajor.add(resultMajor.getString(1));
                resultMajor.moveToNext();
            }
            ArrayAdapter adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listGrade);
            ArrayAdapter adapter1 = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listMajor);
            spinnerGrade.setAdapter(adapter);
            spinnerMajor.setAdapter(adapter1);
            adapter.notifyDataSetChanged();
            adapter1.notifyDataSetChanged();
            resultGrade.close();
            resultMajor.close();
        } catch (Exception e) {
            Log.d("TAG", "MeEdit_initDate: ");
        }


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(isEmail(editTextMail.getText().toString().trim()) && editTextMail.getText().toString().trim().length() <= 31)) {
                    Toast.makeText(MeEditActivity.this, "请检查邮箱格式", Toast.LENGTH_SHORT).show();
                    return;
                }
                user = new User();
                user.setU_Id(editTextId.getText().toString());
                user.setU_Pwd(editTextPwd.getText().toString());
                user.setU_No(editTextNo.getText().toString());
                user.setU_Name(editTextName.getText().toString());
                user.setU_Mail(editTextMail.getText().toString());
                String sex = spinnerSex.getSelectedItem().toString();
                if (sex.equals("男")) {
                    sex = "male";
                } else {
                    sex = "female";
                }
                user.setU_Sex(sex);
                user.setU_Major(spinnerMajor.getSelectedItem().toString());
                user.setU_Grade(spinnerGrade.getSelectedItem().toString());
                user.setU_Flag(intent.getStringExtra("U_Flag"));
                UserEditController userAddController = new UserEditController(user, sqLiteOpenHelper);
                user = userAddController.doController();
                if (user.getU_Flag().equals(User.FLAG_SUCCESS)) {
//                    关闭所有activity然后跳转登陆页实现数据的刷新
                    Toast.makeText(MeEditActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MeEditActivity.this, LoginActivity.class);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    finishAffinity();
                } else {
                    Toast.makeText(MeEditActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public void userSet() {
        Bundle bundle = intent.getExtras();
        user=new User();
        user.setU_Id(bundle.getString("U_Id",null));
        user.setU_No(bundle.getString("U_No",null));
        user.setU_Name(bundle.getString("U_Name",null));
        user.setU_Sex(bundle.getString("U_Sex",null));
        user.setU_Mail(bundle.getString("U_Mail",null));
        user.setU_Grade(bundle.getString("U_Grade",null));
        user.setU_Major(bundle.getString("U_Major",null));
        user.setU_Img(bundle.getByteArray("U_Img"));
        user.setU_Flag(bundle.getString("U_Flag"));
    }
}