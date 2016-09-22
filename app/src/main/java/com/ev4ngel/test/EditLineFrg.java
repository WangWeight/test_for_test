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
        import android.widget.Switch;
        import android.widget.TextView;

        import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/26.
 */
public class EditLineFrg  extends Fragment implements View.OnClickListener{
    public interface OnViewClickedShower{
        void onViewClicked(View v,boolean clicked);
        void onSwitchChanged(boolean isOn);
    }
    //private String on
    public TextView open_save_view;
    public TextView pick_area_view;
    public TextView draw_view;
    public TextView clear_view;
    TextView show_menu_view;
    ArrayList<View> mViews;
    int color_clicked;
    int color_unclicked;
    boolean view_clicked;
    boolean isShowMenu=true;
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
        View v=inflater.inflate(R.layout.line_design_frg_layout,container,false);
        show_menu_view=(TextView)v.findViewById(R.id.show_menu_view);
        open_save_view=(TextView)v.findViewById(R.id.open_save_view);
        pick_area_view=(TextView)v.findViewById(R.id.pick_area_view);
        draw_view=(TextView)v.findViewById(R.id.draw_view);
        clear_view=(TextView)v.findViewById(R.id.clear_view);
        open_save_view.setBackgroundColor(color_unclicked);
        pick_area_view.setBackgroundColor(color_unclicked);
        draw_view.setBackgroundColor(color_unclicked);
        clear_view.setBackgroundColor(color_unclicked);
        open_save_view.setOnClickListener(this);
        pick_area_view.setOnClickListener(this);
        draw_view.setOnClickListener(this);
        clear_view.setOnClickListener(this);
        show_menu_view.setOnClickListener(this);
        mViews.add(open_save_view);
        mViews.add(pick_area_view);
        mViews.add(draw_view);
        mViews.add(clear_view);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==show_menu_view.getId()){
            if(isShowMenu){
                isShowMenu=false;
                show_menu_view.setText("<<");
            }else{
                isShowMenu=true;
                show_menu_view.setText(">>");
            }
        }
        for(View vv:mViews){
            if(isShowMenu) {
                vv.setVisibility(View.VISIBLE);
                ColorDrawable cd = (ColorDrawable) ((TextView) vv).getBackground();
                int color = cd.getColor();
                if (vv.getId() == v.getId()) {
                    if (color == color_clicked) {
                        if (mOnViewClickedListener != null)
                            mOnViewClickedListener.onViewClicked(v, false);
                        v.setBackgroundColor(color_unclicked);
                    } else {
                        if (mOnViewClickedListener != null)
                            mOnViewClickedListener.onViewClicked(v, true);
                        v.setBackgroundColor(color_clicked);
                    }
                } else {
                    if (color == color_clicked) {
                        vv.setBackgroundColor(color_unclicked);
                        if (mOnViewClickedListener != null)
                            mOnViewClickedListener.onViewClicked(v, false);
                    }
                }
            }else{
                vv.setVisibility(View.GONE);
                if(mOnViewClickedListener!=null)
                mOnViewClickedListener.onViewClicked(vv,false);
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }
    }
}