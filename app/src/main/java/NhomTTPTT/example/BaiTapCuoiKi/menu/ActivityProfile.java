package NhomTTPTT.example.BaiTapCuoiKi.menu;

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

import NhomTTPTT.example.BaiTapCuoiKi.Account;
import NhomTTPTT.example.BaiTapCuoiKi.ActivityContaint;
import NhomTTPTT.example.BaiTapCuoiKi.ActivitySignIn;

import com.example.otpverification.R;

public class ActivityProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView imgBack = (ImageView) findViewById(R.id.imgBack);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdateProfile);

        EditText edtfullName = (EditText) findViewById(R.id.edtFullName);
        EditText edtuserName = (EditText) findViewById(R.id.edtUserNameProfile);
        EditText edtemail = (EditText) findViewById(R.id.edtEmail);
        EditText edtphone = (EditText) findViewById(R.id.edtPhone);
        TextView edtname = (TextView) findViewById(R.id.FullName);
        edtphone.setEnabled(false);
        Intent intent = getIntent();
        String phoneAX = intent.getStringExtra("phone");


        Cursor dataAccount = ActivitySignIn.database.GetData("SELECT * FROM TaiKhoan");
        while (dataAccount.moveToNext()) {
            String userName = dataAccount.getString(0);
            String fullname = dataAccount.getString(2);
            String email = dataAccount.getString(3);
            String phone = dataAccount.getString(4);

            if (phone.equals(phoneAX)) {
                edtuserName.setText(userName);
                edtfullName.setText(fullname);
                edtname.setText(fullname);
                edtemail.setText(email);
                edtphone.setText(phone);
                break;
            }
    }
            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  finish();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivitySignIn.database.updateProfile(new Account(edtuserName.getText().toString() , edtfullName.getText().toString(), edtemail.getText().toString(), edtphone.getText().toString()));
                    edtname.setText(edtfullName.getText().toString());
                    Toast.makeText(ActivityProfile.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
