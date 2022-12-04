package NhomTTPTT.example.BaiTapCuoiKi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.otpverification.R;
public class LogOutLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out_loading);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent =new Intent(LogOutLoading.this,ActivitySignIn.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}