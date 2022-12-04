package NhomTTPTT.example.BaiTapCuoiKi;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otpverification.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivitySignUp extends AppCompatActivity {
    private EditText edtUserName,edtEmail,edtPassWord,edtConfirmPassword;
    private Button btnSignIn,btnSignUp;
    private ImageButton imageButtonMatPassword,imageButtonNhamMatPassword;
    private ImageButton imageButtonMatConfirmPassword,imageButtonNhamMatConfirmPassword;
    boolean status=false;
    public static boolean check =false;
    String user;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final ProgressBar progressBar = findViewById(R.id.progress);
        edtUserName= (EditText) findViewById(R.id.inPutMobile);
        edtEmail= (EditText) findViewById(R.id.edtEmail);
        edtPassWord= (EditText) findViewById(R.id.edtPasswordSignUP);
        edtConfirmPassword= (EditText) findViewById(R.id.edtConfirmPassword);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        imageButtonMatPassword=(ImageButton) findViewById(R.id.imgMatPassword);
        imageButtonNhamMatPassword=(ImageButton) findViewById(R.id.imgNhamMatPassword);
        imageButtonMatConfirmPassword=(ImageButton) findViewById(R.id.imgMatConfirmPassword);
        imageButtonNhamMatConfirmPassword=(ImageButton) findViewById(R.id.imgNhamMatConfirmPassword);
        imageButtonNhamMatPassword.setVisibility(View.INVISIBLE);
        imageButtonNhamMatConfirmPassword.setVisibility(View.INVISIBLE);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=false;
                if(edtUserName.getText().length()!=0&& edtEmail.getText().length()!=0
                        && edtPassWord.getText().length()!=0&&edtConfirmPassword.getText().length()!=0){
                    String regex ="^\\w+[a-z0-9]*@{1}\\w+mail.com$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(edtEmail.getText().toString());

                    String regexPhoneNumber="^(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                    Pattern patternPhoneNumber = Pattern.compile(regexPhoneNumber);
                    Matcher matcherPhoneNumber = patternPhoneNumber.matcher(edtUserName.getText().toString());

                    if(!matcher.find()){
                        Toast.makeText(ActivitySignUp.this, "Sai định dạng Mail", Toast.LENGTH_SHORT).show();

                    }
                    else if(!matcherPhoneNumber.find()){
                        Toast.makeText(ActivitySignUp.this, "Số điện thoại không hợp lệ !!", Toast.LENGTH_SHORT).show();
                    } else{
                        if(edtPassWord.getText().toString().equals(edtConfirmPassword.getText().toString())){
                            Cursor dataAccount = ActivitySignIn.database.GetData("SELECT * FROM TaiKhoan");
                            while (dataAccount.moveToNext()){
                                String phone =dataAccount.getString(4);
                                String password =dataAccount.getString(1);
                                if(phone.equals(edtUserName.getText().toString())){
                                    Toast.makeText(ActivitySignUp.this, "Số điện thoại đã được đăng kí", Toast.LENGTH_SHORT).show();
                                    status=true;
                                }
                            }
                            if(!dataAccount.moveToNext() && !status ){
                                progressBar.setVisibility(View.VISIBLE);
                                btnSignUp.setVisibility(View.INVISIBLE);
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        "+84"+edtUserName.getText().toString(),
                                        60,
                                        TimeUnit.SECONDS,
                                        ActivitySignUp.this,
                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                progressBar.setVisibility(View.GONE);
                                                btnSignUp.setVisibility(View.VISIBLE);

                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                                progressBar.setVisibility(View.GONE);
                                                btnSignUp.setVisibility(View.VISIBLE);
                                                Toast.makeText(ActivitySignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                progressBar.setVisibility(View.GONE);
                                                btnSignUp.setVisibility(View.VISIBLE);
                                                Intent intent = new Intent(getApplicationContext(),VerityOTPActivity.class);
                                                intent.putExtra("mobile",edtUserName.getText().toString());
                                                intent.putExtra("email",edtEmail.getText().toString());
                                                intent.putExtra("verificationId",verificationId);
                                                intent.putExtra("password",edtPassWord.getText().toString());
                                                startActivity(intent);
                                            }
                                        }
                                );
                            }

                        }
                        else{
                            Toast.makeText(ActivitySignUp.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else
                {
                    Toast.makeText(ActivitySignUp.this, "Bạn cần nhập đầy đủ thông tin!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check=true;
                Intent intent = new Intent(ActivitySignUp.this, ActivitySignIn.class);
                startActivity(intent);
            }
        });
        imageButtonMatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageButtonMatPassword.setVisibility(View.INVISIBLE);
                imageButtonNhamMatPassword.setVisibility(View.VISIBLE);
            }
        });
        imageButtonNhamMatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageButtonMatPassword.setVisibility(View.VISIBLE);
                imageButtonNhamMatPassword.setVisibility(View.INVISIBLE);
            }
        });
        imageButtonMatConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageButtonMatConfirmPassword.setVisibility(View.INVISIBLE);
                imageButtonNhamMatConfirmPassword.setVisibility(View.VISIBLE);
            }
        });
        imageButtonNhamMatConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageButtonMatConfirmPassword.setVisibility(View.VISIBLE);
                imageButtonNhamMatConfirmPassword.setVisibility(View.INVISIBLE);
            }
        });

    }
}