package NhomTTPTT.example.BaiTapCuoiKi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.otpverification.R;

public class ActivityForgotPassword extends AppCompatActivity {
    EditText edtForgotUserName,edtNhapMa;
    Button btnForgotXacNhan,btnBack;
    TextView txtPassword,txtRanDomMa;
    ImageView imgRefresh;
    RandomStringExmple rd = new RandomStringExmple();
    boolean status=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        btnBack= (Button) findViewById(R.id.btnBack);
        edtForgotUserName =(EditText) findViewById(R.id.edtFogottaiKhoan);
        btnForgotXacNhan=(Button) findViewById(R.id.btnXacNhanFogot);
        txtPassword=(TextView)findViewById(R.id.txtMatKhauDaLay);
        txtRanDomMa=(TextView) findViewById(R.id.txtRanDomMa) ;
        imgRefresh =(ImageView) findViewById(R.id.imgRefresh);
        edtNhapMa=(EditText) findViewById(R.id.edtNhapMa);
        txtRanDomMa.setText(rd.RanDomString());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityForgotPassword.this, ActivitySignIn.class);
                startActivity(intent);
            }
        });
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRanDomMa.setText(rd.RanDomString());
            }
        });
        btnForgotXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=false;
                if (edtForgotUserName.getText().length() != 0  && edtNhapMa.getText().length() !=0 ) {
                    Cursor dataAccount = ActivitySignIn.database.GetData("SELECT * FROM TaiKhoan");
                    while (dataAccount.moveToNext()){
                        String phone =dataAccount.getString(4);
                        String password =dataAccount.getString(1);
                        if (edtForgotUserName.getText().toString().equals(phone)) {
                            status=true;
                            if(edtNhapMa.getText().toString().equals(txtRanDomMa.getText().toString())){
                                txtPassword.setText("Mật khẩu: "+password);
                                break;
                            }else{
                                Toast.makeText(ActivityForgotPassword.this, "Mã xác nhận không đúng", Toast.LENGTH_SHORT).show();
                                edtNhapMa.setText("");
                                txtRanDomMa.setText(rd.RanDomString());
                            }break;
                        }
                    }
                    if(!dataAccount.moveToNext() && !status ){
                        Toast.makeText(ActivityForgotPassword.this, "Thông tin tài khoản không chính xác", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityForgotPassword.this, "Mời bạn nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}