package com.wu1015.coursessystem.fragement;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.wu1015.coursessystem.ClassEditActivity;
import com.wu1015.coursessystem.R;
import com.wu1015.coursessystem.adapter.ClassStdAdapter;
import com.wu1015.coursessystem.adapter.ClassStdAdapter2;
import com.wu1015.coursessystem.controller.aclass.ClassDelController;
import com.wu1015.coursessystem.controller.aclass.ClassListController;
import com.wu1015.coursessystem.controller.stdclass.StdClassSelController;
import com.wu1015.coursessystem.model.Class;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

import java.util.List;

public class ItemClassCenterFragment2 extends Fragment {
    private ListView listViewClass;
    private Button button;
    private List<Class> classList;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private User user;

    public ItemClassCenterFragment2(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_itemclasscenter2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewClass = view.findViewById(R.id.listViewClass);
        button = view.findViewById(R.id.button2);
        button.setVisibility(View.INVISIBLE);
        sqLiteOpenHelper = new DBOpenHelper(getActivity(), "Login.db", null, 1);
        Class aclass = new Class();
        aclass.setM_Name(user.getU_Major());
        ClassListController classListController = new ClassListController(aclass, sqLiteOpenHelper);
        classList = classListController.doList2();
        Log.d("TAG", "onViewCreated: " + aclass.getM_Name());
        boolean[] ck = new boolean[classList.size()];
        ClassStdAdapter2 classStdAdapter = new ClassStdAdapter2(getContext(), classList, ck);
        listViewClass.setAdapter(classStdAdapter);
        listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                todo 多选反选，附加，非必须
                boolean flag = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                AlertDialog alertDialog = alertDemo(builder, i);
                alertDialog.show();
            }
        });
//        listViewClass.setLongClickable(true);
//        listViewClass.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Log.d("TAG", "stdClassCenteronItem: ");
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                AlertDialog alertDialog = alertDemo(builder);
//                alertDialog.show();
//                return false;
//            }
//        });
    }

    public AlertDialog alertDemo(AlertDialog.Builder builder, int i) {

//        设置编辑框
        final String[] lesson = new String[]{"添加", "修改", "删除"};
        EditText editText = new EditText(getContext());
        editText.setText("50");
        AlertDialog alert = builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("系统提示：添加数量")
                .setView(editText)
                .setItems(lesson, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), ClassEditActivity.class);
                        Bundle bundle = new Bundle();
                        if (lesson[which].equals("修改")) {
                            bundle.putString("C_Flag", User.FLAG_USER);
                            bundle.putInt("C_Id", classList.get(i).getC_Id());
                        } else if (lesson[which].equals("添加")) {
                            bundle.putString("C_Flag", User.FLAG_ADMIN);
                            bundle.putInt("C_Num", Integer.parseInt(editText.getText().toString()));
                        } else {
                            ClassDelController controller = new ClassDelController(classList.get(i), sqLiteOpenHelper);
                            Class tmp = controller.doController();
                            if (tmp.getC_Flag().equals(User.FLAG_SUCCESS)) {
                                Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                Class aclass = new Class();
                                aclass.setM_Name(user.getU_Major());
                                ClassListController classListController = new ClassListController(aclass, sqLiteOpenHelper);
                                classList = classListController.doList2();
                                boolean[] ck = new boolean[classList.size()];
                                ClassStdAdapter classStdAdapter = new ClassStdAdapter(getContext(), classList, ck);
                                listViewClass.setAdapter(classStdAdapter);
                                return;
                            } else {
                                Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                                Class aclass = new Class();
                                aclass.setM_Name(user.getU_Major());
                                ClassListController classListController = new ClassListController(aclass, sqLiteOpenHelper);
                                classList = classListController.doList2();
                                boolean[] ck = new boolean[classList.size()];
                                ClassStdAdapter classStdAdapter = new ClassStdAdapter(getContext(), classList, ck);
                                listViewClass.setAdapter(classStdAdapter);
                                return;
                            }
                        }
                        bundle.putString("C_Name", classList.get(i).getC_Name());
                        bundle.putString("C_Teacher", classList.get(i).getC_Teacher());
                        bundle.putString("C_Cost", classList.get(i).getC_Cost());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }).create();
        return alert;
    }
}
