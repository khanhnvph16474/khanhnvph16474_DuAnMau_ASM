package com.example.khanhnvph16474_duanmau_asm.fragment;


import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.dao.ThuThuDAO;
import com.example.khanhnvph16474_duanmau_asm.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class AddUserFragment extends Fragment {
    TextInputEditText edUser, edName, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_add_user, container, false);
        edUser = v.findViewById(R.id.edUser);
        edName = v.findViewById(R.id.edName);
        edPass = v.findViewById(R.id.edPass);
        edRePass = v.findViewById(R.id.edRePass);
        btnSave = v.findViewById(R.id.SaveUser);
        btnCancel = v.findViewById(R.id.CancelUser);

        dao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUser.setText("");
                edName.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuThu thuThu = new ThuThu();
                thuThu.maTT = edUser.getText().toString();
                thuThu.hoTen = edName.getText().toString();
                thuThu.matKhau = edPass.getText().toString();
                if( validate()>0){
                    if(dao.insert(thuThu)>0){
                        Toast.makeText(getActivity(), "L??u th??nh c??ng", Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edName.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "L??u th???t b???i", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    public int validate(){
        int check = 1;
        if(edUser.getText().length()==0 || edName.getText().length()==0 || edPass.getText().length()==0 || edRePass.getText().length()==0){
            Toast.makeText(getContext(), "B???n ph???i nh???p ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (edName.getText().toString().length()<5 || edName.getText().toString().length()>15){
            Toast.makeText(getContext(), "T??n ng?????i d??ng ph???i c?? ????? d??i t??? 5->15", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if(edName.getText().toString().substring(0,1).equals(edName.getText().toString().substring(0,1).toUpperCase()) == false){
            Toast.makeText(getContext(), "Ch??? c??i ?????u ph???i vi???t hoa", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String Pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!Pass.equals(rePass)){
                Toast.makeText(getContext(), "M???t kh???u kh??ng tr??ng kh???p", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
