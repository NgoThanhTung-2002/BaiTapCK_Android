package NhomTTPTT.example.BaiTapCuoiKi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otpverification.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ActivitySignIn extends AppCompatActivity {
    private EditText edtPassWord,edtUseName;
    private TextView editTextForgotPassword;
    private Button btnSignIn, btnSignIn_SignUp;
    private ImageView imageViewFbLogin,imageViewEmailLogin;
    private ImageButton imageButtonMat,imageButtonNhamMat;
    public static DatabaseHelper database;
    private CheckBox checkBox;
    SharedPreferences sharedPreferences;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        database.deleteAll();
        setContentView(R.layout.activity_sign_in);
        firestore=FirebaseFirestore.getInstance();
        Map<String ,Object> users =new HashMap<>();
        users.put("firstName","EASY");
        users.put("lastName","TUTO");
        users.put("description","Subscribe");
        firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_LONG).show();
            }
        });





        database= new DatabaseHelper(ActivitySignIn.this);
//        database.deleteAll();
        database.insertTaiKhoan(new Account("quangtrong","123","Thanhno1","vothanh6081@gmail.com","852146552"));
        edtUseName= (EditText) findViewById(R.id.edtUserName);
        edtPassWord= (EditText) findViewById(R.id.edtPassword);
        btnSignIn= (Button) findViewById(R.id.btnSignIn);
        btnSignIn_SignUp =(Button) findViewById(R.id.btnSignIn_SignUp);

        imageButtonMat=(ImageButton) findViewById(R.id.imgMat);
        imageButtonNhamMat=(ImageButton) findViewById(R.id.imgNhamMat);
        editTextForgotPassword=(TextView) findViewById(R.id.forgotPassword);
        imageButtonNhamMat.setVisibility(View.INVISIBLE);
        checkBox =(CheckBox) findViewById(R.id.checkbox);
        controlButton();
        return ;
    }
    boolean status=false;
    private void  controlButton() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUseName.getText().length() != 0 && edtPassWord.getText().length() != 0) {
                    Cursor dataAccount = database.GetData("SELECT * FROM TaiKhoan");
                    while (dataAccount.moveToNext()){
                        String phone =dataAccount.getString(4);
                        String password =dataAccount.getString(1);
                        if (edtUseName.getText().toString().equals(phone) && edtPassWord.getText().toString().equals(password) ) {
                            Toast.makeText(ActivitySignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActivitySignIn.this, ActivityContaint.class);
                            intent.putExtra("phone",phone );
                            startActivity(intent);
                            status=true;
                            if(checkBox.isChecked()){
                                SharedPreferences .Editor editor = sharedPreferences.edit();
                                editor.putString("taikhoan",phone);
                                editor.putString("matkhau",password);
                                editor.putBoolean("checked",true);
                                editor.commit();
                            }else{
                                SharedPreferences.Editor  editor = sharedPreferences.edit();
                                editor.remove("taikhoan");
                                editor.remove("matkhau");
                                editor.remove("checked");
                                editor.commit();
                            }
                            break;
                        }
                    }
                    if(!dataAccount.moveToNext()&&!status)  {
                        Toast.makeText(ActivitySignIn.this, "Sai thông tin tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivitySignIn.this, "Mời bạn nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSignIn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySignIn.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });
        imageButtonMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageButtonNhamMat.setVisibility(View.VISIBLE);
                imageButtonMat.setVisibility(View.INVISIBLE);
            }
        });
        imageButtonNhamMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageButtonNhamMat.setVisibility(View.INVISIBLE);
                imageButtonMat.setVisibility(View.VISIBLE);
            }
        });
        editTextForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySignIn.this, ActivityForgotPassword.class);
                startActivity(intent);
            }
        });
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        edtUseName.setText(sharedPreferences.getString("taikhoan",""));
        edtPassWord.setText(sharedPreferences.getString("matkhau",""));
        checkBox.setChecked(sharedPreferences.getBoolean("checked",false));
    }
}

