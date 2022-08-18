
public class ShortFilm extends Film{
    private String genre;
    private String releaseDate;
    private String writers;

    public ShortFilm(String id, String title, String language, String directors, String length, String country, String performers, String genre, String releaseDate, String writers) {
        super(id, title, language, directors, length, country, performers);
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.writers = writers;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getWriters() {
        return writers;
    }

}

