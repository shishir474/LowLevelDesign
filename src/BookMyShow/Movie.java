package BookMyShow;

//id, title, duration, descriptoin
public class Movie {
    private final String movieId;
    private final String title;
    private final int duration;
    private final String description;

    public Movie(String movieId, String title, String description, int duration){
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public String getMovieId(){
        return movieId;
    }

    public String getTitle(){
        return title;
    }

    public int getDuration(){
        return duration;
    }

    public String getDescription(){
        return description;
    }




}
