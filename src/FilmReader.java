
import java.io.BufferedReader;
import java.io.FileReader;

public class FilmReader {
    private Film[] allFilm;
    private String[] allIdFilm;
    private FeatureFilm[] allFeatureFilm;
    private ShortFilm[] allShortFilm;
    private Documentary[] allDocumentary;
    private TvSeries[] allTvSeries;

    public FilmReader(String fileName) {
        BufferedReader objReader = null;
        BufferedReader objReader2 = null;
        int counter = 0;
        int total;
        int featureFilmCount = 0;
        int shortFilmCount = 0;
        int documentaryCount = 0;
        int tvSeriesCount = 0;
        //find necessary length of arrays for each artists array by reading the file and finding how many times a film type is called.
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(fileName));
            while ((strCurrentLine = objReader.readLine()) != null) {
                String type = strCurrentLine.split(":")[0];
                switch (type) {
                    case "FeatureFilm":
                        featureFilmCount++;
                        break;
                    case "ShortFilm":
                        shortFilmCount++;
                        break;
                    case "Documentary":
                        documentaryCount++;
                        break;
                    case "TVSeries":
                        tvSeriesCount++;
                        break;

                }
            }
        } catch (Exception e) {
            System.out.println("oops something went wrong");
        }
        total = featureFilmCount + shortFilmCount + documentaryCount + tvSeriesCount;


        // setting the length of arrays as necessary length that is found above
        Film[] allFilm = new Film[total];
        String[] allIdFilm = new String[total];
        FeatureFilm[] allFeatureFilm = new FeatureFilm[featureFilmCount];
        ShortFilm[] allShortFilm = new ShortFilm[shortFilmCount];
        Documentary[] allDocumentary = new Documentary[documentaryCount];
        TvSeries[] allTvSeries = new TvSeries[tvSeriesCount];
        try {
            String strCurrentLine;
            objReader2 = new BufferedReader(new FileReader(fileName));
            while ((strCurrentLine = objReader2.readLine()) != null) {
                String type = strCurrentLine.split(":")[0];
                String[] strWithoutType = strCurrentLine.split(":")[1].trim().split("\t");
                String id = strWithoutType[0];
                Film film = null;
                int length = 40;
                switch (type) {
                    case "FeatureFilm":
                        FeatureFilm featureFilm = new FeatureFilm(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4], strWithoutType[5], strWithoutType[6], strWithoutType[7], strWithoutType[8], strWithoutType[9], strWithoutType[10]);
                        for (int i = 0; i < allFeatureFilm.length; i++) {
                            if (allFeatureFilm[i] == null) {
                                allFeatureFilm[i] = featureFilm;
                                break;
                            }
                        }
                        film = featureFilm;
                        break;

                    case "ShortFilm":
                        length = Integer.parseInt(strWithoutType[4]);
                        ShortFilm shortFilm = new ShortFilm(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4], strWithoutType[5], strWithoutType[6], strWithoutType[7], strWithoutType[8], strWithoutType[9]);
                        for (int i = 0; i < allShortFilm.length; i++) {
                            if (allShortFilm[i] == null) {
                                allShortFilm[i] = shortFilm;
                                break;
                            }
                        }
                        film = shortFilm;
                        break;

                    case "Documentary":
                        Documentary documentary = new Documentary(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4], strWithoutType[5], strWithoutType[6], strWithoutType[7]);
                        for (int i = 0; i < allDocumentary.length; i++) {
                            if (allDocumentary[i] == null) {
                                allDocumentary[i] = documentary;
                                break;
                            }
                        }
                        film = documentary;
                        break;

                    case "TVSeries":
                        TvSeries tvSeries = new TvSeries(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4], strWithoutType[5], strWithoutType[6], strWithoutType[7], strWithoutType[8], strWithoutType[9], strWithoutType[10], strWithoutType[11], strWithoutType[12]);
                        for (int i = 0; i < allTvSeries.length; i++) {
                            if (allTvSeries[i] == null) {
                                allTvSeries[i] = tvSeries;
                                break;
                            }
                        }
                        film = tvSeries;
                        break;
                }

                if (length <= 40) {
                    for (int i = 0; i < allFilm.length; i++) {
                        if (allFilm[i] == null) {
                            allFilm[i] = film;
                            allIdFilm[i] = id;
                            break;
                        }
                    }
                } else {
                    System.out.println("ShortFilm is longer than 40 minutes");
                    counter++;
                }
            }
        } catch (Exception e) {
            System.out.println("oops something went wrong");
        } finally {
            try {
                if ((objReader != null && objReader2 != null)) {
                    objReader.close();
                    objReader2.close();
                }
            } catch (Exception e) {
                System.out.println("oops something went wrong");
            }
        }
        for (int i = 0; i < counter; i++) {
            allFilm = Helper.FilmArrayLastIndexRemover(allFilm);
            allIdFilm = Helper.StringArrayLastIndexRemover(allIdFilm);
            allShortFilm = Helper.ShortFilmArrayLastIndexRemover(allShortFilm);
        }

        this.allFilm = allFilm;
        this.allIdFilm = allIdFilm;
        this.allFeatureFilm = allFeatureFilm;
        this.allShortFilm = allShortFilm;
        this.allDocumentary = allDocumentary;
        this.allTvSeries = allTvSeries;
    }

    public Film[] getAllFilm() {
        return allFilm;
    }

    public String[] getAllIdFilm() {
        return allIdFilm;
    }

    public FeatureFilm[] getAllFeatureFilm() {
        return allFeatureFilm;
    }

    public ShortFilm[] getAllShortFilm() {
        return allShortFilm;
    }

    public Documentary[] getAllDocumentary() {
        return allDocumentary;
    }

    public TvSeries[] getAllTvSeries() {
        return allTvSeries;
    }

    public void setAllFilm(Film[] allFilm) {
        this.allFilm = allFilm;
    }

    public void setAllIdFilm(String[] allIdFilm) {
        this.allIdFilm = allIdFilm;
    }

    public void setAllFeatureFilm(FeatureFilm[] allFeatureFilm) {
        this.allFeatureFilm = allFeatureFilm;
    }
}
