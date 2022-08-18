
public class TvSeries extends Film{
    private String genre;
    private String writers;
    private String startDate;
    private String endDate;
    private String seasons;
    private String episodes;

    public TvSeries(String id, String title, String language, String directors, String length, String country, String performers, String genre, String writers, String startDate, String endDate, String seasons, String episodes) {
        super(id, title, language, directors, length, country, performers);
        this.genre = genre;
        this.writers = writers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public String getGenre() {
        return genre;
    }

    public String getWriters() {
        return writers;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSeasons() {
        return seasons;
    }

    public String getEpisodes() {
        return episodes;
    }


}
