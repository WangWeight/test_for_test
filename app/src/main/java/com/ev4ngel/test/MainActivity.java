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
        //ProjectDatabase pdb=ProjectDatabase.getInstance(this);
/*
        for(int ij=70;ij<100;ij++) {
            pdb.addProject("new_prj_"+ij);
            Log.i("E", "new Project:" + pdb.openProject("new_prj_" + ij));
            for(int i=0;i<10;i++)
            {
                pdb.addWayline("line_" + i);
                pdb.openWayline("line_" + i);
                for(int j=0;j<20;j++){
                    pdb.addWayPoint(0,new WayPoint());
                    pdb.addPhotoInfo("Photo"+j,j,j,j,j);
                }
            }
        }
        try{
            Thread.sleep(1);
        }catch (Exception e){}
        for(int ij=70;ij<100;ij++) {
            ProjectInstance pi=pdb.getDetailProject("new_prj_" + ij);
            Log.i("XXX",pi.getName()+":"+pi.getAccess_time()+":"+pi.getCreate_time());
            pdb.openProject("new_prj_"+ij);
            Log.i("xxx",pdb.getWayPointList().toString());
            ArrayList<String> a=pdb.getWaylines(null);
            Log.i("xxx", "open prj:new_prj" + ij + ",new_line:" + a.toString());
            if(a.size()>0){
                Log.i("xxx","new_line:"+a.get(0));
                Log.i("xxx","new_line:"+pdb.getWaypoints(a.get(0)));
            }
            try{
                Thread.sleep(1);
            }catch (Exception e){}

        }*/
        ((CrossView)findViewById(R.id.xx)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"xxx",Toast.LENGTH_SHORT).show();
            }
        });
        final PoseBall pb=(PoseBall)findViewById(R.id.pose_ball);
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long l=System.currentTimeMillis();
               int r=(int)l/100%90-45;
                int p=(int)l%90-45;
                int y=(int)l/10000/180;

                pb.updateStatus(r,p);
            }
        });
        /*
        pdb.openProject("wangwei89");
        pdb.openProject("wangwei80");

        for(int i=0;i<10;i++)
        {
            pdb.openWayline("line_" + i);
        }
        Log.i("E", ",id:" + pdb.getCurrent_project_id());
*/
       // ((WayDesignFrg) getFragmentManager().findFragmentById(R.id.xx)).setOnSelectListener(this);
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

        mProject = new Project();
        mPrjsCfg = ProjectsConfig.load(Project.root_dirname);
        if (mPrjsCfg.recent_project.isEmpty()) {
            mProject.load_default_project();
            mPrjsCfg.setRecent_project(Project.prj_default_name);
            mPrjsCfg.write();
        } else {
            mProject.load_project(mPrjsCfg.recent_project);
        }
*/
        //mProject.get_waypoint_file().get_waypoints();
        //ArrayList<WayPoint> a=mProject.get_current_waypoints();
        //mProject.get_waypoint_file().change_status(1,100000);
        //mProject.save();
        //int i=mProject.get_waypoint_file().index_first_id(100000);
        //i=1;
        /*
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
        */
        /*
        line_frg.setOnAddPrjListener(this);
        line_frg.setOnDeletePrjListener(this);
        line_frg.setOnOpenPrjListener(this);
        main_frg.setOnViewClickedListener(this);
        getFragmentManager().beginTransaction().add(R.id.prj_frg,prj_frg,"prj_frg")
                .add(R.id.line_frg, line_frg, "line_frg")
                .hide(prj_frg).hide(line_frg).commit();
        final StartMissionDialog sd=new StartMissionDialog();
        sd.setOnMissionAskedShowListener(this);*/
        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Img",Toast.LENGTH_SHORT).show();//sd.show(getFragmentManager(),"xxx");
            }
        });
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
