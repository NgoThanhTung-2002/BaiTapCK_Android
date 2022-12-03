package NhomTTPTT.example.BaiTapCuoiKi.navigation_bottom;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import NhomTTPTT.example.BaiTapCuoiKi.Category;
import NhomTTPTT.example.BaiTapCuoiKi.CategoryAdapter;
import NhomTTPTT.example.BaiTapCuoiKi.MobieViewPagerAdapter;
import NhomTTPTT.example.BaiTapCuoiKi.Movie;
import NhomTTPTT.example.BaiTapCuoiKi.MovieSummary;
import com.example.otpverification.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    ArrayList<Movie> movieArrayList;
    private ViewPager viewPager;
    private CircleIndicator indicator;
    private Handler handler= new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(viewPager.getCurrentItem()== movieArrayList.size()-1){
                viewPager.setCurrentItem(0);
            }else{
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable,3000);
        super.onResume();
    }

    public List<Movie> getListMovie(){
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(R.drawable.cherup));
        list.add(new Movie(R.drawable.hopdongtinhyeu));
        list.add(new Movie(R.drawable.thiavang));
        list.add(new Movie(R.drawable.toiacvohinh));
        list.add(new Movie(R.drawable.bongdungtrungso));
        list.add(new Movie(R.drawable.bigmout));
        return list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_home, container, false);
        recyclerView =(RecyclerView) view.findViewById(R.id.RecyclerViewMovieHome);
        categoryAdapter= new CategoryAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        categoryAdapter.setData(getListcategory());
        recyclerView.setAdapter(categoryAdapter);


        viewPager =(ViewPager) view.findViewById(R.id.viewPager);
        indicator=(CircleIndicator) view.findViewById(R.id.circle) ;
        movieArrayList = (ArrayList<Movie>) getListMovie();
        MobieViewPagerAdapter adapter = new MobieViewPagerAdapter(movieArrayList);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        handler.postDelayed(runnable,3000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }
    private List<Category> getListcategory(){
        List<Category> listCategory = new ArrayList<>();
        List<Movie>   movieArrayList = new ArrayList<>();
        List<Movie>   movieArrayList1 = new ArrayList<>();
        List<Movie>   movieArrayList2 = new ArrayList<>();
        List<Movie>   movieArrayList3 = new ArrayList<>();

        movieArrayList.add(new Movie("CHERUP",R.drawable.cherup, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList.add(new Movie("HỢP ĐỒNG TÌNH YÊU",R.drawable.hopdongtinhyeu, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList.add(new Movie("THÌA VÀNG",R.drawable.thiavang, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList.add(new Movie("TỘI ÁC VÔ HÌNH",R.drawable.toiacvohinh, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList.add(new Movie("BIG MOUTH",R.drawable.bigmout, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList.add(new Movie("BỖNG DƯNG TRÚNG SỐ",R.drawable.bongdungtrungso, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList.add(new Movie("CHERUP",R.drawable.cherup, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList.add(new Movie("CHERUP",R.drawable.cherup, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList.add(new Movie("CHERUP",R.drawable.cherup, MovieSummary.CHIENTRANH,"new","00:04:29"));
        listCategory.add(new Category("ĐANG PHÁT SÓNG",movieArrayList));

        movieArrayList1.add(new Movie("CHERUP",R.drawable.ct_hanhdong, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList1.add(new Movie("HỢP ĐỒNG TÌNH YÊU",R.drawable.ct_chientranh, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList1.add(new Movie("THÌA VÀNG",R.drawable.ct_hai, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList1.add(new Movie("TỘI ÁC VÔ HÌNH",R.drawable.ct_hoathinh, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList1.add(new Movie("BIG MOUTH",R.drawable.ct_kinhdi, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList1.add(new Movie("BỖNG DƯNG TRÚNG SỐ",R.drawable.bongdungtrungso, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList1.add(new Movie("CHERUP",R.drawable.ct_khoahoc, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList1.add(new Movie("CHERUP",R.drawable.ct_vientuong, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList1.add(new Movie("CHERUP",R.drawable.cherup, MovieSummary.CHIENTRANH,"new","00:04:29"));
        listCategory.add(new Category("TOP 10 HÔM NAY",movieArrayList1));

        movieArrayList2.add(new Movie("BỖNG DƯNG TRÚNG SỐ",R.drawable.bongdungtrungso, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList2.add(new Movie("CHERUP",R.drawable.ct_khoahoc, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList2.add(new Movie("CHERUP",R.drawable.ct_vientuong, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList2.add(new Movie("CHERUP",R.drawable.cherup, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList2.add(new Movie("CHERUP",R.drawable.ct_hanhdong, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList2.add(new Movie("HỢP ĐỒNG TÌNH YÊU",R.drawable.ct_chientranh, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList2.add(new Movie("THÌA VÀNG",R.drawable.ct_hai, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList2.add(new Movie("TỘI ÁC VÔ HÌNH",R.drawable.ct_hoathinh, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList2.add(new Movie("BIG MOUTH",R.drawable.ct_kinhdi, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        listCategory.add(new Category("MỌI NGƯỜI ĐỀU XEM",movieArrayList2));

        movieArrayList3.add(new Movie("BỖNG DƯNG TRÚNG SỐ",R.drawable.bongdungtrungso, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList3.add(new Movie("CHERUP",R.drawable.ct_khoahoc, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList3.add(new Movie("TỘI ÁC VÔ HÌNH",R.drawable.ct_hoathinh, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList3.add(new Movie("BIG MOUTH",R.drawable.ct_kinhdi, MovieSummary.CHIENTRANH,"123,4K","00:04:29"));
        movieArrayList3.add(new Movie("CHERUP",R.drawable.ct_vientuong, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList3.add(new Movie("CHERUP",R.drawable.cherup, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList3.add(new Movie("CHERUP",R.drawable.ct_hanhdong, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList3.add(new Movie("HỢP ĐỒNG TÌNH YÊU",R.drawable.ct_chientranh, MovieSummary.CHIENTRANH,"new","00:04:29"));
        movieArrayList3.add(new Movie("THÌA VÀNG",R.drawable.ct_hai, MovieSummary.CHIENTRANH,"new","00:04:29"));
        listCategory.add(new Category("MỌI NGƯỜI ĐỀU TÌM KIẾM",movieArrayList3));
        listCategory.add(new Category("PHIM MỚI NHẤT",movieArrayList1));
        listCategory.add(new Category("SẮP LÊN SÓNG",movieArrayList2));
        listCategory.add(new Category("PHIM HÀN QUỐC",movieArrayList1));
        listCategory.add(new Category("TV SHOW- GIẢI TRÍ",movieArrayList3));
        listCategory.add(new Category("PHIM RAP HOT",movieArrayList2));
        return listCategory;
    }
}