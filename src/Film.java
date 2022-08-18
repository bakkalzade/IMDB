
public class Film {
    private String id;
    private String title;
    private String language;
    private String directors;
    private String length;
    private String country;
    private String performers;
    public String[] raters;//{"userId rating"}

    public Film(String id, String title, String language, String directors, String length, String country, String performers) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.directors = directors;
        this.length = length;
        this.country = country;
        this.performers = performers;
        raters= new String[0];

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPerformers() {
        return performers;
    }

}
