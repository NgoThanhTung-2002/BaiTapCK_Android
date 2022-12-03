package NhomTTPTT.example.BaiTapCuoiKi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otpverification.R;

import java.util.List;

public class AdapterMovie extends BaseAdapter {


    private Context context;
    private int layout;
    private List<Movie> musicList;

    public AdapterMovie(Context context, int layout, List<Movie> musicList) {
        this.context = context;
        this.layout = layout;
        this.musicList = musicList;
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view= layoutInflater.inflate(layout,null);
        Animation animation = AnimationUtils.loadAnimation(this.context, R.anim.hieu_ung);
        view.startAnimation(animation);
        ImageView imgView =(ImageView) view.findViewById(R.id.imgMovie);
        TextView txtLike= (TextView) view.findViewById(R.id.txt_like);
        TextView txtNameMovie= (TextView) view.findViewById(R.id.txt_nameMovie);
        TextView txtTime= (TextView) view.findViewById(R.id.txt_time);
        TextView txtName= (TextView) view.findViewById(R.id.txt_name_cast);

        Movie movie = musicList.get(i);
        imgView.setImageResource(movie.getImg());
        txtLike.setText(movie.getLikes());
        txtNameMovie.setText(movie.getMovieName());
        txtTime.setText(movie.getTime());
        txtName.setText(movie.getActorName());
        return view;
    }
}