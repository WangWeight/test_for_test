package com.ev4ngel.test;

import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/26.
 */
public class MainMenuFrg extends Fragment implements View.OnClickListener,
        Switch.OnCheckedChangeListener{
    public interface OnViewClickedShower{
        void onViewClicked(View v,boolean clicked);
        void onSwitchChanged(boolean isOn);
        void onHideAll();
    }
    //private String on
    public TextView prj_view;
    public TextView line_view;
    public TextView edit_view;
    public TextView set_view;
    ArrayList<View> mViews;
    Switch mSwitch;//SeekBar mSeekBar;
    int color_clicked;
    int color_unclicked;
    boolean view_clicked;
    private OnViewClickedShower mOnViewClickedListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        color_clicked=Color.argb(120,255,255,255);
        color_unclicked=Color.argb(60,0,0,0);
        view_clicked=false;
        mViews=new ArrayList<>();
    }
    public void setOnViewClickedListener(OnViewClickedShower l)
    {
        mOnViewClickedListener=l;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.main_menu_layout,container,false);
        mSwitch=(Switch)v.findViewById(R.id.auto_or_man_sb);
        prj_view=(TextView)v.findViewById(R.id.prj_view);
        line_view=(TextView)v.findViewById(R.id.line_view);
        edit_view=(TextView)v.findViewById(R.id.edit_view);
        set_view=(TextView)v.findViewById(R.id.set_view);
        prj_view.setBackgroundColor(color_unclicked);
        line_view.setBackgroundColor(color_unclicked);
        edit_view.setBackgroundColor(color_unclicked);
        set_view.setBackgroundColor(color_unclicked);
        prj_view.setOnClickListener(this);
        line_view.setOnClickListener(this);
        edit_view.setOnClickListener(this);
        set_view.setOnClickListener(this);
        mSwitch.setOnCheckedChangeListener(this);
        mViews.add(prj_view);
        mViews.add(line_view);
        mViews.add(edit_view);
        mViews.add(set_view);
        return v;
    }

    @Override
    public void onClick(View v) {
        for(View vv:mViews){
            ColorDrawable cd=(ColorDrawable)((TextView)vv).getBackground();
            int color=cd.getColor();
            if(vv.getId()==v.getId()){
                if(color==color_clicked){
                    if(mOnViewClickedListener!=null)
                        mOnViewClickedListener.onViewClicked(v,false);
                    v.setBackgroundColor(color_unclicked);
                }else {
                    if(mOnViewClickedListener!=null)
                        mOnViewClickedListener.onViewClicked(v,true);
                    v.setBackgroundColor(color_clicked);
                }
            }else{
                if(color==color_clicked) {
                    vv.setBackgroundColor(color_unclicked);
                    if (mOnViewClickedListener != null)
                        mOnViewClickedListener.onViewClicked(v, false);
                }
            }
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(mOnViewClickedListener!=null)
            mOnViewClickedListener.onSwitchChanged(isChecked);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            if(mOnViewClickedListener!=null)
                mOnViewClickedListener.onHideAll();
        }
    }
}
