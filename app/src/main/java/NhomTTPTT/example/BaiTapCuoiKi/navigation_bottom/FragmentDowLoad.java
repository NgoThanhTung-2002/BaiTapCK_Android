package NhomTTPTT.example.BaiTapCuoiKi.navigation_bottom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.otpverification.R;

import NhomTTPTT.example.BaiTapCuoiKi.ActivityDetail;
import NhomTTPTT.example.BaiTapCuoiKi.ActivitycategoryDetail;
import NhomTTPTT.example.BaiTapCuoiKi.AdapterMovie;
import NhomTTPTT.example.BaiTapCuoiKi.repository.ListDowLoad;

public class FragmentDowLoad extends Fragment {
    ImageView img;
    TextView txt;
    ListView lv;
    AdapterMovie adapterMovie;
    int vitri =-1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_dowload, container, false);
        img = view.findViewById(R.id.imgEmpty);
        txt = view.findViewById(R.id.txt_empty);
        lv = view.findViewById(R.id.listviewDowLoad);
        if(ListDowLoad.movieArrayList.size()==0){
            lv.setVisibility(View.INVISIBLE);
        }else{
            lv.setVisibility(View.VISIBLE);
            txt.setVisibility(View.INVISIBLE);
            img.setVisibility(View.INVISIBLE);
        }

        adapterMovie = new AdapterMovie(getContext(), R.layout.custom_line_movie,ListDowLoad.movieArrayList);
        lv.setAdapter(adapterMovie);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ActivityDetail.class);
                intent.putExtra("key1", ListDowLoad.movieArrayList.get(i).getMovieName());
                intent.putExtra("key2", ListDowLoad.movieArrayList.get(i).getMovieSummary());
                startActivity(intent);
            }
        });

      lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
              AlertDialog.Builder  builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_DeviceDefault_Dialog);
              builder.setTitle("Bạn có chắc muốn xóa");
              builder.setMessage("Hãy lựa chọn bên dưới để xác nhận");
              builder.setIcon(android.R.drawable.ic_dialog_alert);
              builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int it) {
                      ListDowLoad.movieArrayList.remove(i);
                      adapterMovie.notifyDataSetChanged();
                      Toast.makeText(getContext(), "Bạn đã xóa thành công!!!", Toast.LENGTH_SHORT).show();
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

        return view;
    }
}