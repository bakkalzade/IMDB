
public class Documentary extends Film{
    private String releaseDate;

    public Documentary(String id, String title, String language, String directors, String length, String country, String performers, String releaseDate) {
        super(id, title, language, directors, length, country, performers);
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}
