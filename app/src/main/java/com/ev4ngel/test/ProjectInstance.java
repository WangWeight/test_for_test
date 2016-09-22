package com.ev4ngel.test;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ProjectInstance {
    String name;
    private Date create_time;
    private Date access_time;
    private ArrayList<String> mWaylineList=null;//ProjectDatabase共享同一个list，在PDB中管理

    public ProjectInstance(String name, Date create_time, Date access_time, ArrayList<String> waylineList) {
        this.name = name;
        this.create_time = create_time;
        this.access_time = access_time;
        mWaylineList = waylineList;
    }

    public String getName() {
        return name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getAccess_time() {
        return access_time;
    }

    public ArrayList<String> getWaylineList() {
        return mWaylineList;
    }
}
