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

public class AdapterDiscover extends ArrayAdapter<Movie> {
    private Context context;
    private ArrayList<Movie> movieArrayList;

    public AdapterDiscover(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.context= context;
        this.movieArrayList= new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView ==null){
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= layoutInflater.inflate(R.layout.custom_discover,null);
        }
        if(movieArrayList.size()>0){
         Movie music = this.movieArrayList.get(position);

            ImageView imgMusic =(ImageView) convertView.findViewById(R.id.img_game_ds);
            TextView txtTenNhac=(TextView) convertView.findViewById(R.id.txt_tenGame_ds);
            imgMusic.setImageResource(music.getImg());
            txtTenNhac.setText(music.getCategory());
        }
        return convertView;
    }
}
