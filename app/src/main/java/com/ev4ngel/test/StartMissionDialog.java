package com.ev4ngel.test;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/25.
 */
public class StartMissionDialog extends DialogFragment implements
        SeekBar.OnSeekBarChangeListener ,
        View.OnClickListener{
    OnMissionAskedShowListener mListener=null;
    SeekBar speed_sb;
    SeekBar height_sb;
    TextView speed_tv;
    TextView height_tv;
    Button ok_but;
    Button cancel_but;

    @Override
    public void onClick(View v) {
        if(v.getId()==ok_but.getId()){
            if(mListener!=null){
                mListener.onAskedShow(speed_sb.getProgress(),height_sb.getProgress());
            }
            dismiss();
        }else if(v.getId()==cancel_but.getId()){
            dismiss();
        }
    }

    public interface OnMissionAskedShowListener{
        void onAskedShow(int fly_speed,int return_height);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.mission_start_ask_layout,container,false);
        getDialog().setTitle("请确认任务信息");
        speed_sb=(SeekBar)v.findViewById(R.id.speed_sb);
        height_sb=(SeekBar)v.findViewById(R.id.height_sb);
        speed_tv=(TextView)v.findViewById(R.id.speed_tv);
        height_tv=(TextView)v.findViewById(R.id.height_tv);
        ok_but=(Button)v.findViewById(R.id.ok_but);
        cancel_but=(Button)v.findViewById(R.id.cancel_but);
        speed_sb.setOnSeekBarChangeListener(this);
        height_sb.setOnSeekBarChangeListener(this);
        ok_but.setOnClickListener(this);
        cancel_but.setOnClickListener(this);
        return v;
    }
    public void setOnMissionAskedShowListener(OnMissionAskedShowListener l)
    {
        mListener=l;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.getId()==speed_sb.getId()){
            speed_tv.setText(progress+"m/s");
        }else if(seekBar.getId()==height_sb.getId()){
            height_tv.setText(progress+"m");
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
}
