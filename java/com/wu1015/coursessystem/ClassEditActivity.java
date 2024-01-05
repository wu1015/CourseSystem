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

import com.wu1015.coursessystem.controller.aclass.ClassAddController;
import com.wu1015.coursessystem.controller.aclass.ClassEditController;
import com.wu1015.coursessystem.controller.user.UserAddController;
import com.wu1015.coursessystem.model.Class;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ClassEditActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private EditText editTextId;
    private EditText editTextNo;
    private EditText editTextName;
    private Spinner spinnerMajor;
    private Button btnAdd;
    private Class aClass;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Intent intent;
    private boolean flag;
    private int CTmp_Id;
    private int CTmp_Num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_edit);

        imageButton = findViewById(R.id.imageButton);
        editTextId = findViewById(R.id.editTextId);
        editTextNo = findViewById(R.id.editTextNo);
        editTextName = findViewById(R.id.editTextName);
        spinnerMajor = findViewById(R.id.spinnerMajor);
        btnAdd = findViewById(R.id.btnRegister);

        intent = getIntent();

        initDate();
    }

    public void initDate() {
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        if (bundle.getString("C_Flag").equals(User.FLAG_ADMIN)) {
            btnAdd.setText("添加");
            flag=true;
            CTmp_Num=bundle.getInt("C_Num");
        } else {
            flag=false;
            btnAdd.setText("修改");
            CTmp_Id=bundle.getInt("C_Id");
            editTextId.setText(bundle.getString("C_Name"));
            editTextNo.setText(bundle.getString("C_Teacher"));
            editTextName.setText(bundle.getString("C_Cost"));
        }

        sqLiteOpenHelper = new DBOpenHelper(this, "Login.db", null, 1);
        sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
        try {
            Cursor resultMajor = sqLiteDatabase.rawQuery("select * from major", null);
            List<String> listMajor = new ArrayList<>();
            resultMajor.moveToFirst();

            for (int i = 0; i < resultMajor.getCount(); i++) {
                listMajor.add(resultMajor.getString(1));
                resultMajor.moveToNext();
            }
            ArrayAdapter adapter1 = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listMajor);
            spinnerMajor.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();
            resultMajor.close();
        } catch (Exception e) {
            Log.d("TAG", "ClassEdit_initDate: ");
        }


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aClass = new Class();

                aClass.setC_Name(editTextId.getText().toString());
                aClass.setC_Teacher(editTextNo.getText().toString());
                aClass.setC_Cost(editTextName.getText().toString());
                aClass.setM_Name(spinnerMajor.getSelectedItem().toString());
                aClass.setC_Flag(User.FLAG_ADMIN);
                if (flag) {
                    aClass.setC_Num(CTmp_Num);
                    ClassAddController userAddController = new ClassAddController(aClass, sqLiteOpenHelper);
                    aClass = userAddController.doController();
                }else {
                    aClass.setC_Id(CTmp_Id);
                    ClassEditController classEditController=new ClassEditController(aClass,sqLiteOpenHelper);
                    aClass=classEditController.doController();
                }
                if (aClass.getC_Flag().equals(User.FLAG_SUCCESS)) {
                    Toast.makeText(ClassEditActivity.this, "成功，请再次点击选课中心以刷新数据", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ClassEditActivity.this, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}