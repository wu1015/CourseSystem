package com.wu1015.coursessystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wu1015.coursessystem.R;
import com.wu1015.coursessystem.model.Grade;
import com.wu1015.coursessystem.model.Major;

import java.util.List;


public class ClassStdTableAdapter3 extends BaseAdapter {
    public final class ViewZip {
        public TextView textViewName;
        public TextView textViewTeacher;
        public TextView textViewCost;
        public TextView textViewNum;
        public TextView textViewSum;
    }

    private Context context;
    private List<Grade> datas;

    public ClassStdTableAdapter3(Context context, List<Grade> datas, boolean[] chk_chk) {
        this.context = context;
        this.datas = datas;
        this.chk_chk = chk_chk;
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


            holder.textViewTeacher.setVisibility(View.INVISIBLE);
            holder.textViewSum.setVisibility(View.INVISIBLE);
            holder.textViewCost.setVisibility(View.INVISIBLE);
            holder.textViewNum.setVisibility(View.INVISIBLE);
            view.setTag(holder);
        } else {
            holder = (ViewZip) view.getTag();
        }
        holder.textViewName.setText(datas.get(i).getG_Name().toString());
//        holder.textViewTeacher.setText(datas.get(i).getC_Teacher().toString());
//        holder.textViewCost.setText(datas.get(i).getC_Cost().toString());

        return view;
    }
}
