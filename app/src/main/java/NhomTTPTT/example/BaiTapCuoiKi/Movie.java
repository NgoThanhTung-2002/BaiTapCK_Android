package NhomTTPTT.example.BaiTapCuoiKi;

public class Movie {
    private String movieName;
    private int img;
    private String movieSummary;
    private String likes;
    private String time;
    private String category;
    private  String actorName;


    public Movie(String movieName, int img, String movieSummary, String likes, String time, String category, String actorName) {
        this.movieName = movieName;
        this.img = img;
        this.movieSummary = movieSummary;
        this.likes = likes;
        this.time = time;
        this.category = category;
        this.actorName = actorName;
    }

    public Movie(int img) {
        this.img = img;
    }

    public Movie(String movieName, int img, String movieSummary, String likes, String time, String category) {
        this.movieName = movieName;
        this.img = img;
        this.movieSummary = movieSummary;
        this.likes = likes;
        this.time = time;
        this.category = category;
    }

    public Movie(String movieName, int img, String lyrics, String likes, String time) {
        this.movieName = movieName;
        this.img = img;
        this.movieSummary = lyrics;
        this.likes = likes;
        this.time = time;
    }

    public Movie() {
    }

    public String getMovieSummary() {
        return movieSummary;
    }

    public void setMovieSummary(String movieSummary) {
        this.movieSummary = movieSummary;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
