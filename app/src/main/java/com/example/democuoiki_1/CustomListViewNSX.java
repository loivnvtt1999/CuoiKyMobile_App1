package com.example.democuoiki_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomListViewNSX extends ArrayAdapter<NhaSanXuat> {
    Context context;
    int resource;
    ArrayList<NhaSanXuat> lstNSX;
    public CustomListViewNSX(@NonNull Context context, int resource, @NonNull ArrayList<NhaSanXuat> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.lstNSX=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_nsx,parent,false);
            viewHolder= new ViewHolder();
            viewHolder.tvID=convertView.findViewById(R.id.tvID_NSX);
            viewHolder.tvName=convertView.findViewById(R.id.tvTenNSX);
            viewHolder.tvDiaChi=convertView.findViewById(R.id.tvDiaChiNSX);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        NhaSanXuat nsx=lstNSX.get(position);
        viewHolder.tvID.setText(nsx.getId_nsx());
        viewHolder.tvName.setText(nsx.getTen());
        viewHolder.tvDiaChi.setText(nsx.getDiachi());
        return convertView;
    }
    public class ViewHolder{
        TextView tvID,tvName, tvDiaChi;
    }
}
