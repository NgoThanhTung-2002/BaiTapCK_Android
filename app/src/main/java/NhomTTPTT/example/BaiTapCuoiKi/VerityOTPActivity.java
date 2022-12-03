package NhomTTPTT.example.BaiTapCuoiKi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otpverification.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerityOTPActivity extends AppCompatActivity {
    private EditText code1,code2,code3,code4,code5,code6;
    private String verificationId;
    private String phoneNumber;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verity_otpactivity);

        Intent intent =getIntent();
       phoneNumber=intent.getStringExtra("mobile");
       password=intent.getStringExtra("password");
       email=intent.getStringExtra("email");
        TextView txtMobile= findViewById(R.id.textMobile);
        txtMobile.setText(String.format(
                "+84-%s",getIntent().getStringExtra("mobile")
        ));

        code1 =findViewById(R.id.code1);
        code2 =findViewById(R.id.code2);
        code3 =findViewById(R.id.code3);
        code4 =findViewById(R.id.code4);
        code5 =findViewById(R.id.code5);
        code6 =findViewById(R.id.code6);
        setUpOTPInputs();
        code1.requestFocus();
        final ProgressBar process = findViewById(R.id.progressvery);
        final Button btnVery=findViewById(R.id.btn_verifi);

        verificationId=getIntent().getStringExtra("verificationId");

        btnVery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code1.getText().toString().trim().isEmpty() ||
                        code2.getText().toString().trim().isEmpty() ||
                        code3.getText().toString().trim().isEmpty() ||
                        code4.getText().toString().trim().isEmpty() ||
                        code5.getText().toString().trim().isEmpty() ||
                        code6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(VerityOTPActivity.this, "Vui long nhap otp", Toast.LENGTH_SHORT).show();
                    return;
                }

                String code = code1.getText().toString() +
                        code2.getText().toString() +
                        code3.getText().toString() +
                        code4.getText().toString() +
                        code5.getText().toString() +
                        code6.getText().toString();

                if (verificationId != null) {
                    process.setVisibility(View.VISIBLE);
                    btnVery.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    process.setVisibility(View.GONE);
                                    btnVery.setVisibility(View.VISIBLE);
                                    if(task.isSuccessful()){
                                ActivitySignIn.database.insertTaiKhoan(new Account("",password,"",email,phoneNumber));
                                Toast.makeText(VerityOTPActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(),ActivitySignIn.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(VerityOTPActivity.this, "Ma OTP khong dung", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        findViewById(R.id.textResendOtp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+84"+getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        VerityOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerityOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                             verificationId= newVerificationId;
                                Toast.makeText(VerityOTPActivity.this, "OTP send", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }
    private void setUpOTPInputs(){
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code5.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        code5.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    code6.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}