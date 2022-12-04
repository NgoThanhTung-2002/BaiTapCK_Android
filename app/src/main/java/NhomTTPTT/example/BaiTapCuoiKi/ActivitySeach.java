package NhomTTPTT.example.BaiTapCuoiKi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.otpverification.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ActivitySeach extends AppCompatActivity {

    RecyclerView rc ;
    MainAdapter mainAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);

        rc =(RecyclerView) findViewById(R.id.rv);
        searchView = (SearchView) findViewById(R.id.seachView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSeach(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSeach(s);
                return false;
            }
        });

        rc.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MovieModel> options =
                new FirebaseRecyclerOptions.Builder<MovieModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("movies"), MovieModel.class)
                        .build();

        mainAdapter = new MainAdapter(options,this);
        rc.setAdapter(mainAdapter);
    }
    private void txtSeach(String str){
        FirebaseRecyclerOptions<MovieModel> options =
                new FirebaseRecyclerOptions.Builder<MovieModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("movies").orderByChild("namemovie").startAt(str).endAt(str+"~"), MovieModel.class)
                        .build();

        mainAdapter= new MainAdapter(options,this);
        mainAdapter.startListening();
        rc.setAdapter(mainAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }



}