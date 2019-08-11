package online.beartreasure.beart_amusement.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.base.Beart_BaseActivity;
import online.beartreasure.beart_amusement.other.ToFragmentListener;
import online.beartreasure.beart_amusement.ui.fragment.Beart_MyFragment;
import online.beartreasure.beart_amusement.utils.Beart_NetworkInterfaceSatinApi;

public class LoginActivity extends Beart_BaseActivity implements View.OnClickListener {
    private EditText beart_etusername_login, beart_etpasswrod_login;
    private Button beart_buttonlogin_login;
    private String beart_username, beart_password;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        bindView();
    }

    private void bindView() {
        beart_etusername_login = findViewById(R.id.beart_etusername_login);
        beart_etpasswrod_login = findViewById(R.id.beart_etpasswrod_login);
        beart_buttonlogin_login = findViewById(R.id.beart_buttonlogin_login);
        tv1 = findViewById(R.id.tv1);
    }

    @Override
    public void initDatas() {

    }


    @Override
    public void settingView() {
        beart_buttonlogin_login.setOnClickListener(this);
        tv1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beart_buttonlogin_login:
                if (!TextUtils.isEmpty(beart_etusername_login.getText().toString())) {
                    if (!TextUtils.isEmpty(beart_etpasswrod_login.getText().toString())) {
                        beart_username = beart_etusername_login.getText().toString();
                        beart_password = beart_etpasswrod_login.getText().toString();
                        if (!TextUtils.isEmpty(beart_username) && !TextUtils.isEmpty(beart_password)) {
                            OkHttpUtils.post().url(Beart_NetworkInterfaceSatinApi.Beart_LOGINULR).addParams("username", beart_username).addParams("userpwd", beart_password).build().execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e(TAG, "onResponse: " + e.getMessage());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e(TAG, "onResponse: " + response);
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        switch (object.getString("code")) {
                                            case "200":
                                                SharedPreferences sp = LoginActivity.this.getSharedPreferences("sp", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sp.edit();
                                                editor.putString("username", beart_username);
                                                editor.commit();
//                                                startActivity(new Intent(LoginActivity.this, IndexActivity.class));
                                                finish();
                                                break;
                                            case "404":
                                                Toast.makeText(LoginActivity.this, "账号或密码有误", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "500":
                                                Toast.makeText(LoginActivity.this, "该账号已被冻结", Toast.LENGTH_SHORT).show();
                                                break;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    } else {
                        beartToast("密码不能为空");
                    }
                } else {
                    beartToast("账号不能为空");
                }
                break;
            case R.id.tv1:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }
}
