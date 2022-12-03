package NhomTTPTT.example.BaiTapCuoiKi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.otpverification.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategory extends ArrayAdapter<Movie> {
    private Context context;
    private ArrayList<Movie> movieArrayList;

    public AdapterCategory(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.context= context;
        this.movieArrayList= new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView ==null){
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= layoutInflater.inflate(R.layout.custom_line_grilview,null);
        }
        if(movieArrayList.size()>0){
           Movie movie = this.movieArrayList.get(position);

            ImageView imgMovie =(ImageView) convertView.findViewById(R.id.img_movieCustom);
            TextView txtNameMovie=(TextView) convertView.findViewById(R.id.txt_tenMovie);
            imgMovie.setImageResource(movie.getImg());
            txtNameMovie.setText(movie.getCategory());
        }
        return convertView;
    }
}

