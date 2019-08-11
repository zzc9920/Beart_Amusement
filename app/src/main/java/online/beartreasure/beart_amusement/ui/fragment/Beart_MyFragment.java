package online.beartreasure.beart_amusement.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.other.ToFragmentListener;
import online.beartreasure.beart_amusement.ui.activity.LoginActivity;

public class Beart_MyFragment extends Fragment implements View.OnClickListener, ToFragmentListener {
    private View beart_view;
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;
    private TextView tv_login;
    private TextView tv_kefu;

    public TextView getTv_login() {
        return tv_login;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        beart_view = inflater.inflate(R.layout.fragment_my, container, false);
        textView1 = beart_view.findViewById(R.id.t1);
        textView2 = beart_view.findViewById(R.id.t2);
        textView3 = beart_view.findViewById(R.id.t3);
        textView4 = beart_view.findViewById(R.id.t4);
        textView5 = beart_view.findViewById(R.id.t5);
        textView6 = beart_view.findViewById(R.id.t6);
        tv_login = beart_view.findViewById(R.id.tv_login);
        tv_kefu = beart_view.findViewById(R.id.tv_kefu);

        tv_login.setOnClickListener(this);
        tv_kefu.setOnClickListener(this);
        Drawable drawable1 = getResources().getDrawable(R.mipmap.beart_writtenwords);
        Drawable drawable2 = getResources().getDrawable(R.mipmap.beart_edit);
        Drawable drawable3 = getResources().getDrawable(R.mipmap.beart_interstellar);
        Drawable drawable4 = getResources().getDrawable(R.mipmap.guankan);
        Drawable drawable5 = getResources().getDrawable(R.mipmap.kuohao);
        Drawable drawable7 = getResources().getDrawable(R.mipmap.kuohao);
        Drawable drawable8 = getResources().getDrawable(R.mipmap.kuohao);
        Drawable drawable9 = getResources().getDrawable(R.mipmap.beart_setting);
        drawable1.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable2.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable3.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable4.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable5.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable7.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable8.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable9.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        textView1.setCompoundDrawables(null, drawable1, null, null);//只放左边
        textView2.setCompoundDrawables(null, drawable2, null, null);//只放左边
        textView3.setCompoundDrawables(null, drawable3, null, null);//只放左边
        textView4.setCompoundDrawables(null, drawable4, null, null);//只放左边
        textView5.setCompoundDrawables(null, null, drawable8, null);//只放左边
        textView6.setCompoundDrawables(null, null, drawable8, null);//只放左边
        tv_kefu.setCompoundDrawables(null, null, drawable8, null);//只放左边

        SharedPreferences sp = getActivity().getSharedPreferences("sp",0);
            tv_login.setText(sp.getString("username","登录/注册"));
            if(!sp.getString("username","登录/注册").equals("登录/注册")){
                tv_login.setEnabled(false);
            }
        return beart_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_kefu:

                break;

        }
    }



    @Override
    public void onTypeClick(String message) {

    }
}
