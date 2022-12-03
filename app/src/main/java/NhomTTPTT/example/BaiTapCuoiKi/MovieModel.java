package NhomTTPTT.example.BaiTapCuoiKi;
public class MovieModel {
    String dienvien,namemovie,time,url;

    public MovieModel(String dienvien, String namemovie, String time, String url) {
        this.dienvien = dienvien;
        this.namemovie = namemovie;
        this.time = time;
        this.url = url;
    }

    public MovieModel() {
    }

    public String getDienvien() {
        return dienvien;
    }

    public void setDienvien(String dienvien) {
        this.dienvien = dienvien;
    }

    public String getNamemovie() {
        return namemovie;
    }

    public void setNamemovie(String namemovie) {
        this.namemovie = namemovie;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

