package NhomTTPTT.example.BaiTapCuoiKi.menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otpverification.R;

import NhomTTPTT.example.BaiTapCuoiKi.ActivityDetail;
import NhomTTPTT.example.BaiTapCuoiKi.AdapterMovie;
import NhomTTPTT.example.BaiTapCuoiKi.repository.ListDowLoad;
import NhomTTPTT.example.BaiTapCuoiKi.repository.Listfavorite;

public class ActivityFavorite extends AppCompatActivity {
    TextView txt;
    ListView lv;
    AdapterMovie adapterMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        txt = (TextView) findViewById(R.id.txtViewFavorite);
        lv = (ListView) findViewById(R.id.lv_favorite);

        if(Listfavorite.movieArrayList.size()==0){
            lv.setVisibility(View.INVISIBLE);
        }else{
            lv.setVisibility(View.VISIBLE);
            txt.setVisibility(View.INVISIBLE);
        }

        adapterMovie = new AdapterMovie(this, R.layout.custom_line_movie,Listfavorite.movieArrayList);
        lv.setAdapter(adapterMovie);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityFavorite.this, ActivityDetail.class);
                intent.putExtra("key1", Listfavorite.movieArrayList.get(i).getMovieName());
                intent.putExtra("key2", Listfavorite.movieArrayList.get(i).getMovieSummary());
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(ActivityFavorite.this, android.R.style.Theme_DeviceDefault_Dialog);
                builder.setTitle("Bạn có chắc muốn xóa");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int it) {
                        Listfavorite.movieArrayList.remove(i);
                        adapterMovie.notifyDataSetChanged();
                        Toast.makeText(ActivityFavorite.this, "Bạn đã xóa thành công!!!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                return false;
            }
        });
    }
}