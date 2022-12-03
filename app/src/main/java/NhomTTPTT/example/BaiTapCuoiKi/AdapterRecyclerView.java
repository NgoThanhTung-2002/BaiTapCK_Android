package NhomTTPTT.example.BaiTapCuoiKi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otpverification.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MovieViewHoder> implements Filterable {
    private List<Movie> mList;
    private List<Movie> mListOld;

    public AdapterRecyclerView(List<Movie> mList) {
        this.mList = mList;
        this.mListOld=mList;
    }

    @NonNull
    @Override
    public MovieViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_line_movie,parent,false);
        return new MovieViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHoder holder, int position) {
        Movie movie =mList.get(position);
        if(movie==null){
            return;
        }
        holder.imageMovie.setImageResource(movie.getImg());
        holder.txtNameMovie.setText(movie.getMovieName());
        holder.txtNameCast.setText(movie.getActorName());
        holder.txtLike.setText(movie.getLikes());
        holder.txtTime.setText(movie.getTime());
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class MovieViewHoder extends RecyclerView.ViewHolder{
       ImageView imageMovie;
       TextView txtNameMovie;
       TextView txtNameCast;
       TextView txtLike;
       TextView txtTime;

        public MovieViewHoder(@NonNull View itemView) {
            super(itemView);
            imageMovie = itemView.findViewById(R.id.imgMovie);
            txtNameMovie = itemView.findViewById(R.id.txt_nameMovie);
            txtNameCast = itemView.findViewById(R.id.txt_name_cast);
            txtLike = itemView.findViewById(R.id.txt_like);
            txtTime = itemView.findViewById(R.id.txt_time);


        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSeachr=  charSequence.toString();
                if(strSeachr.isEmpty()){
                    mList=mListOld;
                }else {
                    List<Movie> list = new ArrayList<>();
                    for(Movie movie: mListOld){
                        if(movie.getMovieName().toLowerCase().contains(strSeachr.toLowerCase(Locale.ROOT))){
                            list.add(movie);
                        }
                    }
                    mList=list;
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values=mList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mList = (List<Movie>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }
}
