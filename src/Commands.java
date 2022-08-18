
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;

public class Commands {
    public static String rate(PeopleReader peopleReader, FilmReader filmReader, String userId, String idFilm, String rating) {
        String strToAdd = userId + " " + rating;
        String strToWrite = "RATE\t" + userId + "\t" + idFilm + "\t" + rating + "\n\n";
        int checker= 0;
        for (int i = 0; i < peopleReader.getAllIdPeople().length; i++) {

            //checking whether the given id is an id of a User or not
            if (peopleReader.getAllIdPeople()[i].equals(userId) && peopleReader.getAllPerson()[i] instanceof User) {
                checker=1;
                for (int s = 0; s < filmReader.getAllIdFilm().length; s++) {
                    if (filmReader.getAllIdFilm()[s].equals(idFilm)) {
                        String[] newRaters = new String[filmReader.getAllFilm()[s].raters.length + 1];

                        //checking whether the rater has rated this film or not
                        if (Arrays.asList(filmReader.getAllFilm()[s].raters).contains(strToAdd)) {
                            strToWrite +=
                                    "This film was earlier rated" +
                                            "\n\n----------------------------------------------------------------------------------\n";
                            return strToWrite;
                        }

                        //if every thing goes well, rating is done successfully...
                        //adding the new rater and his or her rating into "raters array" of the film
                        if (filmReader.getAllFilm()[s].raters.length > 0) {
                            for (int k = 0; k < filmReader.getAllFilm()[s].raters.length; k++) {
                                newRaters[k] = filmReader.getAllFilm()[s].raters[k];
                            }
                        }

                        newRaters[filmReader.getAllFilm()[s].raters.length] = strToAdd;
                        filmReader.getAllFilm()[s].raters = newRaters;
                        strToWrite +=
                                "Film rated successfully" +
                                        "\nFilm type: " + filmReader.getAllFilm()[s].getClass().getSimpleName() +
                                        "\nFilm title: " + filmReader.getAllFilm()[s].getTitle() +
                                        "\n\n----------------------------------------------------------------------------------\n";

                        //adding the rating and the name of the film into Users "rated movies array"
                        String[] newRatedMovies = new String[((User) peopleReader.getAllPerson()[i]).ratedMovies.length + 1];
                        if (((User) peopleReader.getAllPerson()[i]).ratedMovies.length > 0) {
                            for (int k = 0; k < ((User) peopleReader.getAllPerson()[i]).ratedMovies.length; k++) {
                                newRatedMovies[k] = ((User) peopleReader.getAllPerson()[i]).ratedMovies[k];
                            }
                        }
                        newRatedMovies[((User) peopleReader.getAllPerson()[i]).ratedMovies.length] = filmReader.getAllFilm()[s].getTitle() + " " + rating;
                        ((User) peopleReader.getAllPerson()[i]).ratedMovies = newRatedMovies;

                        ArrayList<String> newAllRatedMovies = peopleReader.allRatedMovies;
                        String str = userId + " " + idFilm + " " + rating;
                        newAllRatedMovies.add(str);
                        peopleReader.allRatedMovies = newAllRatedMovies;
                        return strToWrite;
                    }
                }
            }
        }
        if(checker==0) {
            strToWrite +=
                    "Command Failed" +
                            "\nUser ID: " + userId +
                            "\nFilm ID: " + idFilm +
                            "\n\n----------------------------------------------------------------------------------\n";
        }

        return strToWrite;
    }

    public static String addFilm(PeopleReader peopleReader, FilmReader filmReader, String filmType, String id, String title, String language, String directors, String length, String country, String performers, String genre, String releaseDate, String writers, String budget) {
        String outputString = "ADD\tFEATUREFILM\t" + id + "\t" + title + "\t" + language + "\t" + directors + "\t" + length + "\t" + country + "\t" + performers + "\t" + genre + "\t" + releaseDate + "\t" + writers + "\t" + budget + "\n\n";
        String[] filmId = filmReader.getAllIdFilm();

        // Are directors exist
        Director[] directorID = peopleReader.getAllDirector();
        String[] strDirectorID = new String[directorID.length];
        for (int i = 0; i < directorID.length; i++) {
            if (directorID[i] != null) {
                strDirectorID[i] = directorID[i].getId();
            }
        }
        // Are writers exist
        Writer[] writerID = peopleReader.getAllWriter();
        String[] strWriterID = new String[writerID.length];
        for (int i = 0; i < writerID.length; i++) {
            if (writerID[i] != null) {
                strWriterID[i] = writerID[i].getId();
            }
        }
        String[] strPerformerID = peopleReader.getAllIdPerformer();

        // checking film id, directors, writers and performers whether  they are appropriate or not.
        if (!Helper.StringInItChecker(filmId, id) && Helper.StringInItChecker(strDirectorID, directors) && Helper.StringInItChecker(strWriterID, writers) && Helper.StringInItChecker(strPerformerID, performers)) {
            String[] oldAllFilmId = filmReader.getAllIdFilm();
            Film[] oldAllFilm = filmReader.getAllFilm();
            FeatureFilm[] oldAllFeatureFilm = filmReader.getAllFeatureFilm();
            FeatureFilm featureFilm = new FeatureFilm(id, title, language, directors, length, country, performers, genre, releaseDate, writers, budget);
            filmReader.setAllFilm(Helper.FilmArrayExtend(oldAllFilm, featureFilm));
            filmReader.setAllIdFilm(Helper.StringArrayExtend(oldAllFilmId, id));
            filmReader.setAllFeatureFilm(Helper.FeatureFilmArrayExtend(oldAllFeatureFilm, featureFilm));
            outputString +=
                    "FeatureFilm added successfully\n" +
                            "Film ID: " + id +
                            "\nFilm title: " + title +
                            "\n\n----------------------------------------------------------------------------------\n";

        } else {
            outputString +=
                    "Command Failed" +
                            "\nFilm ID: " + id +
                            "\nFilm title: " + title +
                            "\n\n----------------------------------------------------------------------------------\n";
        }
        return outputString;
    }

    public static String viewFilm(PeopleReader peopleReader, FilmReader filmReader, String filmId) {
        String outputString = "VIEWFILM\t" + filmId + "\n\n";
        String[] allFilmId = filmReader.getAllIdFilm();
        Film[] allFilm = filmReader.getAllFilm();
        Film film = null;

        for (int i = 0; i < allFilmId.length; i++) {
            if (allFilmId[i].equals(filmId)) {
                film = allFilm[i];
                break;
            }
        }
        if (film != null) {
            String type = film.getClass().getSimpleName();
            String filmString = "";
            switch (type) {
                case "FeatureFilm": {
                    String year = ((FeatureFilm) film).getReleaseDate().split("\\.")[2];
                    String writer = Helper.PersonNameStr(peopleReader, ((FeatureFilm) film).getWriters());
                    String directors = Helper.PersonNameStr(peopleReader, film.getDirectors());
                    String stars = Helper.PersonNameStr(peopleReader, film.getPerformers());
                    String ratings = Helper.RatingCalculator(film);


                    filmString += film.getTitle() + " (" + year + ")" + "\n" +
                            ((FeatureFilm) film).getGenre() + "\n" +
                            "Writers: " + writer + "\n" +
                            "Directors: " + directors + "\n" +
                            "Stars: " + stars + "\n" +
                            ratings +
                            "\n\n----------------------------------------------------------------------------------\n";


                    outputString += filmString;
                    break;
                }
                case "ShortFilm": {
                    String year = ((ShortFilm) film).getReleaseDate().split("\\.")[2];
                    String writer = Helper.PersonNameStr(peopleReader, ((ShortFilm) film).getWriters());
                    String directors = Helper.PersonNameStr(peopleReader, film.getDirectors());
                    String stars = Helper.PersonNameStr(peopleReader, film.getPerformers());
                    String ratings = Helper.RatingCalculator(film);


                    filmString += film.getTitle() + " (" + year + ")" + "\n" +
                            ((ShortFilm) film).getGenre() + "\n" +
                            "Writers: " + writer + "\n" +
                            "Directors: " + directors + "\n" +
                            "Stars: " + stars + "\n" +
                            ratings +
                            "\n\n----------------------------------------------------------------------------------\n";

                    outputString += filmString;

                    break;
                }
                case "Documentary": {

                    String year = ((Documentary) film).getReleaseDate().split("\\.")[2];
                    String directors = Helper.PersonNameStr(peopleReader, film.getDirectors());
                    String stars = Helper.PersonNameStr(peopleReader, film.getPerformers());
                    String ratings = Helper.RatingCalculator(film);


                    filmString += film.getTitle() + " (" + year + ")" + "\n\n" +
                            "Directors: " + directors + "\n" +
                            "Stars: " + stars + "\n" +
                            ratings +
                            "\n\n----------------------------------------------------------------------------------\n";

                    outputString += filmString;

                    break;
                }
                case "TvSeries": {
                    String startYear = ((TvSeries) film).getStartDate().split("\\.")[2];
                    String endYear = ((TvSeries) film).getEndDate().split("\\.")[2];
                    String seasons = ((TvSeries) film).getSeasons();
                    String episodes = ((TvSeries) film).getEpisodes();
                    String writers = Helper.PersonNameStr(peopleReader, ((TvSeries) film).getWriters());
                    String directors = Helper.PersonNameStr(peopleReader, film.getDirectors());
                    String stars = Helper.PersonNameStr(peopleReader, film.getPerformers());
                    String ratings = Helper.RatingCalculator(film);


                    filmString += film.getTitle() + " (" + startYear + "-" + endYear + ")" + "\n" +
                            seasons + " seasons, " + episodes + " episodes\n" +
                            ((TvSeries) film).getGenre().replace(",", ", ") + "\n" +
                            "Writers: " + writers + "\n" +
                            "Directors: " + directors + "\n" +
                            "Stars: " + stars + "\n" +
                            ratings +
                            "\n\n----------------------------------------------------------------------------------\n";

                    outputString += filmString;
                    break;
                }
            }


        } else {
            outputString += "Command Failed\n" +
                    "Film ID:\t" + filmId +
                    "\n\n----------------------------------------------------------------------------------\n";

        }


        return outputString;

    }

    public static String editRate(PeopleReader peopleReader, FilmReader filmReader, String userId, String filmId, String rating) {
        String outputString = "EDIT\tRATE\t" + userId + "\t" + filmId + "\t" + rating + "\n\n";
        String[] allPersonId = peopleReader.getAllIdPeople();
        Person[] allPerson = peopleReader.getAllPerson();
        String[] allFilmId = filmReader.getAllIdFilm();
        Film[] allFilm = filmReader.getAllFilm();
        User user = null;
        Film film = null;
        int checker=0;

        //checking whether userId and filmId is valid
        for (int i = 0; i < allPersonId.length; i++) {
            if (allPersonId[i].equals(userId) && allPerson[i] instanceof User) {
                user = (User) allPerson[i];
                break;
            }
        }
        for (int j = 0; j < allFilmId.length; j++) {
            if (allFilmId[j].equals(filmId)) {
                film = allFilm[j];
                break;
            }
        }

        if (user != null && film != null) {
            String[] raters = film.raters;
            ArrayList<String> allRatedMovies = peopleReader.allRatedMovies;
            String[] ratedMovies = user.ratedMovies;
            for (int i = 0; i < raters.length; i++) {
                //designing raters array in film's own class
                if (raters[i].split(" ")[0].equals(userId)) {
                    checker=1;
                    String oldRating = raters[i].split(" ")[1];
                    raters[i] = raters[i].replace(oldRating, rating);

                    //designing allRatedMovies array list in peopleReader class
                    for (int j = 0; j < allRatedMovies.size(); j++) {
                        if (allRatedMovies.get(j).contains(userId) && allRatedMovies.get(j).contains(filmId)) {
                            String ooldRating = allRatedMovies.get(j).split(" ")[2];
                            allRatedMovies.set(j, allRatedMovies.get(j).replace(ooldRating, rating));
                            break;
                        }
                    }
                    //designing rated movies array in user's own class
                    for (int j = 0; j < ratedMovies.length; j++) {
                        if (ratedMovies[j].contains(filmId)) {
                            String ooldRating = ratedMovies[j].split(" ")[1];
                            ratedMovies[j] = ratedMovies[j].replace(ooldRating, rating);
                            break;
                        }
                    }
                }
            }
            if (checker==0){
                outputString +=
                        "Command Failed\n" +
                                "User ID: " + userId + "\n" +
                                "Film ID: " + filmId +
                                "\n\n----------------------------------------------------------------------------------\n";
                return outputString;

            }
            peopleReader.allRatedMovies = allRatedMovies;
            user.ratedMovies = ratedMovies;
            film.raters = raters;
            outputString +=
                    "New ratings done successfully\n" +
                            "Film title: " + film.getTitle() + "\n" +
                            "Your rating: " + rating  +
                            "\n\n----------------------------------------------------------------------------------\n";


        } else {
            outputString +=
                    "Command Failed\n" +
                            "User ID: " + userId +"\n"+
                            "Film ID: " + filmId +
                            "\n\n----------------------------------------------------------------------------------\n";
        }


        return outputString;
    }

    public static String removeRate(PeopleReader peopleReader, FilmReader filmReader, String userId, String filmId) {
        String outputString = "REMOVE\tRATE\t" + userId + "\t" + filmId + "\n\n";
        String[] allPersonId = peopleReader.getAllIdPeople();
        Person[] allPerson = peopleReader.getAllPerson();
        String[] allFilmId = filmReader.getAllIdFilm();
        Film[] allFilm = filmReader.getAllFilm();
        User user = null;
        Film film = null;

        //checking whether userId and filmId is valid
        for (int i = 0; i < allPersonId.length; i++) {
            if (allPersonId[i].equals(userId) && allPerson[i] instanceof User) {
                user = (User) allPerson[i];
                for (int j = 0; j < allFilmId.length; j++) {
                    if (allFilmId[j].equals(filmId)) {
                        film = allFilm[j];
                        break;
                    }
                }
            }
        }

        if (user != null && film != null) {
            String[] raters = film.raters;
            ArrayList<String> allRatedMovies = peopleReader.allRatedMovies;
            String[] ratedMovies = user.ratedMovies;
            for (int i = 0; i < raters.length; i++) {

                //removing from the rater array which is in film's own class
                if (raters[i].contains(userId)){
                    raters=Helper.elementRemover(raters,userId);
                    for (int j = 0; j < allRatedMovies.size(); j++) {
                        //removing from the all rated movies arraylist which is in people reader class
                        if (allRatedMovies.get(j).contains(userId)&&allRatedMovies.get(j).contains(filmId)){
                            allRatedMovies.remove(j);
                            peopleReader.allRatedMovies=allRatedMovies;

                            break;
                        }
                    }
                    for (int j = 0; j < ratedMovies.length; j++) {
                        //removing from rated movies array which is in user's own class
                        if (ratedMovies[j].contains(film.getTitle())){
                            ratedMovies=Helper.elementRemover(ratedMovies,film.getTitle());
                            user.ratedMovies=ratedMovies;
                            film.raters=Helper.elementRemover(film.raters,userId);
                        }
                    }
                }else {
                    outputString +=
                                    "Command Failed\n" +
                                    "User ID: " + userId + "\n" +
                                    "Film ID: " + filmId +
                                    "\n\n----------------------------------------------------------------------------------\n";
                    return outputString;
                }
            }
            outputString +=
                    "Your film rating was removed successfully\n"+
                            "Film title: "+ film.getTitle()+
                            "\n\n----------------------------------------------------------------------------------\n";
        }else {
            outputString +=
                    "Command Failed\n" +
                            "User ID: " + userId + "\n" +
                            "Film ID: " + filmId+
                            "\n\n----------------------------------------------------------------------------------\n";
        }

        return outputString;
    }
    
    public static String listFilmSeries(FilmReader filmReader){
        String outputStr= "LIST\tFILM\tSERIES\n\n";
        TvSeries[] allTvSeries= filmReader.getAllTvSeries();
        for (TvSeries tvSeries: allTvSeries ){
            outputStr+= Helper.tvSeriesToStr(tvSeries)+"\n";
        }
        outputStr+="----------------------------------------------------------------------------------\n";
        return outputStr;
    }

    public static String listUser(PeopleReader peopleReader, String userID) {
        String outputString = "LIST\tUSER\t" +userID+"\tRATES\n\n";
        User user=null;
        try {
            user= (User)Helper.personFromID(peopleReader,userID);

        }catch (Exception e){
            outputString+=
                    "Command Failed\n"+
                            "User ID: "+userID+
                            "\n\n----------------------------------------------------------------------------------\n";
            return outputString;
        }

        //checking the user whether exist or not
        if (user==null){
            outputString+=
                    "Command Failed\n"+
                            "User ID: "+userID+
                            "\n\n----------------------------------------------------------------------------------\n";
            return outputString;
        }
        String[] ratedMovies = user.ratedMovies;
        //checking the user whether has rated any movie or not
        if (ratedMovies.length==0){
            outputString+=
                    "There is not any ratings so far"+
                            "\n\n----------------------------------------------------------------------------------\n";
            return outputString;
        }
        // designing the output as it should be with ": " 
        for (int i = 0; i < ratedMovies.length; i++) {
            outputString+= ratedMovies[i].replace(" ", ": ")+"\n";
        }

        outputString+=
                "\n----------------------------------------------------------------------------------\n";

        return outputString;

    }
    
    public static String listFilmsByCountry(FilmReader filmReader,String country){
        String outputStr="LIST\tFILMS\tBY\tCOUNTRY\t"+country+"\n\n";
        Film[] allFilm=filmReader.getAllFilm();
        for (Film film: allFilm) {
            if (film.getCountry().equals(country)){
                outputStr+= Helper.filmInfo(film);
            }
        }
        if (outputStr.equals("LIST\tFILMS\tBY\tCOUNTRY\t"+country+"\n\n")){
            outputStr+="No result\n\n";
        }
        outputStr+= "----------------------------------------------------------------------------------\n";
        return outputStr;
    }
    
    public static String listFilmsBefore(FilmReader filmReader, String year){
        String outputStr="LIST\tFEATUREFILMS\tBEFORE\t"+year+"\n\n";
        FeatureFilm[] allFeatureFilm= filmReader.getAllFeatureFilm();
        String currentStr="";
        int intYear= Integer.parseInt(year);
        for (FeatureFilm featureFilm: allFeatureFilm){
            int filmYear = Integer.parseInt(featureFilm.getReleaseDate().split("\\.")[2]);
            if (filmYear<intYear){
                currentStr+= Helper.filmInfo(featureFilm);
            }
        }if (currentStr.equals("")){
            currentStr="No result\n\n";
        }
        outputStr+=currentStr+"----------------------------------------------------------------------------------\n";
        return outputStr;
    }

    public static String listFilmsAfter(FilmReader filmReader, String year){
        String outputStr="LIST\tFEATUREFILMS\tAFTER\t"+year+"\n\n";
        FeatureFilm[] allFeatureFilm= filmReader.getAllFeatureFilm();
        String currentStr="";
        int intYear= Integer.parseInt(year);
        for (FeatureFilm featureFilm: allFeatureFilm){
            int filmYear = Integer.parseInt(featureFilm.getReleaseDate().split("\\.")[2]);
            if (filmYear>=intYear){
                currentStr+= Helper.filmInfo(featureFilm);
            }
        }if (currentStr.equals("")){
            currentStr="No result\n\n";
        }
        outputStr+=currentStr+"----------------------------------------------------------------------------------\n";
        return outputStr;
    }

    public static String listArtistByCountry(PeopleReader peopleReader, String country){
        String outputStr = "LIST\tARTISTS\tFROM\t"+country+"\n";
        Person[] allPerson = peopleReader.getAllPerson();
        String directors="\nDirectors:\n";
        String writers="\nWriters:\n";
        String actors="\nActors:\n";
        String childActors="\nChildActors:\n";
        String stuntActors="\nStuntPerformers:\n";

        for (Person person: allPerson){
            if (person.getCountry().equals(country)){
                if (!(person instanceof User)){
                    if (person instanceof Director){
                        directors += person.getName()+" "+ person.getSurname()+" "+((Director) person).getAgent()+"\n";

                    }else if (person instanceof Writer){
                        writers += person.getName()+" "+ person.getSurname()+" "+((Writer) person).getStyle()+"\n";

                    }else if (person instanceof Actor){
                        actors += person.getName()+" "+ person.getSurname()+" "+((Actor) person).getHeight()+" cm\n";

                    }else if (person instanceof ChildActor){
                        childActors+=person.getName()+" "+ person.getSurname()+" "+((ChildActor) person).getAge()+"\n";

                    }else if (person instanceof StuntPerformer){
                        stuntActors+=person.getName()+" "+ person.getSurname()+" "+((StuntPerformer) person).getHeight()+" cm\n";

                    }
                }
            }
        }
        if (directors.equals("\nDirectors:\n")){
            directors+="No result\n";

        }if (writers.equals("\nWriters:\n")){
            writers+="No result\n";

        }if (actors.equals("\nActors:\n")){
            actors+="No result\n";

        }if (childActors.equals("\nChildActors:\n")){
            childActors+="No result\n";

        }if (stuntActors.equals("\nStuntPerformers:\n")){
            stuntActors+="No result\n";
        }


        outputStr+=directors+writers+actors+childActors+stuntActors+
                "\n----------------------------------------------------------------------------------\n";
        return outputStr;

    }

    public static String listFilmsByRate(FilmReader filmReader){
        String outputStr ="LIST\tFILMS\tBY\tRATE\tDEGREE\n\n";
        String featureFilm = "FeatureFilm:\n"+Helper.filmOrder(filmReader.getAllFeatureFilm());
        String shortFilm ="ShortFilm:\n"+Helper.filmOrder(filmReader.getAllShortFilm());
        String documentary ="Documentary:\n"+Helper.filmOrder(filmReader.getAllDocumentary());
        String tvSeries = "TVSeries:\n"+Helper.filmOrder(filmReader.getAllTvSeries());
        outputStr+=featureFilm+"\n"+shortFilm+"\n"+documentary+"\n"+tvSeries+
                "\n----------------------------------------------------------------------------------\n";


        return outputStr;

    }
}