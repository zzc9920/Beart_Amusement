package online.beartreasure.beart_amusement.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
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
import online.beartreasure.beart_amusement.bean.Beart_Code;
import online.beartreasure.beart_amusement.utils.Beart_NetworkInterfaceSatinApi;

public class RegisterActivity extends Beart_BaseActivity implements View.OnClickListener {
    private EditText beart_etusername_register, beart_etverificationcode_register, beart_etpasswrod_register;
    private Button beart_buttonverificationcode_register, beart_buttonlogin_register;
    private int beart_Verification = 60;
    private EventHandler eventHandler;
    private String beart_verification, beart_username, beart_password;
    private Beart_Code beart_code;

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
                        beart_username = beart_etusername_register.getText().toString().trim();
                        Log.e(TAG, "onClick: " + beart_username);
                        SMSSDK.getVerificationCode("86", beart_username);
                        beart_buttonverificationcode_register.setEnabled(false);
                        startcountDown();
                    } else {
                        beartToast("手机号或者邮箱输入错误请检查");
                    }
                }
                break;
            case R.id.beart_buttonlogin_register:
                if (!TextUtils.isEmpty(beart_etverificationcode_register.getText().toString())) {
                    if (!TextUtils.isEmpty(beart_etpasswrod_register.getText().toString())) {
                        beart_verification = beart_etverificationcode_register.getText().toString();
                        beart_password = beart_etpasswrod_register.getText().toString();
                        SMSSDK.submitVerificationCode("86", beart_username, beart_verification);
                        Log.e(TAG, "onClick: " + beart_username + beart_verification);
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
//                                T.showAnimToast(RegisterActivity.this, "验证成功");
                                if (!TextUtils.isEmpty(beart_username) && !TextUtils.isEmpty(beart_password)) {

                                    OkHttpUtils.post().url(Beart_NetworkInterfaceSatinApi.Beart_REGITER).addParams("username", beart_username).addParams("userpwd", beart_password).build().execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.e(TAG, "onResponse: " + response);
                                            Gson gson = new Gson();
                                            beart_code = gson.fromJson(response, Beart_Code.class);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (beart_code.getCode().equals("200")) {
                                                        beartToast("注册成功");
                                                        new Handler().postDelayed(new Runnable() {
                                                            public void run() {
                                                                //execute the task
                                                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                                                finish();
                                                            }
                                                        }, 2000);

                                                    } else if (beart_code.getCode().equals("500")) {
                                                        beartToast("账号已冻结");
                                                    } else {
                                                        beartToast("账号或者密码错误");
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        });

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                T.showAnimSuccessToast(RegisterActivity.this, "验证码已发送");
                                beartToast("验证码已发送");

                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {

                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                    try {
                        JSONObject obj = new JSONObject(throwable.getMessage());
                        Log.e(TAG, "afterEvent: " + throwable.getMessage());
                        final String des = obj.optString("detail");
                        if (!TextUtils.isEmpty(des)) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    T.showAnimErrorToast(RegisterActivity.this, "验证码错误");
                                    beartToast("验证码错误");
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
