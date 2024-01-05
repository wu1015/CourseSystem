package com.wu1015.coursessystem.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wu1015.coursessystem.R;
import com.wu1015.coursessystem.model.Class;

import java.util.List;


public class ClassStdAdapter2 extends BaseAdapter {
    public final class ViewZip {
        public TextView textViewName;
        public TextView textViewTeacher;
        public TextView textViewCost;
        public TextView textViewNum;
        public TextView textViewSum;
    }

    private Context context;
    private List<Class> datas;

    public ClassStdAdapter2(Context context, List<Class> datas, boolean[] chk_chk) {
        this.context = context;
        this.datas = datas;
        this.chk_chk = chk_chk;
        Log.d("TAG", "ClassStdAdapter: "+datas);
    }


    private boolean[] chk_chk;
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewZip holder = null;
        if (view == null) {
            holder = new ViewZip();
            view = LayoutInflater.from(context).inflate(R.layout.item2, null);
            holder.textViewName = view.findViewById(R.id.textViewName);
            holder.textViewTeacher = view.findViewById(R.id.textViewTeacher);
            holder.textViewNum = view.findViewById(R.id.textViewNum);
            holder.textViewCost = view.findViewById(R.id.textViewCost);
            holder.textViewSum = view.findViewById(R.id.textViewSum);
            view.setTag(holder);
        } else {
            holder = (ViewZip) view.getTag();
        }
        holder.textViewName.setText(datas.get(i).getC_Name().toString());
        holder.textViewTeacher.setText(datas.get(i).getC_Teacher().toString());
        holder.textViewNum.setText("总数量："+String.valueOf(datas.get(i).getC_Num()));
        holder.textViewCost.setText(datas.get(i).getC_Cost().toString());
        holder.textViewSum.setText("剩余数量："+String.valueOf(datas.get(i).getC_Sum()));
        return view;
    }
}
