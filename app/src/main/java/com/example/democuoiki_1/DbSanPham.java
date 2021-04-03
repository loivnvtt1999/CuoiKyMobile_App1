package com.example.democuoiki_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbSanPham extends SQLiteOpenHelper {
    Context context;
    private final  static  String query_nsx="create table tbl_nsx("+
            "id_nsx text primary key not null,"+
            "ten_nsx text,"+
            "diachi_nsx text)";
    private final  static String query_sanpham="create table tbl_sanpham("+
            "id_sp text primary key not null,"+
            "ten_sp text,"+
            "donvi text,"+
            "dongia double,"+
            "id_nsx text,"+
            "foreign key(id_nsx) references tbl_nsx(id_nsx)on delete cascade on update cascade)";

    @Override
    public void onOpen(SQLiteDatabase db) {
        if(!db.isReadOnly()){
            db.execSQL("pragma foreign_keys=ON");
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    public DbSanPham(@Nullable Context context) {
        super(context, "db_sanpham", null, 3);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query_nsx);
        sqLiteDatabase.execSQL(query_sanpham);
    }
    public long insertNSX(NhaSanXuat nsx){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("id_nsx",nsx.getId_nsx());
        contentValues.put("ten_nsx",nsx.getTen());
        contentValues.put("diachi_nsx",nsx.getDiachi());
        long n= db.insert("tbl_nsx",null,contentValues);
        return n;
    }
    public long insertSanPham(SanPham sanPham){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("id_sp",sanPham.getId_sp());
        contentValues.put("ten_sp",sanPham.getTen_sp());
        contentValues.put("donvi",sanPham.getDvt());
        contentValues.put("dongia",sanPham.getDongia());
        contentValues.put("id_nsx",sanPham.getId_nsx());
        long n=db.insert("tbl_sanpham",null,contentValues);
        return n;
    }
    public ArrayList<NhaSanXuat> loadAllNSX(){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<NhaSanXuat> lstNSX= new ArrayList<NhaSanXuat>();
        String query="select * from tbl_nsx";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                NhaSanXuat nsx= new NhaSanXuat();
                nsx.setId_nsx(cursor.getString(0));
                nsx.setTen(cursor.getString(1));
                nsx.setDiachi(cursor.getString(2));
                lstNSX.add(nsx);
            }while (cursor.moveToNext());
        }
        return lstNSX;
    }
    public ArrayList<NhaSanXuat> loadNSXByID(String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<NhaSanXuat> lstNSX= new ArrayList<NhaSanXuat>();
        String query="select * from tbl_nsx where id_nsx="+"'"+ID+"'";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                NhaSanXuat nsx= new NhaSanXuat();
                nsx.setId_nsx(cursor.getString(0));
                nsx.setTen(cursor.getString(1));
                nsx.setDiachi(cursor.getString(2));
                lstNSX.add(nsx);
            }while (cursor.moveToNext());
        }
        return lstNSX;
    }
    public ArrayList<SanPham> loadAllSanPham(){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<SanPham> lstSP= new ArrayList<SanPham>();
        String query="select * from tbl_sanpham ";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                SanPham sp= new SanPham();
                sp.setId_sp(cursor.getString(0));
                sp.setTen_sp(cursor.getString(1));
                sp.setDvt(cursor.getString(2));
                sp.setDongia(cursor.getDouble(3));
                sp.setId_nsx(cursor.getString(4));
                lstSP.add(sp);
            }while (cursor.moveToNext());
        }
        return lstSP;
    }
    public ArrayList<SanPham> loadSanPhamByID(String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<SanPham> lstSP= new ArrayList<SanPham>();
        String query="select * from tbl_sanpham where id_sp="+"'"+ID+"'";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                SanPham sp= new SanPham();
                sp.setId_sp(cursor.getString(0));
                sp.setTen_sp(cursor.getString(1));
                sp.setDvt(cursor.getString(2));
                sp.setDongia(cursor.getDouble(3));
                sp.setId_nsx(cursor.getString(4));
                lstSP.add(sp);
            }while (cursor.moveToNext());
        }
        return lstSP;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists tbl_nsx");
        sqLiteDatabase.execSQL("drop table if exists tbl_sanpham");
        onCreate(sqLiteDatabase);
    }
}
