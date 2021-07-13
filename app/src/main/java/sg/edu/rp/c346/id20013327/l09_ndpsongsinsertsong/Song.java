package sg.edu.rp.c346.id20013327.l09_ndpsongsinsertsong;

import java.io.Serializable;

public class Song implements Serializable {

    private int id ;
    private String title ;
    private String singers;
    private int year;
    private int stars;

    public Song(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = 0;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSingers() {
        return singers;
    }
    public void setSingers(String singers) {
        this.singers = singers;
    }
    public int getYear() {
        return year;
    }
    public void setStars(int stars) {
        this.stars = stars;
    }
    public int getStars() {
        return stars;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", " + "Singer: " + singers + ", " + "Year: " + year;
    }

}
