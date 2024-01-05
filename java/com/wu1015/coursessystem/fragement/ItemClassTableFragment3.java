package com.wu1015.coursessystem.fragement;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.wu1015.coursessystem.R;
import com.wu1015.coursessystem.adapter.ClassStdTableAdapter2;
import com.wu1015.coursessystem.adapter.ClassStdTableAdapter3;
import com.wu1015.coursessystem.controller.grade.GradeListController;
import com.wu1015.coursessystem.controller.major.MajorListController;
import com.wu1015.coursessystem.model.Grade;
import com.wu1015.coursessystem.model.Major;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

import java.util.List;

public class ItemClassTableFragment3 extends Fragment {
    private ListView listViewClass;
    private Button button;
    private List<Grade> classList;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private User user;

    public ItemClassTableFragment3(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_itemclasstable2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewClass = view.findViewById(R.id.listviewclassTable);
        button = view.findViewById(R.id.button);
        sqLiteOpenHelper = new DBOpenHelper(getActivity(), "Login.db", null, 1);
        Grade aclass = new Grade();
        aclass.setG_Name(user.getU_Major());
        GradeListController classListController = new GradeListController(aclass, sqLiteOpenHelper);
        classList = classListController.doList();
        Log.d("TAG", "TableOnViewCreated: " + user.getU_Id());
        boolean[] ck = new boolean[classList.size()];
        ClassStdTableAdapter3 classStdAdapter = new ClassStdTableAdapter3(getContext(), classList, ck);
//        todo 选课单展示问题，不能和中心一样
        listViewClass.setAdapter(classStdAdapter);
        listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                todo 多选反选，附加，非必须
                int id=classList.get(i).getG_Id();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                AlertDialog alertDialog = alertDemo(builder, true, classList.get(i).getG_Name().toString(),id);
                alertDialog.show();
            }
        });
        listViewClass.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                AlertDialog alert = builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("系统提示：")
                        .setMessage("是否进行删除")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String where="G_Id="+classList.get(i).getG_Id();
                                SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
                                int i=sqLiteDatabase.delete(DBOpenHelper.SQL_TB_Grade_NAME,where,null);
                                if (i>0){
                                    Log.d("TAG", "onClick:update ");
                                    Toast.makeText(getContext(), "删除成功" , Toast.LENGTH_SHORT).show();
                                }else {
                                    Log.d("TAG", "onClick:noUpdate ");
                                    Toast.makeText(getContext(), "删除失败" , Toast.LENGTH_SHORT).show();
                                }
                                Grade aclass = new Grade();
                                aclass.setG_Name(user.getU_Major());
                                GradeListController classListController = new GradeListController(aclass, sqLiteOpenHelper);
                                classList = classListController.doList();
                                boolean[] ck = new boolean[classList.size()];
                                ClassStdTableAdapter3 classStdAdapter = new ClassStdTableAdapter3(getContext(), classList, ck);
                                listViewClass.setAdapter(classStdAdapter);
                            }
                        })
                        .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                        Toast.makeText(MainActivity.this, "你点击了中立按钮~", Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                AlertDialog alertDialog = alertDemo(builder, false, "",id);
                alertDialog.show();
            }
        });
    }

    public AlertDialog alertDemo(AlertDialog.Builder builder, boolean flag, String messageText, int id) {
        String kkk = "";
        if (flag) {
            kkk = "更新";
        } else {
            kkk = "创建";
        }
//        设置编辑框
        EditText editText = new EditText(getContext());
        editText.setText(messageText);
        AlertDialog alert = builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("系统提示：")
                .setMessage(kkk)
                .setView(editText)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        如果有信息的话添加
                        SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.put("G_Name", editText.getText().toString());
                        if (flag) {
                            values.put("G_Id", id);
                            String where = "G_Id=" + id;
                            int i = sqLiteDatabase.update(DBOpenHelper.SQL_TB_Grade_NAME, values, where, null);
                            if (i > 0) {
                                Log.d("TAG", "onClick:update ");
                                Toast.makeText(getContext(), "更新成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("TAG", "onClick:noUpdate ");
                                Toast.makeText(getContext(), "更新失败", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            long i = sqLiteDatabase.insert(DBOpenHelper.SQL_TB_Grade_NAME, null, values);
                            if (i == -1) {
                                Log.d("TAG", "onClick:NoInsert ");
                                Toast.makeText(getContext(), "添加失败", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("TAG", "onClick:insert");
                                Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                            }
                        }
//                        重新设置数据源，以更新数据
                        Grade aclass = new Grade();
                        aclass.setG_Name(user.getU_Major());
                        GradeListController classListController = new GradeListController(aclass, sqLiteOpenHelper);
                        classList = classListController.doList();
                        boolean[] ck = new boolean[classList.size()];
                        ClassStdTableAdapter3 classStdAdapter = new ClassStdTableAdapter3(getContext(), classList, ck);
                        listViewClass.setAdapter(classStdAdapter);
                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        return alert;
    }
}
