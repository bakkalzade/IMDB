
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class Helper {
    public static String[] StringArrayExtend(String[] oldArray, String strToAdd){
        int length=oldArray.length;
        String[] newArray= new String[length+1];
        System.arraycopy(oldArray, 0, newArray, 0, length);
        newArray[length]=strToAdd;
        return newArray;

    }
    public static Film[] FilmArrayExtend(Film[] oldArray, Film filmToAdd){
        int length=oldArray.length;
        Film[] newArray= new Film[length+1];
        System.arraycopy(oldArray, 0, newArray, 0, length);
        newArray[length]=filmToAdd;
        return newArray;
    }
    public static FeatureFilm[] FeatureFilmArrayExtend(FeatureFilm[] oldArray, FeatureFilm filmToAdd){
        int length=oldArray.length;
        FeatureFilm[] newArray= new FeatureFilm[length+1];
        System.arraycopy(oldArray, 0, newArray, 0, length);
        newArray[length]=filmToAdd;
        return newArray;
    }
    public static Film[] FilmArrayLastIndexRemover(Film[] oldArray){
        int length=oldArray.length;
        Film[] newArray= new Film[length-1];
        System.arraycopy(oldArray, 0, newArray, 0, length-1);
        return newArray;
    }
    public static String[] StringArrayLastIndexRemover(String[] oldArray){
        int length=oldArray.length;
        String[] newArray= new String[length-1];
        System.arraycopy(oldArray, 0, newArray, 0, length-1);
        return newArray;
    }
    public static ShortFilm[] ShortFilmArrayLastIndexRemover(ShortFilm[] oldArray){
        int length=oldArray.length;
        ShortFilm[] newArray= new ShortFilm[length-1];
        System.arraycopy(oldArray, 0, newArray, 0, length-1);
        return newArray;
    }
    public static boolean StringInItChecker(String[] arrayToCheck, String elements){
        int counter= 0;
        String[] elementArray= elements.split(",");
        for (int i = 0; i < elementArray.length; i++) {
            if (Arrays.asList(arrayToCheck).contains(elementArray[i])) {
                counter++;
            }
        }
        return counter == elements.split(",").length;

        /*int counter = 0;
        for (String k : elements.split(",")){
            System.out.println("----");
            System.out.println(k);
            for (String s : arrayToCheck) {
                System.out.println(s);
                if(k.equals(s)){
                    System.out.println(k+" is equal to "+ s);
                    counter++;
                    break;
                }
            }
        }
        return counter == elements.split(",").length;*/
    }
    public static void elementAdderToBlank(String[] array, String element){
        for (int i = 0; i < array.length; i++) {
            if (array[i]==null){
                array[i]=element;
                break;
            }
        }
    }
    public static Person personFromID(PeopleReader peopleReader, String personID){
        String[] allPersonID = peopleReader.getAllIdPeople();
        Person[] allPerson= peopleReader.getAllPerson();
        Person person;
        for (int i = 0; i < allPerson.length; i++) {
            if (allPersonID[i].equals(personID)){
                person=allPerson[i];
                return person;
            }
        }
        return null;
    }
    public static String PersonNameStr(PeopleReader peopleReader,String writers){
        String[] writersArray=writers.split(",");
        String writersNameStr="";


        for (int i = 0; i < writersArray.length; i++){
            String writerId=writersArray[i];
            Person writer = personFromID(peopleReader,writerId);
            if (i< writersArray.length-1){
                writersNameStr += writer.getName()+ " "+ writer.getSurname() +", ";
            }else{
                writersNameStr += writer.getName()+ " "+ writer.getSurname();
            }
        }

        return writersNameStr;
    }
    public static String RatingCalculator(Film film){
        String returnStr;
        String[] raters=film.raters;
        int countOfUsers=raters.length;
        if(countOfUsers==0){
            return "Awaiting for votes";
        }
        double sum=0;
        for (int i = 0; i < raters.length; i++) {
                sum+= Double.parseDouble(raters[i].split(" ")[1]);
        }

        double average=sum/countOfUsers;
        int intAverage= (int) average;
        if (average ==  intAverage){
            returnStr="Ratings: "+intAverage+"/10 from "+countOfUsers+" users" ;
        }else {
            DecimalFormat df = new DecimalFormat("#.#");
            String strAverage= df.format(average).replace(".",",");
            returnStr="Ratings: "+strAverage+"/10 from "+countOfUsers+" users" ;
        }
        return returnStr;
    }
    public static String RatingCalculator(Film film,String just){
        String returnStr= Helper.RatingCalculator(film);
        if (returnStr.equals("Awaiting for votes")){
            return "Ratings: 0/10 from 0 users";
        }
        return returnStr;
    }
    public static String JustRatingStr(Film film){
        String strAverage="";
        String[] raters=film.raters;
        int countOfUsers=raters.length;
        if(countOfUsers==0){
            return "0";
        }
        double sum=0;
        for (int i = 0; i < raters.length; i++) {
            sum+= Double.parseDouble(raters[i].split(" ")[1]);
        }
        double average=sum/countOfUsers;
        int intAverage= (int) average;
        if (average ==  intAverage){
            strAverage=String.valueOf(intAverage);
        }else {
            DecimalFormat df = new DecimalFormat("#.#");
            strAverage= df.format(average).replace(".",",");
        }
        return strAverage;

    }
    public static String[] elementRemover(String[] array, String element){
        String[] newArray= new String[0];
        if (array.length==1){
            return newArray;
        }
        newArray= new String[array.length-1];
        for (int i = 0,k=0; i < array.length; i++) {
            if (array[i].contains(element)){
                continue;
            }
            newArray[k++]=array[i];
        }
        return newArray;
    }
    public static String tvSeriesToStr(TvSeries tvSeries){

        String startDate = tvSeries.getStartDate().split("\\.")[2];
        String endDate = tvSeries.getEndDate().split("\\.")[2];
        String title = tvSeries.getTitle();
        String seasons = tvSeries.getSeasons();
        String episodes = tvSeries.getEpisodes();


        return title+" ("+startDate+"-"+endDate+")\n"+
                seasons+" seasons and "+episodes+" episodes\n";

    }
    public static String filmInfo(Film film){
        String title= "Film title: "+ film.getTitle()+"\n";
        String length= film.getLength()+" min"+"\n";
        String language = "Language: "+ film.getLanguage()+"\n\n";

        return  title+
                length+
                language;
    }
    public static String filmInfo(FeatureFilm film){
        String title= "Film title: "+ film.getTitle()+ " ("+((FeatureFilm)film).getReleaseDate().split("\\.")[2]+")"+"\n";
        String length= film.getLength()+" min"+"\n";
        String language = "Language: "+ film.getLanguage()+"\n\n";

        return  title+
                length+
                language;

    }
    public static String filmOrder(FeatureFilm[] allFeatureFilm){
        String featureFilmOrderStr="";
        String[] ratings=new String[allFeatureFilm.length];
        String[] realRatings= new String[allFeatureFilm.length];
        FeatureFilm[] orderedFeatureFilm= new FeatureFilm[allFeatureFilm.length];
        for (int i=0; i< allFeatureFilm.length; i++){
            ratings[i]=Helper.JustRatingStr(allFeatureFilm[i]);
            realRatings[i]=Helper.JustRatingStr(allFeatureFilm[i]);
        }
        Arrays.sort(ratings, Collections.reverseOrder());
        for (int i = 0; i <ratings.length ; i++) {
            for (int j = 0; j < realRatings.length; j++) {
                if (ratings[i].equals(realRatings[j])){
                    if (!Arrays.asList(orderedFeatureFilm).contains(allFeatureFilm[j])){
                        orderedFeatureFilm[i]=allFeatureFilm[j];
                        break;
                    }
                }
            }
        }
        for (FeatureFilm film: orderedFeatureFilm) {
            String rating= Helper.RatingCalculator(film,"just");
            String releaseDate=" ("+film.getReleaseDate().split("\\.")[2]+") ";
            String title =film.getTitle();
            featureFilmOrderStr+=title+ releaseDate+rating+"\n";
        }
        return featureFilmOrderStr;
    }
    public static String filmOrder(ShortFilm[] allShorFilm){
        String featureFilmOrderStr="";
        String[] ratings=new String[allShorFilm.length];
        String[] realRatings= new String[allShorFilm.length];
        ShortFilm[] orderedFeatureFilm= new ShortFilm[allShorFilm.length];
        for (int i=0; i< allShorFilm.length; i++){
            ratings[i]=Helper.JustRatingStr(allShorFilm[i]);
            realRatings[i]=Helper.JustRatingStr(allShorFilm[i]);
        }
        Arrays.sort(ratings, Collections.reverseOrder());
        for (int i = 0; i <ratings.length ; i++) {
            for (int j = 0; j < realRatings.length; j++) {
                if (ratings[i].equals(realRatings[j])){
                    if (!Arrays.asList(orderedFeatureFilm).contains(allShorFilm[j])){
                        orderedFeatureFilm[i]=allShorFilm[j];
                        break;
                    }
                }
            }
        }
        for (ShortFilm film: orderedFeatureFilm) {
            String rating= Helper.RatingCalculator(film,"just");
            String releaseDate=" ("+film.getReleaseDate().split("\\.")[2]+") ";
            String title =film.getTitle();
            featureFilmOrderStr+=title+ releaseDate+rating+"\n";
        }
        return featureFilmOrderStr;
    }
    public static String filmOrder(Documentary[] allDocumentary){
        String featureFilmOrderStr="";
        String[] ratings=new String[allDocumentary.length];
        String[] realRatings= new String[allDocumentary.length];
        Documentary[] orderedFeatureFilm= new Documentary[allDocumentary.length];
        for (int i=0; i< allDocumentary.length; i++){
            ratings[i]=Helper.JustRatingStr(allDocumentary[i]);
            realRatings[i]=Helper.JustRatingStr(allDocumentary[i]);
        }
        Arrays.sort(ratings, Collections.reverseOrder());
        for (int i = 0; i <ratings.length ; i++) {
            for (int j = 0; j < realRatings.length; j++) {
                if (ratings[i].equals(realRatings[j])){
                    if (!Arrays.asList(orderedFeatureFilm).contains(allDocumentary[j])){
                        orderedFeatureFilm[i]=allDocumentary[j];
                        break;
                    }
                }
            }
        }
        for (Documentary film: orderedFeatureFilm) {
            String rating= Helper.RatingCalculator(film,"just");
            String releaseDate=" ("+film.getReleaseDate().split("\\.")[2]+") ";
            String title =film.getTitle();
            featureFilmOrderStr+=title+ releaseDate+rating+"\n";
        }
        return featureFilmOrderStr;
    }
    public static String filmOrder(TvSeries[] alltvSeries){
        String featureFilmOrderStr="";
        String[] ratings=new String[alltvSeries.length];
        String[] realRatings= new String[alltvSeries.length];
        TvSeries[] orderedFeatureFilm= new TvSeries[alltvSeries.length];
        for (int i=0; i< alltvSeries.length; i++){
            ratings[i]=Helper.JustRatingStr(alltvSeries[i]);
            realRatings[i]=Helper.JustRatingStr(alltvSeries[i]);
        }
        Arrays.sort(ratings, Collections.reverseOrder());
        for (int i = 0; i <ratings.length ; i++) {
            for (int j = 0; j < realRatings.length; j++) {
                if (ratings[i].equals(realRatings[j])){
                    if (!Arrays.asList(orderedFeatureFilm).contains(alltvSeries[j])){
                        orderedFeatureFilm[i]=alltvSeries[j];
                        break;
                    }
                }
            }
        }
        for (TvSeries film: orderedFeatureFilm) {
            String rating= Helper.RatingCalculator(film,"just");
            String startDate=film.getStartDate().split("\\.")[2];
            String endDate=film.getEndDate().split("\\.")[2];
            String releaseDate=" ("+startDate+"-"+endDate+") ";
            String title =film.getTitle();
            featureFilmOrderStr+=title+ releaseDate+rating+"\n";
        }
        return featureFilmOrderStr;

    }
}
