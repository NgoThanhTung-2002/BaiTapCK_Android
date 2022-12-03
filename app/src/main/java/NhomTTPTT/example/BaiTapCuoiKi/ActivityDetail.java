package NhomTTPTT.example.BaiTapCuoiKi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.otpverification.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetail extends AppCompatActivity {
    TextView txtNameMovie,txtSumary;
    ImageButton imgmp4;
    VideoView vdView;

    private GridView gridView;
    private AdapterDiscover adapterDiscover;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtNameMovie =(TextView) findViewById(R.id.txt_detailNameMovie);
        txtSumary =(TextView) findViewById(R.id.txt_Summary);
        imgmp4 =(ImageButton) findViewById(R.id.imgmp4);
        vdView = (VideoView) findViewById(R.id.vd_ViDeoMP4);

        Intent intent =getIntent();
        txtNameMovie.setText(intent.getStringExtra("key1"));
        txtSumary.setText(intent.getStringExtra("key2"));

        imgmp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgmp4.setVisibility(View.INVISIBLE);

                switch (txtNameMovie.getText().toString()) {
                    case "Chiến Tranh":
                    case "Kinh Dị":
                    case "Hài Kịch":
                    case "Hành Động":
                    case "Cheer Up":
                    case "Tình Cảm":
                    case "Viễn Tưởng":
                    case "Khoa Học":
                    case "Hoạt Hình" :{
                        vdView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+R.raw.chientranh));
                        break;
                    }
                }
                vdView.start();
                MediaController mediaController = new MediaController(ActivityDetail.this);
                mediaController.setMediaPlayer(vdView);
                vdView.setMediaController(mediaController);
            }
        });
        gridView = (GridView) findViewById(R.id.grViewDetail);
        movieList = new ArrayList<>();
        movieList.add(new Movie("",R.drawable.ct_kinhdi, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM KINH DỊ"));
        movieList.add(new Movie("",R.drawable.ct_vientuong, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM VIỄN TƯỞNG"));
        movieList.add(new Movie("",R.drawable.ct_hanhdong, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM HÀNH ĐỘNG"));
        movieList.add(new Movie("",R.drawable.ct_phieuluu, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM PHIÊU LƯU"));
        movieList.add(new Movie("",R.drawable.ct_khoahoc, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM KHOA HỌC VIỄN TƯỞNG"));
        movieList.add(new Movie("",R.drawable.ct_trinhtham, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM TRINH THÁM"));
        movieList.add(new Movie("",R.drawable.ct_tinhcam, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM TÌNHH CẢM"));
        movieList.add(new Movie("",R.drawable.ct_hai, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM HÀI"));
        movieList.add(new Movie("",R.drawable.ct_hoathinh, MovieSummary.CHIENTRANH,"123,4K","00:04:29","HOẠT HÌNH"));
        movieList.add(new Movie("",R.drawable.ct_canhac, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM CA NHẠC"));
        movieList.add(new Movie("",R.drawable.ct_anime, MovieSummary.CHIENTRANH,"123,4K","00:04:29","ANIME"));
        movieList.add(new Movie("",R.drawable.ct_chientranh, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM CHIẾN TRANH"));
        movieList.add(new Movie("",R.drawable.ct_toipham, MovieSummary.CHIENTRANH,"123,4K","00:04:29","PHIM TỘI PHẠM" ));
        adapterDiscover= new AdapterDiscover(ActivityDetail.this,0, movieList);
        gridView.setAdapter(adapterDiscover);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityDetail.this, ActivitycategoryDetail.class);
                intent.putExtra("key1", movieList.get(i).getCategory());
                intent.putExtra("key2", movieList.get(i).getImg());
                startActivity(intent);
            }
        });
    }
}