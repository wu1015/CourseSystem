package com.wu1015.coursessystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wu1015.coursessystem.R;

import java.util.List;
import java.util.Map;


public class UserRegisterAdapter extends BaseAdapter {
    public final class ViewZip{
        public TextView textViewName;
        public TextView textViewTeacher;
        public  TextView textViewCost;
        public  TextView textViewNum;
        public  TextView textViewSum;
    }
    private Context context;
    private List<Map<String,Object>> datas;

    public UserRegisterAdapter(Context context, List<Map<String, Object>> datas) {
        this.context = context;
        this.datas = datas;
    }


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
        ViewZip holder=null;
        if (view==null){
            holder=new ViewZip();
            view= LayoutInflater.from(context).inflate(R.layout.item,null);
            holder.textViewName=view.findViewById(R.id.textViewName);
            holder.textViewTeacher=view.findViewById(R.id.textViewTeacher);
            holder.textViewNum=view.findViewById(R.id.textViewNum);
            holder.textViewCost=view.findViewById(R.id.textViewCost);
            holder.textViewSum=view.findViewById(R.id.textViewSum);
            view.setTag(holder);
        }
        else {
            holder=(ViewZip)view.getTag();
        }
        holder.textViewName.setText(datas.get(i).get("ClassName").toString());
        holder.textViewTeacher.setText(datas.get(i).get("ClassTeacher").toString());
        holder.textViewNum.setText(datas.get(i).get("ClassNum").toString());
        holder.textViewCost.setText(datas.get(i).get("ClassCost").toString());
        holder.textViewSum.setText(datas.get(i).get("ClassSum").toString());
        return view;
    }
}