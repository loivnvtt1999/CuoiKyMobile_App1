package com.example.democuoiki_1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {
    static final String AUTHORITY="com.example.democuoiki_1";
    static final String CONTENT_PROVIDER="contentprovider";
    static final String URL="content://"+AUTHORITY+"/"+CONTENT_PROVIDER;
    static final Uri CONTENT_URI=Uri.parse(URL);
    static final String PRODUCT_TABLE="tbl_sanpham";
    private SQLiteDatabase db;
    static final int ONE=1;
    static final int ALL=2;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,PRODUCT_TABLE,ONE);
        uriMatcher.addURI(AUTHORITY,PRODUCT_TABLE+"/#",ALL);
    }
    private static HashMap<String,String> PROJECTION_MAP;
    public MyContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        Context context=getContext();
        DbSanPham databaseHelper=new DbSanPham(context);
        db=databaseHelper.getWritableDatabase();
        if(db==null)
            return false;
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteQueryBuilder sqLiteQueryBuilder= new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(PRODUCT_TABLE);
        switch (uriMatcher.match(uri)){
            case ALL:
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case ONE:
                sqLiteQueryBuilder.appendWhere("id_sp ="+uri.getPathSegments().get(0));
                break;
        }
        if(sortOrder==null||sortOrder==""){
            sortOrder="ten_sp";
        }
        Cursor cursor=sqLiteQueryBuilder.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
