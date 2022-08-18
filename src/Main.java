
import java.io.*;

public class Main {
    public static void commander(PeopleReader peopleReader, FilmReader filmReader, String commandFile,String outputFile){
        try{
            BufferedReader br=new BufferedReader(new FileReader(commandFile));
            String strCurrentLine;
            while((strCurrentLine= br.readLine()) !=null){
                String[] arrayCurrentLine= strCurrentLine.split("\t");
                String type=arrayCurrentLine[0];
                String strCurrentOutput="";
                switch (type){
                    case "RATE":
                        strCurrentOutput=Commands.rate(peopleReader,filmReader,arrayCurrentLine[1],arrayCurrentLine[2],arrayCurrentLine[3]);
                        break;
                    case "ADD":
                        strCurrentOutput=
                                Commands.addFilm(peopleReader,filmReader,arrayCurrentLine[1],arrayCurrentLine[2],
                                arrayCurrentLine[3],arrayCurrentLine[4],arrayCurrentLine[5],arrayCurrentLine[6],
                                arrayCurrentLine[7],arrayCurrentLine[8],arrayCurrentLine[9],arrayCurrentLine[10],
                                arrayCurrentLine[11],arrayCurrentLine[12]);
                        break;
                    case "VIEWFILM":
                        strCurrentOutput=Commands.viewFilm(peopleReader,filmReader,arrayCurrentLine[1]);
                        break;
                    case "EDIT":
                        strCurrentOutput=Commands.editRate(peopleReader,filmReader,arrayCurrentLine[2],arrayCurrentLine[3],arrayCurrentLine[4]);
                        break;
                    case "REMOVE":
                        strCurrentOutput=Commands.removeRate(peopleReader,filmReader,arrayCurrentLine[2],arrayCurrentLine[3]);
                        break;
                    case "LIST":
                        if (arrayCurrentLine[1].equals("USER")){
                            strCurrentOutput=Commands.listUser(peopleReader,arrayCurrentLine[2]);

                        }else if (arrayCurrentLine[1].equals("FILM")&&arrayCurrentLine[2].equals("SERIES")){
                            strCurrentOutput=Commands.listFilmSeries(filmReader);

                        }else if (arrayCurrentLine[1].equals("FILMS")&&arrayCurrentLine[3].equals("COUNTRY")){
                            strCurrentOutput=Commands.listFilmsByCountry(filmReader,arrayCurrentLine[4]);

                        }else if (arrayCurrentLine[1].equals("FILMS")&&arrayCurrentLine[3].equals("RATE")){
                            strCurrentOutput=Commands.listFilmsByRate(filmReader);

                        }else if (arrayCurrentLine[1].equals("FEATUREFILMS")&&arrayCurrentLine[2].equals("BEFORE")){
                            strCurrentOutput=Commands.listFilmsBefore(filmReader,arrayCurrentLine[3]);

                        }else if ((arrayCurrentLine[1].equals("FEATUREFILMS")&&arrayCurrentLine[2].equals("AFTER"))){
                            strCurrentOutput=Commands.listFilmsAfter(filmReader,arrayCurrentLine[3]);

                        }else if ((arrayCurrentLine[1].equals("ARTISTS")&&arrayCurrentLine[2].equals("FROM"))){
                            strCurrentOutput=Commands.listArtistByCountry(peopleReader,arrayCurrentLine[3]);
                        }
                        break;
                }
                try{
                    BufferedWriter brr=new BufferedWriter(new FileWriter(outputFile,true));
                    brr.write(strCurrentOutput);
                    brr.close();
                }catch (Exception e){
                    System.out.println("oops something went wrong// output file writing.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        String peopleFile = args[0];
        String filmFile = args[1];
        String commandFile = args[2];
        String outputFile = args[3];
        PeopleReader peopleReader = new PeopleReader(peopleFile);
        FilmReader filmReader = new FilmReader(filmFile);
        commander(peopleReader,filmReader,commandFile,outputFile);
    }
}
