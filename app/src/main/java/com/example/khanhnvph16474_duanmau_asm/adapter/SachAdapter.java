package com.example.khanhnvph16474_duanmau_asm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.dao.LoaiSachDAO;
import com.example.khanhnvph16474_duanmau_asm.fragment.SachFragment;
import com.example.khanhnvph16474_duanmau_asm.model.LoaiSach;
import com.example.khanhnvph16474_duanmau_asm.model.Sach;
import com.example.khanhnvph16474_duanmau_asm.model.ThanhVien;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter implements Filterable {
    private Context context;
    SachFragment fragment;
    private ArrayList<Sach> list;
    private ArrayList<Sach> listFilter;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;

    public SachAdapter(@NonNull Context context, SachFragment fragment, ArrayList<Sach> list) {
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
            v = inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item = list.get(position);
        if(item != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.maLoai));
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã Sách: "+item.maSach);
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: "+item.tenSach);
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Thuê: "+item.giaThue);
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại Sách: "+loaiSach.tenLoai);
            imgDel = v.findViewById(R.id.imgDeleteS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.maSach));
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
                    list =listFilter;
                }else {
                    ArrayList<Sach> arr = new ArrayList<>();
                    for(Sach s :list){
                        if (s.tenSach.toUpperCase().contains(search.toUpperCase())){
                            arr.add(s);
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
                list = (ArrayList<Sach>) results.values;
                notifyDataSetChanged();
            }
        };

    }
}
