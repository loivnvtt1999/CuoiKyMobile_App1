package com.example.democuoiki_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivitySanPham extends AppCompatActivity {
    Button btnThem, btnChon;
    ArrayList<String> lstHienThi;
    ArrayAdapter<String> adapter;
    DbSanPham db;
    ArrayList<SanPham> lstSanPham;
    EditText edtID_SP, edtName_SP, edtDVT_SP, edtDonGia_SP, edtIDNSX_SP;
    ListView lvSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        btnThem=(Button)findViewById(R.id.btnThemSP);
        btnChon=(Button)findViewById(R.id.btnChon);
        edtID_SP=(EditText)findViewById(R.id.edtID_SP);
        edtName_SP=(EditText)findViewById(R.id.edtName_SanPham);
        edtDVT_SP=(EditText)findViewById(R.id.edtDVT_SanPham);
        edtDonGia_SP=(EditText)findViewById(R.id.edtDonGia_SP);
        edtIDNSX_SP=(EditText)findViewById(R.id.edtID_NSX_SP);
        lstHienThi=new ArrayList<String>();
        db=new DbSanPham(this);
        lstSanPham=new ArrayList<SanPham>();
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lstHienThi);
        lvSP= (ListView)findViewById(R.id.lvSanPham);
        lvSP.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sp= new SanPham();
                sp.setId_sp(edtID_SP.getText().toString());
                sp.setTen_sp(edtName_SP.getText().toString());
                sp.setDvt(edtDVT_SP.getText().toString());
                sp.setDongia(Double.parseDouble(edtDonGia_SP.getText().toString()));
                sp.setId_nsx(edtIDNSX_SP.getText().toString());
                if(db.insertSanPham(sp)>0){
                    Toast.makeText(ActivitySanPham.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ActivitySanPham.this, "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lstHienThi=new ArrayList<String>();
                lstSanPham=new ArrayList<SanPham>();
                if(edtID_SP.getText().length()==0){
                    lstSanPham=db.loadAllSanPham();
                    for(SanPham sp:lstSanPham){
                        String s="";
                        s="Ma san pham:"+sp.getId_sp()+"\tTen san pham:"+sp.getTen_sp()+"\tDon gia:"+sp.getDongia();
                        lstHienThi.add(s);
                    }
                    adapter= new ArrayAdapter<String>(ActivitySanPham.this,android.R.layout.simple_list_item_1,lstHienThi);
                    lvSP.setAdapter(adapter);
                }
                else{
                    lstSanPham=db.loadSanPhamByID(edtID_SP.getText().toString());
                    for(SanPham sp:lstSanPham){
                        String s="";
                        s="Ma san pham:"+sp.getId_sp()+"\tTen san pham:"+sp.getTen_sp()+"\tDon gia:"+sp.getDongia()+
                        "\tDon vi tinh:"+sp.getDvt()+"\tMa nha sx:"+sp.getId_nsx();
                        lstHienThi.add(s);
                    }
                    adapter= new ArrayAdapter<String>(ActivitySanPham.this,android.R.layout.simple_list_item_1,lstHienThi);
                    lvSP.setAdapter(adapter);
                }
            }
        });
    }
}