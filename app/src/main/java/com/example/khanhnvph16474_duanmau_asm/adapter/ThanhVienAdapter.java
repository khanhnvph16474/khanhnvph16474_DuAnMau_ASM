package com.example.khanhnvph16474_duanmau_asm.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.fragment.ThanhVienFragment;
import com.example.khanhnvph16474_duanmau_asm.model.ThanhVien;

import java.util.ArrayList;


public class ThanhVienAdapter extends BaseAdapter implements Filterable {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> list;
    private ArrayList<ThanhVien> listFilter;
    TextView tvMaTV, tvTenTV, tvNamSinh, tvStk;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> list) {
        this.context = context;
        this.fragment = fragment;
        this.list = list;
        this.listFilter = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item,null);
        }
        final ThanhVien item = list.get(position);
        if(item != null){
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã Thành Viên: "+item.maTV);
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên Thành Viên: "+item.hoTen);
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm Sinh: "+item.namSinh);
            tvStk = v.findViewById(R.id.tvStk);
            tvStk.setText("Số Tài Khoản: "+item.stk);
            imgDel = v.findViewById(R.id.imgDeleteTV);
        }
        if(item.stk %5 == 0){
            tvStk.setTypeface(null, Typeface.BOLD);
        }else {

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.maTV));
            }
        });
        return v;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()){
                    list = listFilter;
                }else {
                    ArrayList<ThanhVien> arr = new ArrayList<>();
                    for(ThanhVien tv :list){
                        if (tv.hoTen.toUpperCase().contains(search.toUpperCase())){
                            arr.add(tv);
                        }
                    }
                    list =arr;
                }
                FilterResults results = new FilterResults();
                results.values = list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<ThanhVien>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
