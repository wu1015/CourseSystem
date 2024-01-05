package com.wu1015.coursessystem.fragement;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wu1015.coursessystem.R;
import com.wu1015.coursessystem.model.User;

public class ItemMeFragment extends Fragment {

    private ImageView imageView;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private User user;

    public ItemMeFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_itemme, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView=view.findViewById(R.id.imageView);
        textView4=view.findViewById(R.id.textView4);
        textView5=view.findViewById(R.id.textView5);
        textView6=view.findViewById(R.id.textView6);
        textView7=view.findViewById(R.id.textView7);
        textView8=view.findViewById(R.id.textView8);
        textView9=view.findViewById(R.id.textView9);
        textView10=view.findViewById(R.id.textView10);
        textView11=view.findViewById(R.id.textView11);
        try {
        }catch (Exception e){

        }

        String flag;
        if (user.getU_Flag().equals(User.FLAG_USER)){
            flag="用户身份：学生";
            imageView.setImageResource(R.drawable.iconmonstr_school_24);
            textView5.setVisibility(View.VISIBLE);
            textView9.setVisibility(View.VISIBLE);
            textView10.setVisibility(View.VISIBLE);
        }else {
            flag="用户身份：管理员";
            imageView.setImageResource(R.drawable.iconmonstr_building_32);
            textView5.setVisibility(View.INVISIBLE);
            textView9.setVisibility(View.INVISIBLE);
            textView10.setVisibility(View.INVISIBLE);
        }

        textView4.setText("用户帐号："+user.getU_Id());
        textView5.setText("用户学号："+user.getU_No());
        textView6.setText("用户姓名："+user.getU_Name());
        String sex;
        if (user.getU_Sex().equals("male")){
            sex="男";
        }else if (user.getU_Sex().equals("female")){
            sex="女";
        }else {
            sex="null";
        }
        textView7.setText("用户性别："+sex);
        textView8.setText("用户邮箱："+user.getU_Mail());
        textView9.setText("用户专业："+user.getU_Major());
        textView10.setText("用户年级："+user.getU_Grade());
        textView11.setText(flag);
    }
}
