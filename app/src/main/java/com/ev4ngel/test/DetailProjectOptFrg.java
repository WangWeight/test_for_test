package com.ev4ngel.test;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/6.
 * 项目名称：xxxx
 * 起始日期：xxx
 * 最近更新：xxxx
 * 航线数量：xx
 * 完成度：
 * 大致位置：辽宁省新民市xxx
 */
public class DetailProjectOptFrg extends Fragment implements View.OnClickListener{
    ProjectOptFrg.OnDeleteProjectListener mDelListener=null;
    ProjectOptFrg.OnOpenProjectListener mOpenListener=null;
    TextView mContent;
    Button mOpen_bt;
    Button mDel_bt;
    //Project info?
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.main_prj_detail,container,false);
        mOpen_bt=(Button)v.findViewById(R.id.open_prj_bt);
        mDel_bt=(Button)v.findViewById(R.id.del_prj_bt);
        mContent=(TextView)v.findViewById(R.id.detail_prj_tv);
        mOpen_bt.setOnClickListener(this);;
        mDel_bt.setOnClickListener(this);

        return v;
    }
    public void setProjectInfo(){
        mContent.setText(Html.fromHtml(""));
    }
    public void setDelListener(ProjectOptFrg.OnDeleteProjectListener delListener) {
        mDelListener = delListener;
    }

    public void setOpenListener(ProjectOptFrg.OnOpenProjectListener openListener) {
        mOpenListener = openListener;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==mOpen_bt.getId()){
            mOpenListener.onOpenProject("");
        }else if(v.getId()==mDel_bt.getId()){
            mDelListener.onDeleteProject("");
        }
    }
}
