package online.beartreasure.beart_amusement.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.base.Beart_BaseActivity;

public class RegisterActivity extends Beart_BaseActivity implements View.OnClickListener {
    private EditText beart_etusername_register, beart_etverificationcode_register, beart_etpasswrod_register;
    private Button beart_buttonverificationcode_register, beart_buttonlogin_register;
    private int beart_Verification = 60;
    private EventHandler eventHandler;
    private String beart_verification, beart_username, beart_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        afterEvent();
        bindView();
    }

    private void bindView() {
        beart_etusername_register = findViewById(R.id.beart_etusername_register);
        beart_etverificationcode_register = findViewById(R.id.beart_etverificationcode_register);
        beart_etpasswrod_register = findViewById(R.id.beart_etpasswrod_register);
        beart_buttonverificationcode_register = findViewById(R.id.beart_buttonverificationcode_register);
        beart_buttonlogin_register = findViewById(R.id.beart_buttonlogin_register);
    }

    @Override
    public void initDatas() {

    }

    public void startcountDown() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                beart_Verification--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        beart_buttonverificationcode_register.setText(beart_Verification + "S");
                        if (beart_Verification == 0) {
                            beart_buttonverificationcode_register.setEnabled(true);
                            beart_buttonverificationcode_register.setText("点击重新获取");
                            beart_Verification = 60;
                            scheduledExecutorService.shutdown();
                        }
                    }
                });
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void settingView() {
        bindEvent();

    }

    private void bindEvent() {
        beart_buttonverificationcode_register.setOnClickListener(this);
        beart_buttonlogin_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beart_buttonverificationcode_register:
                if (!TextUtils.isEmpty(beart_etusername_register.getText().toString())) {
                    boolean phoneverificationcode = beartisMobileNO(beart_etusername_register.getText().toString());
                    if (phoneverificationcode) {
                        beart_verification = beart_etusername_register.getText().toString().trim();
                        SMSSDK.getVerificationCode("86", beart_verification);
                        beart_buttonverificationcode_register.setEnabled(false);
                        startcountDown();
                    }
                }
                break;
            case R.id.beart_buttonlogin_register:
                if (!TextUtils.isEmpty(beart_etverificationcode_register.getText().toString())) {
                    if (!TextUtils.isEmpty(beart_etpasswrod_register.getText().toString())) {
                        beart_username = beart_etusername_register.getText().toString();
                        beart_password = beart_etpasswrod_register.getText().toString();
                        if (!TextUtils.isEmpty(beart_username) && !TextUtils.isEmpty(beart_password)) {
                            OkHttpUtils.post().url("http://192.168.1.75:8888/jsp/AppRegServlet").addParams("username", beart_username).addParams("userpwd", beart_password).build().execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e(TAG, "onResponse: " + response);
                                }
                            });
                        }
                    } else {
                        beartToast("账号密码不能为空或者错误");
                    }
                } else {
                    beartToast("验证码错误或者错误");
                }
                break;
        }
    }

    private void afterEvent() {//初始化短信验证接口
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, final Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {

                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                    try {
                        JSONObject obj = new JSONObject(throwable.getMessage());
                        final String des = obj.optString("detail");
                        if (!TextUtils.isEmpty(des)) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }
}
