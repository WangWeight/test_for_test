package com.ev4ngel.test;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/22.
 * implement OnAddProject,OnDeleteProject,OnOpenProject
 */
public class ProjectOptFrg extends Fragment implements AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener,
        View.OnClickListener,
        AlertDialog.OnClickListener,
        TextWatcher{
    OnAddProjectListener mAddPrjListener=null;
    OnDeleteProjectListener mDeletePrjListener=null;
    OnOpenProjectListener mOpenPrjListener=null;
    ListView lv;
    boolean isShowTmp=false;
    int select_item_id=-1;
    AlertDialog mAdOpen;
    AlertDialog mAdDelete;
    EditText mEditText=null;
    TextView mButDel;
    TextView mButAdd;
    private ArrayList<String> prj_names=null;
    private ArrayAdapter<String> prj_names_adapter;
    private ArrayAdapter<String> tmp_prj_names_adapter;
    private ArrayList<String> tmp_prj_names=null;

    public interface OnAddProjectListener {
        void onAddProject(String prj_name);
    }
    public interface OnDeleteProjectListener {
        void onDeleteProject(String prj_name);
    }
    public interface OnOpenProjectListener{
        void onOpenProject(String prj_name);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.main_new_project_layout,container,false);
        lv=(ListView) v.findViewById(R.id.main_prj_list_vl);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);

        mButAdd=(TextView)v.findViewById(R.id.main_prj_add_bt);
        mButAdd.setOnClickListener(this);
        mButDel=(TextView)v.findViewById(R.id.main_prj_del_bt);
        mButDel.setOnClickListener(this);
        mEditText=(EditText)v.findViewById(R.id.main_prj_add_et);
        mEditText.addTextChangedListener(this);
        mEditText.setOnClickListener(this);
        if(savedInstanceState!=null)
        {
            mEditText.setText(savedInstanceState.getString("edittext"));
        }
        mAdDelete=new AlertDialog.Builder(getActivity()).setTitle("确认删除?").setPositiveButton("删除",this).setNegativeButton("取消",this).create();
        mAdOpen=new AlertDialog.Builder(getActivity()).setTitle("确认打开?").setPositiveButton("确定",this).setNegativeButton("取消",this).create();

        tmp_prj_names=new ArrayList<>();
        tmp_prj_names_adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,tmp_prj_names);
        return v;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("edittext", mEditText.getText().toString());
    }

    public void setPrj_names(ArrayList<String> prj_names) {
        this.prj_names = prj_names;
        prj_names_adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,this.prj_names);
        lv.setAdapter(prj_names_adapter);
        isShowTmp=false;
    }

    public void setOnDeletePrjListener(OnDeleteProjectListener l)
    {
        mDeletePrjListener=l;
    }

    public void setOnAddPrjListener(OnAddProjectListener addPrjListener) {
        mAddPrjListener = addPrjListener;
    }

    public void setOnOpenPrjListener(OnOpenProjectListener openPrjListener) {
        mOpenPrjListener = openPrjListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        select_item_id=position;
        mAdOpen.show();
        if(mOpenPrjListener!=null)
            mOpenPrjListener.onOpenProject(prj_names.get(position));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        select_item_id=position;
        mAdDelete.show();
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==mButAdd.getId()){
            if(prj_names!=null) {
                int rlt = prj_names.indexOf(mEditText.getText().toString());
                if (rlt < 0) {
                    prj_names.add(0,mEditText.getText().toString());
                    mEditText.setText("");
                    prj_names_adapter.notifyDataSetChanged();
                    if(mAddPrjListener!=null)
                        mAddPrjListener.onAddProject(mEditText.getText().toString());
                }
            }
            lv.setAdapter(prj_names_adapter);
            tmp_prj_names.removeAll(tmp_prj_names);
            isShowTmp=false;
        }else if(v.getId()==mButDel.getId()){
            mEditText.setText("");
            if(isShowTmp&&lv!=null&&prj_names_adapter!=null){
                lv.setAdapter(prj_names_adapter);
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(dialog!=null && prj_names!=null)
        {
            if(dialog.toString().equals(mAdDelete.toString())){
                if(which==DialogInterface.BUTTON_POSITIVE)
                {
                    if(isShowTmp)
                        select_item_id=prj_names.indexOf(tmp_prj_names.get(select_item_id));
                    prj_names.remove(select_item_id);
                    prj_names_adapter.notifyDataSetChanged();
                    if(mDeletePrjListener!=null)
                        mDeletePrjListener.onDeleteProject(prj_names.get(select_item_id));
                    select_item_id=-1;
                }
            }else if(dialog.toString().equals(mAdOpen.toString())){
                if(mOpenPrjListener!=null) {
                    if(isShowTmp)
                        select_item_id=prj_names.indexOf(tmp_prj_names.get(select_item_id));
                    mOpenPrjListener.onOpenProject(prj_names.get(select_item_id));
                }
                select_item_id=-1;
            }
            if(isShowTmp) {
                lv.setAdapter(prj_names_adapter);
                isShowTmp=false;
            }
        }
    }
    public void setNewPrjName(){
        mEditText.setText(new SimpleDateFormat("yy_MM_dd_kk_mm_ss").format(new Date()));
    }
    @Override
    public void afterTextChanged(Editable s) {
        if(prj_names!=null)
        {
            String txt=mEditText.getText().toString();
            if(tmp_prj_names.size()>0)
                tmp_prj_names.removeAll(tmp_prj_names);
            for(String t:prj_names)
            {
                if(t.indexOf(txt)>=0)
                {
                    tmp_prj_names.add(t);
                }
            }
            if(tmp_prj_names.size()>0 &&mEditText.getText().length() != 0) {
                tmp_prj_names_adapter.notifyDataSetChanged();
                lv.setAdapter(tmp_prj_names_adapter);
                isShowTmp=true;
            }else{
                lv.setAdapter(prj_names_adapter);
                isShowTmp=false;
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

}
