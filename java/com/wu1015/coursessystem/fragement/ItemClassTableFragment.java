package com.wu1015.coursessystem.fragement;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wu1015.coursessystem.R;
import com.wu1015.coursessystem.adapter.ClassStdAdapter;
import com.wu1015.coursessystem.adapter.ClassStdTableAdapter;
import com.wu1015.coursessystem.controller.aclass.ClassListController;
import com.wu1015.coursessystem.controller.stdclass.StdClassDelController;
import com.wu1015.coursessystem.controller.stdclass.StdClassListController;
import com.wu1015.coursessystem.controller.stdclass.StdClassSelController;
import com.wu1015.coursessystem.model.Class;
import com.wu1015.coursessystem.model.ClassSel;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

import java.util.List;

public class ItemClassTableFragment extends Fragment {
    private ListView listViewClass;
    private List<ClassSel> classList;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private User user;

    public ItemClassTableFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_itemclasstable, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewClass = view.findViewById(R.id.listviewclassTable);
        sqLiteOpenHelper = new DBOpenHelper(getActivity(), "Login.db", null, 1);
        Class aclass = new Class();
        aclass.setM_Name(user.getU_Major());
        StdClassListController classListController = new StdClassListController(aclass, sqLiteOpenHelper);
        classListController.setUser(user);
        classList = classListController.doList();
        Log.d("TAG", "TableOnViewCreated: " + user.getU_Id());
        boolean[] ck = new boolean[classList.size()];
        ClassStdTableAdapter classStdAdapter = new ClassStdTableAdapter(getContext(), classList, ck);
//        todo 选课单展示问题，不能和中心一样
        listViewClass.setAdapter(classStdAdapter);
        listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                todo 多选反选，附加，非必须
                boolean flag = false;
                for (int j = 0; j < classList.size(); j++) {
                    if (ck[j]) {
                        ck[j]=false;
                        Class tmp=new Class();
                        tmp.setC_Id(classList.get(j).getC_Id());
                        ClassSel classSel=classList.get(j);
                        StdClassDelController stdClassSelController = new StdClassDelController(tmp, sqLiteOpenHelper);
                        stdClassSelController.setUser(user);
                        stdClassSelController.setClassSel(classSel);
                        Class tmpclass = stdClassSelController.doController();
                        Log.d("TAG", "stdClassCenteronItemClick: " + classList.get(j).getC_Name());
                        if (tmpclass.getC_Flag().equals(User.FLAG_SUCCESS)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    Toast.makeText(getActivity(), "退课成功", Toast.LENGTH_SHORT).show();
                    classList = classListController.doList();
                    ClassStdTableAdapter classStdAdapter = new ClassStdTableAdapter(getContext(), classList, ck);
                    listViewClass.setAdapter(classStdAdapter);
                } else {
                    Toast.makeText(getActivity(), "退课失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
