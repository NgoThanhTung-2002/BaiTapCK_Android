package NhomTTPTT.example.BaiTapCuoiKi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.otpverification.R;

import java.util.List;


public class MobieViewPagerAdapter extends PagerAdapter {
    List<Movie> movieList;

    public MobieViewPagerAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView imgPhoTo =view.findViewById(R.id.img_photo);
        Movie music = movieList.get(position);
        imgPhoTo.setImageResource(music.getImg());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(movieList !=null){
            return  movieList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
