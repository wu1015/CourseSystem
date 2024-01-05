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

import com.wu1015.coursessystem.MainActivity;
import com.wu1015.coursessystem.R;
import com.wu1015.coursessystem.adapter.ClassStdAdapter;
import com.wu1015.coursessystem.controller.aclass.ClassListController;
import com.wu1015.coursessystem.controller.stdclass.StdClassSelController;
import com.wu1015.coursessystem.model.Class;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

import java.util.List;

public class ItemClassCenterFragment extends Fragment {
    private ListView listViewClass;
    private List<Class> classList;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private User user;

    public ItemClassCenterFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_itemclasscenter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewClass = view.findViewById(R.id.listViewClass);
        sqLiteOpenHelper = new DBOpenHelper(getActivity(), "Login.db", null, 1);
        Class aclass = new Class();
        aclass.setM_Name(user.getU_Major());
        ClassListController classListController = new ClassListController(aclass, sqLiteOpenHelper);
        classList = classListController.doList();
        Log.d("TAG", "onViewCreated: " + aclass.getM_Name());
        boolean[] ck = new boolean[classList.size()];
        ClassStdAdapter classStdAdapter = new ClassStdAdapter(getContext(), classList, ck);
        listViewClass.setAdapter(classStdAdapter);
        listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                todo 多选反选，附加，非必须
                boolean flag = false;
                for (int j = 0; j < classList.size(); j++) {
                    if (ck[j]) {
                        ck[j]=false;
                        StdClassSelController stdClassSelController = new StdClassSelController(classList.get(j), sqLiteOpenHelper);
                        stdClassSelController.setUser(user);
                        Class tmpclass = stdClassSelController.doController();
                        if (tmpclass.getC_Flag().equals(User.FLAG_SUCCESS)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    Log.d("TAG", "stdClassCenteronItemClick: " + classList.get(i).getC_Name());
                }
                if (flag) {
                    Toast.makeText(getActivity(), "选课成功", Toast.LENGTH_SHORT).show();
                    classList = classListController.doList();
                    ClassStdAdapter classStdAdapter = new ClassStdAdapter(getContext(), classList, ck);
                    listViewClass.setAdapter(classStdAdapter);
                } else {
                    Toast.makeText(getActivity(), "选课失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        listViewClass.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Log.d("TAG", "stdClassCenteronItem: ");
//                return false;
//            }
//        });
    }
}
