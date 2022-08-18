
public class FeatureFilm extends Film{
    private String genre;
    private String releaseDate;
    private String writers;
    private String budget;

    public FeatureFilm(String id, String title, String language, String directors, String length, String country, String performers, String genre, String releaseDate, String writers, String budget) {
        super(id, title, language, directors, length, country, performers);
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.writers = writers;
        this.budget = budget;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getWriters() {
        return writers;
    }

}
