package com.ev4ngel.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements StartMissionDialog.OnMissionAskedShowListener,
        ProjectOptFrg.OnOpenProjectListener,
        ProjectOptFrg.OnDeleteProjectListener,
        ProjectOptFrg.OnAddProjectListener,
        MainMenuFrg.OnViewClickedShower,
        WayDesignFrg.OnSelectListener{
    ProjectOptFrg prj_frg;
    ProjectOptFrg line_frg;
    MainMenuFrg main_frg;
    Project mProject=null;
    ProjectsConfig mPrjsCfg=null;
    public  void i(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        ProjectDatabase pdb=new ProjectDatabase(this);
        for(int i=0;i<10;i++)
            pdb.addProject("wangwei"+i);
        pdb.addProject("waa");
        pdb.addProject("waa");
        pdb.openProject("wangwei1");
        pdb.deleteProject("waa");
        ArrayList<String> a=pdb.getProjects();

        ((WayDesignFrg) getFragmentManager().findFragmentById(R.id.xx)).setOnSelectListener(this);
/*
        main_frg=(MainMenuFrg) getFragmentManager().findFragmentById(R.id.xxxx);
        prj_frg=new ProjectOptFrg();
        line_frg=new ProjectOptFrg();
        prj_frg.setOnAddPrjListener(this);
        prj_frg.setOnDeletePrjListener(this);
        prj_frg.setOnOpenPrjListener(this);

        if(savedInstanceState!=null){
            mProject.load_project(savedInstanceState.getString("last_prj"));
        }else {

        }
        */
        mProject = new Project();
        mPrjsCfg = ProjectsConfig.load(Project.root_dirname);
        if (mPrjsCfg.recent_project.isEmpty()) {
            mProject.load_default_project();
            mPrjsCfg.setRecent_project(Project.prj_default_name);
            mPrjsCfg.write();
        } else {
            mProject.load_project(mPrjsCfg.recent_project);
        }

        //mProject.get_waypoint_file().get_waypoints();
        //ArrayList<WayPoint> a=mProject.get_current_waypoints();
        //mProject.get_waypoint_file().change_status(1,100000);
        //mProject.save();
        //int i=mProject.get_waypoint_file().index_first_id(100000);
        //i=1;
        mProject.new_project("xxx", true);
        mPrjsCfg.add_prj("xxx");
        mProject.new_project("xxx2", true);
        mPrjsCfg.add_prj("xxx2");
        mProject.new_project("xxx3", true);
        mPrjsCfg.add_prj("xxx3");
        mPrjsCfg.setRecent_project("xxx3");
        mProject.new_waypoint_file("aaaa", true);
        mProject.new_waypoint_file("aaaa1", true);
        mProject.new_waypoint_file("aaaa2", true);
        mPrjsCfg.write();
        int rlt=mProject.load_project("aaaa1");
        rlt=mProject.load_project("xxx2");
        rlt=mProject.load_project("xxx3");
        mProject.load_waypoint_file("aaaa2");
        mProject.get_waypoint_file().add_waypoint(122, 3343, 4);
        mProject.get_waypoint_file().add_waypoint(1222, 3353, 4);
        mProject.get_waypoint_file().add_waypoint(1322, 3343, 14);
        mProject.get_waypoint_file().add_waypoint(1422, 3533, 14);
        mProject.save();
        mProject.get_waypoint_file().change_status(1,100);
        //ArrayList<WayPoint> a=mProject.get_waypoint_file().get_waypoints();
        //a.add(new WayPoint(22211111,333,44));
        mProject.save();
        /**/
        /*
        line_frg.setOnAddPrjListener(this);
        line_frg.setOnDeletePrjListener(this);
        line_frg.setOnOpenPrjListener(this);
        main_frg.setOnViewClickedListener(this);
        getFragmentManager().beginTransaction().add(R.id.prj_frg,prj_frg,"prj_frg")
                .add(R.id.line_frg, line_frg, "line_frg")
                .hide(prj_frg).hide(line_frg).commit();
        final StartMissionDialog sd=new StartMissionDialog();
        sd.setOnMissionAskedShowListener(this);
        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sd.show(getFragmentManager(),"xxx");
            }
        });*/
    }
    public static void log(String msg){
        Log.i("w2", msg);
    }
    @Override
    protected void onStart() {
        super.onStart();
        //prj_frg.setPrj_names(new ArrayList<String>());
        //line_frg.setPrj_names(new ArrayList<String>());
    }

    @Override
    public void onAskedShow(int fly_speed, int return_height) {
        Toast.makeText(getApplicationContext(),"sp:"+fly_speed+";hei:"+return_height,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewClicked(View v, boolean clicked) {
        if(v.getId()==main_frg.prj_view.getId()){
            if(clicked) getFragmentManager().beginTransaction().show(prj_frg).commit();
            else getFragmentManager().beginTransaction().hide(prj_frg).commit();
        }else if(v.getId()==main_frg.line_view.getId()){
            if(clicked) getFragmentManager().beginTransaction().show(line_frg).commit();
            else getFragmentManager().beginTransaction().hide(line_frg).commit();
        }
    }

    @Override
    public void onAddProject(String prj_name) {

    }

    @Override
    public void onDeleteProject(String prj_name) {

    }

    @Override
    public void onOpenProject(String prj_name) {

    }

    @Override
    public void onSwitchChanged(boolean isOn) {
        if(isOn){

        }
    }

    @Override
    public void onHideAll() {

    }

    @Override
    public void onMenuItemSelect(WayDesignFrg.Status status) {
        Log.i("E","status:"+status);
    }
}
