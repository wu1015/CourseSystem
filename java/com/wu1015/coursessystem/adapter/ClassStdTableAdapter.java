package com.wu1015.coursessystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wu1015.coursessystem.R;
import com.wu1015.coursessystem.model.ClassSel;

import java.util.List;
import java.util.Map;


public class ClassStdTableAdapter extends BaseAdapter {
    public final class ViewZip {
        public TextView textViewName;
        public TextView textViewTeacher;
        public TextView textViewCost;
        public TextView textViewNum;
        public TextView textViewSum;
        public CheckBox chk_sel;
    }

    private Context context;
    private List<ClassSel> datas;

    public ClassStdTableAdapter(Context context, List<ClassSel> datas,boolean[] chk_chk) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.textViewName = view.findViewById(R.id.textViewName);
            holder.textViewTeacher = view.findViewById(R.id.textViewTeacher);
            holder.textViewNum = view.findViewById(R.id.textViewNum);
            holder.textViewCost = view.findViewById(R.id.textViewCost);
            holder.textViewSum = view.findViewById(R.id.textViewSum);
            holder.textViewSum.setVisibility(View.INVISIBLE);
            holder.textViewNum.setVisibility(View.INVISIBLE);
            holder.chk_sel=view.findViewById(R.id.chkBox);
            view.setTag(holder);
        } else {
            holder = (ViewZip) view.getTag();
        }
        holder.textViewName.setText(datas.get(i).getC_Name().toString());
        holder.textViewTeacher.setText(datas.get(i).getC_Teacher().toString());
        holder.textViewCost.setText(datas.get(i).getC_Cost().toString());

        holder.chk_sel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    //            todo 需要lisTable类来进行选课，list类仅是课程的sql操作
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    chk_chk[i]=true;
                }
                else {
                    chk_chk[i]=false;
                }
            }
        });
        if (chk_chk[i]!=holder.chk_sel.isChecked()){
            holder.chk_sel.setChecked(chk_chk[i]);
        }
        return view;
    }
}
