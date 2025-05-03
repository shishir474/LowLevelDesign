package BookMyShow;

import java.util.ArrayList;
import java.util.List;

// id, name, location, list of movies
public class Theatre {
    private final String theatreId;
    private final String theatreName;
    private final String location;
    private List<Movie> movieList;

    public Theatre(String theatreId, String theatreName, String location){
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.location = location;
        movieList = new ArrayList<>();
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getLocation() {
        return location;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public String getTheatreId() {
        return theatreId;
    }
}
