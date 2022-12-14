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
                                txtPassword.setText("M???t kh???u: "+password);
                                break;
                            }else{
                                Toast.makeText(ActivityForgotPassword.this, "M?? x??c nh???n kh??ng ????ng", Toast.LENGTH_SHORT).show();
                                edtNhapMa.setText("");
                                txtRanDomMa.setText(rd.RanDomString());
                            }break;
                        }
                    }
                    if(!dataAccount.moveToNext() && !status ){
                        Toast.makeText(ActivityForgotPassword.this, "Th??ng tin t??i kho???n kh??ng ch??nh x??c", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityForgotPassword.this, "M???i b???n nh???p ????? th??ng tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}