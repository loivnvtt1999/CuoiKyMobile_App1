package com.example.democuoiki_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnThem, btnSelect, btnAddSP_NSX;
    EditText edtIDNSX, edtTenNSX, edtDiaChiNSX;
    CustomListViewNSX adapter;
    ListView lvNSX;
    ArrayList<NhaSanXuat> lstNhaSanXuat;
    DbSanPham db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnThem=(Button)findViewById(R.id.btnThem);
        btnSelect=(Button)findViewById(R.id.btnSelect);
        btnAddSP_NSX=(Button)findViewById(R.id.btnAddSP_NSX);
        edtIDNSX=(EditText)findViewById(R.id.edtID_NSX);
        edtTenNSX=(EditText)findViewById(R.id.edtTenNSX);
        edtDiaChiNSX=(EditText)findViewById(R.id.edtDiaChiNSX);
        lstNhaSanXuat= new ArrayList<NhaSanXuat>();
        db=new DbSanPham(this);
        lvNSX=(ListView)findViewById(R.id.lvNSX);
        lstNhaSanXuat=db.loadAllNSX();
        adapter=new CustomListViewNSX(this,R.layout.listview_nsx,lstNhaSanXuat);
        lvNSX.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhaSanXuat nsx= new NhaSanXuat();
                nsx.setId_nsx(edtIDNSX.getText().toString());
                nsx.setTen(edtTenNSX.getText().toString());
                nsx.setDiachi((edtDiaChiNSX.getText().toString()));
                if(db.insertNSX(nsx)>0){
                    Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    lstNhaSanXuat.clear();
                    lstNhaSanXuat.addAll(db.loadAllNSX());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtIDNSX.getText().length()==0){
                   lstNhaSanXuat.clear();
                   lstNhaSanXuat.addAll(db.loadAllNSX());
                   adapter.notifyDataSetChanged();
                }
                else{
                    lstNhaSanXuat.clear();
                    lstNhaSanXuat.addAll(db.loadNSXByID(edtIDNSX.getText().toString()));
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnAddSP_NSX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, ActivitySanPham.class);
                startActivity(intent);
            }
        });
    }
}