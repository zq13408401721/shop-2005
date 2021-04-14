package com.sprout.ui.login;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sprout.R;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.login.ILogin;
import com.sprout.mode.data.LoginBean;
import com.sprout.presenter.login.LoginPresenter;
import com.sprout.utils.SpUtils;

import org.w3c.dom.Text;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View {

    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.txt_login)
    TextView txtLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    @Override
    protected ILogin.Presenter createPersenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {

    }

    /**
     * 登录
     */
    private void login(){
        String username = inputUsername.getText().toString();
        String pw = inputPassword.getText().toString();
        if(username == null || username.length() == 0){
            showToast("请输入正确的用户名",Toast.LENGTH_SHORT);
            return;
        }
        if(pw == null || pw.length() < 6){
            showToast("请输入正确的密码",Toast.LENGTH_SHORT);
            return;
        }

        presenter.login(username,pw);
    }

    @Override
    public void loginReturn(LoginBean result) {
        if(result.getData() != null){
            SpUtils.getInstance().setValue("token",result.getData().getToken());
            SpUtils.getInstance().setValue("uid",result.getData().getUserInfo().getUid());
            SpUtils.getInstance().setValue("username",result.getData().getUserInfo().getUsername());
            finish();
        }
    }
}
