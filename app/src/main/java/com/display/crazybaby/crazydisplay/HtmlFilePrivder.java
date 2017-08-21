package com.display.crazybaby.crazydisplay;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.display.crazybaby.crazydisplay.tool.NetTools;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by walle on 2017/7/10.
 */

public class HtmlFilePrivder extends ContentProvider {
    private final static String AUTHORITY = "com.crazybaby.hmtl";
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int LOCKSCREEN_WALLPAPER = 1;
    static {
        sURIMatcher.addURI(AUTHORITY, NetTools.dirName+"/*", LOCKSCREEN_WALLPAPER);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    String TAG="htmlprider";
    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode)
            throws FileNotFoundException {
        Log.d(TAG, "openFile::::::::getType(uri) = "+getType(uri));
        Log.d(TAG, "uri = "+uri);
        Log.d(TAG, "uri.getPath() = "+uri.getPath());
        File filea =getContext().getFilesDir();
        File filexcel=new File(filea.getAbsolutePath()+"/"+ NetTools.dirName);
        if (filexcel.exists()&&filexcel.isDirectory()){
            File[] files=filexcel.listFiles();
            if (files.length>0){
                return ParcelFileDescriptor.open(files[0],
                        ParcelFileDescriptor.MODE_READ_ONLY);
            }else {
                Log.d(TAG, "geted but no file = "+filexcel.getPath());
            }

        }

        throw new FileNotFoundException(uri.getPath());
    }
}
