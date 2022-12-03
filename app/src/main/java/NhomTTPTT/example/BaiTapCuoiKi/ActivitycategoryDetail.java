package NhomTTPTT.example.BaiTapCuoiKi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.otpverification.R;

import java.util.ArrayList;

public class ActivitycategoryDetail extends AppCompatActivity {
    ListView listViewMovie;
    ArrayList<Movie> movieArrayList ;
    AdapterMovie adapterMovie;
    ImageView img;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitycategory_detail);

        listViewMovie = (ListView) findViewById(R.id.lvcategory_detail);
        movieArrayList = new ArrayList<>();
        movieArrayList.add(new Movie("Chiến Tranh",R.drawable.ct_chientranh, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        movieArrayList.add(new Movie("Hoạt Hình",R.drawable.ct_hoathinh, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        movieArrayList.add(new Movie("Khoa Học",R.drawable.ct_khoahoc, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        movieArrayList.add(new Movie("Kinh Dị",R.drawable.ct_kinhdi, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        movieArrayList.add(new Movie("Tình Cảm",R.drawable.ct_tinhcam, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        movieArrayList.add(new Movie("Viễn Tưởng",R.drawable.ct_vientuong, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        movieArrayList.add(new Movie("Hài Kịch",R.drawable.ct_hai, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        movieArrayList.add(new Movie("Hành Động",R.drawable.ct_hanhdong, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        movieArrayList.add(new Movie("Cheer Up",R.drawable.cherup, MovieSummary.CHIENTRANH,"123,4K","00:04:29","","KT- VO KIM THANH"));
        adapterMovie = new AdapterMovie(ActivitycategoryDetail.this, R.layout.custom_line_movie,movieArrayList);
        listViewMovie.setAdapter(adapterMovie);
        listViewMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivitycategoryDetail.this, ActivityDetail.class);
                intent.putExtra("key1", movieArrayList.get(i).getMovieName());
                intent.putExtra("key2", movieArrayList.get(i).getMovieSummary());
                startActivity(intent);
            }
        });
        img = (ImageView) findViewById(R.id.imgcategory_detail);
        txt= (TextView) findViewById(R.id.txtcategory_detail);
        Intent intent =getIntent();
        txt.setText(intent.getStringExtra("key1"));
        img.setImageResource(intent.getIntExtra("key2",0));
    }
}