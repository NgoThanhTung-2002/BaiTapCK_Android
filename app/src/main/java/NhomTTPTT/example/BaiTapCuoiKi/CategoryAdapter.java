package NhomTTPTT.example.BaiTapCuoiKi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otpverification.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context mContext;
    private List<Category> mListcategory;

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Category> list){
        this.mListcategory=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);

        Category category =mListcategory.get(position);
        if(category==null){
            return;
        }

        holder.txt_movieXuHuong.setText(category.getGameXuHuong());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        holder.rcv_movie.setLayoutManager(linearLayoutManager);
        MovieAdapter nhacAdapter = new MovieAdapter();
        nhacAdapter.setData(category.getMovieList());
        holder.rcv_movie.setAdapter(nhacAdapter);
    }

    @Override
    public int getItemCount() {
        if(!mListcategory.isEmpty()){
            return mListcategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_movieXuHuong;
        private RecyclerView rcv_movie;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_movieXuHuong= itemView.findViewById(R.id.txt_xuHuong);
            rcv_movie =itemView.findViewById(R.id.rcv_movie);
        }
    }
}

