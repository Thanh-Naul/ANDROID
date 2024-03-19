package com.example.app_giay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_giay.R;
import com.example.app_giay.retrofit.ApiBanHang;
import com.example.app_giay.retrofit.RetrofitClient;
import com.example.app_giay.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangKyActivity extends AppCompatActivity {

    EditText email, pass, repass, phone, username;
    ImageView back;
    AppCompatButton button;
    ApiBanHang apiBanHang;
    FirebaseAuth firebaseAuth;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        initView();
        initControl();
    }

    private void initControl() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangky();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void dangky() {
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_repass = repass.getText().toString().trim();
        String str_phone = phone.getText().toString().trim();
        String str_user = username.getText().toString().trim();
        if(TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_user)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập User name", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_pass)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Password", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_repass)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Repassword", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_phone)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Phone", Toast.LENGTH_SHORT).show();
        } else {
            if(str_pass.equals(str_repass)){
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(str_email, str_pass)
                        .addOnCompleteListener(DangKyActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if(user != null){
                                        postData(str_user, str_email, str_phone, str_pass, user.getUid());
                                    }
                                }else {
                                    Toast.makeText(getApplicationContext(), "Email đã tồn tại hoặc không thành công", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }else {
                Toast.makeText(getApplicationContext(), "Password chưa khớp", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void postData(String str_user, String str_email, String str_phone, String str_pass, String uid){
        compositeDisposable.add(apiBanHang.dangky(str_user, str_email, str_phone, "onfirebase", uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if(userModel.isSuccess()){
                                Utils.user_current.setEmail(str_email);
                                Utils.user_current.setPass("onfirebase");
                                Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                                startActivity(intent);
                                finish();

                            }else {
                                Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void initView() {
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        email  = findViewById(R.id.email);
        pass  = findViewById(R.id.pass);
        repass  = findViewById(R.id.repass);
        phone = findViewById(R.id.phone);
        username = findViewById(R.id.username);
        button  = findViewById(R.id.btndangky);
        back = findViewById(R.id.back);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}