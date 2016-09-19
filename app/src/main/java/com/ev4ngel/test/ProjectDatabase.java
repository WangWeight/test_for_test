package com.ev4ngel.test;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/19.
 */
public class ProjectDatabase extends SQLiteOpenHelper {
    public static final String db_name="project.db";
    public static final int db_version=1;
    private int current_project_id=-1;
    private int current_wayline_id=-1;
    public  ProjectDatabase(Context c){
        super(c,db_name,null,db_version);
    }

    public int getCurrent_project_id() {
        return current_project_id;
    }

    public int getCurrent_wayline_id() {
        return current_wayline_id;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE project(id integer primary key ,name text,create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP);" +
                "CREATE TABLE wayline(id integer primary key,name text,prj_id int,create_time timestamp,access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP);" +
                "CREATE TABLE waypoint(id integer primary key,wayline_id int,status int);" +
                "CREATE TABLE photoinfo(id integer primary key,name text,lat float,lng float,yaw float,pitch float,wayline_id int,take_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void exportPhotosInfo(String line){

    }
    public int getIndexByProjectName(String name){
        Cursor c=getReadableDatabase().rawQuery("SELECT id FROM project where name=? limit=1",new String[]{name});
        return c.getInt(c.getColumnIndex("name"));
    }
    public ArrayList<String> getProjects(){
        Cursor c=getReadableDatabase().rawQuery("select * from project order by access_time desc",null);
        //getReadableDatabase().query("project", new String[]{"id", "name", "create_time"}, null, null, null, null, "access_time");
        ArrayList<String> rlt=new ArrayList<>();
        for(int i=0;i<c.getCount();i++){
            rlt.add(c.getString(c.getColumnIndex("name")));
            c.moveToNext();
        }
        return rlt;
    }
    public ArrayList<String> getWaylines(String prj_name){
        ArrayList<String> tmp=new ArrayList<>();
        if(prj_name!=null){
            getReadableDatabase().query("wayline",new String[]{"name","id"},"project_id=?",new String[]{""+getIndexByProjectName(prj_name)},null,null,"create_time");


        }else if(current_project_id!=-1){

        }
        return tmp;
    }
    public int addProject(String name){
        if(getProjects().contains(name))
            return -1;
        getWritableDatabase().execSQL("INSERT INTO project(name) values('"+name+"')");
        return 0;
    }
    public int openProject(String name){
        try {
            getWritableDatabase().execSQL("UPDATE TABLE project SET access_time=" + System.currentTimeMillis());
            current_project_id=getIndexByProjectName(name);
            return current_project_id;
        }catch (SQLException e){
            return -1;
        }

    }
    public int openWayline(String name){
        try{
            Cursor c=getWritableDatabase().query("wayline", new String[]{"id"}, "name=?", new String[]{name}, null, null, null, "1");
            if(c.getCount()>0)
                current_wayline_id=c.getInt(c.getColumnIndex("id"));
            return current_wayline_id;
        }catch (SQLException e)
        {
            return -1;
        }
    }
    public void deleteProject(String name){
        getWritableDatabase().delete("project","name=?",new String[]{name});
    }
    public void addPhotoInfo(String file_name,float lat,float lng,float yaw,float pitch){
        if(current_project_id==-1||current_wayline_id==-1)
            return;
        else{
            getWritableDatabase().execSQL("INSERT INTO photoinfo(name,lat,lng,yaw,pitch,wayline_id) values('"+file_name+"',"+lat+","+lng+","+yaw+","+pitch+","+current_wayline_id+")");
        }
    }
}
