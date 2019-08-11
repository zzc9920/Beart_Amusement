package online.beartreasure.beart_amusement.ui.fragment;

import android.content.Intent;
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

    private TextView tv_login;
    private TextView tv_kefu;

    public TextView getTv_login() {
        return tv_login;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        beart_view = inflater.inflate(R.layout.fragment_my, container, false);

        tv_login = beart_view.findViewById(R.id.tv_login);
        tv_kefu = beart_view.findViewById(R.id.tv_kefu);

        tv_login.setOnClickListener(this);
        tv_kefu.setOnClickListener(this);

        return beart_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
