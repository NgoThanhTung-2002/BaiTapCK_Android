package NhomTTPTT.example.BaiTapCuoiKi;

import java.util.List;

public class Category {
    private String movieXuHuong;
    private List<Movie> movieList;

    public Category(String movieXuHuong, List<Movie> movieList) {
        this.movieXuHuong = movieXuHuong;
        this.movieList = movieList;
    }

    public Category() {
    }

    public String getGameXuHuong() {
        return movieXuHuong;
    }

    public void setGameXuHuong(String gameXuHuong) {
        this.movieXuHuong = gameXuHuong;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setGameList(List<Movie> musicList) {
        this.movieList = musicList;
    }
}

