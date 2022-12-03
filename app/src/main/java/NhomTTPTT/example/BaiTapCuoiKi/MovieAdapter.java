package NhomTTPTT.example.BaiTapCuoiKi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otpverification.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MusicViewHolder> {
    private List<Movie> movieList;

    public void setData(List<Movie> list){
        this.movieList =list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
        Movie game = movieList.get(position);
        if(game==null){
            return;
        }else {
            holder.img_View.setImageResource(game.getImg());
            holder.txt_TenGame.setText(game.getMovieName());
            holder.txt_MoTa.setText(game.getLikes());
        }

    }

    @Override
    public int getItemCount() {
        if(!movieList.isEmpty()){
            return movieList.size();
        }
        return 0;
    }

    public  class MusicViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_View;
        private TextView txt_TenGame;
        private TextView txt_MoTa;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            img_View = itemView.findViewById(R.id.img_movie);
            txt_TenGame =itemView.findViewById(R.id.txt_tenPhim);
            txt_MoTa =itemView.findViewById(R.id.txt_moTa);
        }
    }

}
