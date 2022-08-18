
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PeopleReader {
    private Person[] allPerson ;
    private String[] allIdPeople ;
    private Actor[] allActor ;
    private ChildActor[] allChildActor;
    private final StuntPerformer[] allStuntPerformer;
    private Director[] allDirector;
    private Writer[] allWriter;
    private User[] allUser;
    private String[] allIdPerformer;
    public ArrayList<String> allRatedMovies;//{"userId filmId rating"}

    public PeopleReader(String fileName) {
        BufferedReader objReader = null;
        BufferedReader objReader2 =null;
        Person[] allPerson = new Person[0];
        String[] allIdPeople = new String[0];
        Actor[] allActor = new Actor[0];
        ChildActor[] allChildActor= new ChildActor[0];
        StuntPerformer[] allStuntPerformer = new  StuntPerformer[0];
        Director[] allDirector =new Director[0];
        Writer[] allWriter = new Writer[0];
        User[] allUser = new User[0];
        String[] allPerformerID= new String[0];
        int ActorCount=0;
        int ChildActorCount=0;
        int StuntPerformerCount=0;
        int DirectorCount=0;
        int WriterCount=0;
        int total;
        int UserCount=0;
        try {
            //find necessary length of arrays for each artists array by reading the file and finding how many times a person type is called.
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(fileName));
            objReader2 = new BufferedReader(new FileReader(fileName));
            while ((strCurrentLine = objReader.readLine()) != null) {
                String type= strCurrentLine.split(":")[0];
                switch (type){
                    case "Actor":
                        ActorCount++;
                        break;

                    case "ChildActor":
                        ChildActorCount++;
                        break;

                    case "StuntPerformer":
                        StuntPerformerCount++;
                        break;

                    case "Director":
                        DirectorCount++;
                        break;

                    case "Writer":
                        WriterCount++;
                        break;

                    case "User":
                        UserCount++;
                        break;
                }
            }
            objReader.close();


            // setting the length of arrays as necessary length that is found above
            total=ActorCount+ChildActorCount+DirectorCount+WriterCount+StuntPerformerCount+UserCount;
            allPerson = new Person[total];
            allIdPeople = new String[total];
            allActor = new Actor[ActorCount];
            allChildActor = new ChildActor[ChildActorCount];
            allStuntPerformer= new StuntPerformer[StuntPerformerCount];
            allDirector= new Director[DirectorCount];
            allWriter=new Writer[WriterCount];
            allUser = new User[UserCount];
            allPerformerID= new String[StuntPerformerCount+ActorCount+ChildActorCount];

            //filling the arrays according to the first word of a line
            while ((strCurrentLine = objReader2.readLine()) != null) {
                String type= strCurrentLine.split(":")[0];
                String[] strWithoutType=strCurrentLine.split(":")[1].trim().split("\t");
                String id=strWithoutType[0];
                Person object=null;
                switch (type) {
                    case "Actor":
                        Actor actor = new Actor(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4]);
                        Helper.elementAdderToBlank(allPerformerID,strWithoutType[0]);
                        for (int i = 0; i < allActor.length; i++) {
                            if (allActor[i] == null) {
                                allActor[i] = actor;
                                object = actor;
                                break;
                            }
                        }
                        break;

                    case "ChildActor":
                        ChildActor childActor = new ChildActor(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4]);
                        Helper.elementAdderToBlank(allPerformerID,strWithoutType[0]);
                        for (int i = 0; i < allChildActor.length; i++) {
                            if (allChildActor[i] == null) {
                                allChildActor[i] = childActor;
                                object = childActor;
                                break;
                            }
                        }
                        break;

                    case "StuntPerformer":
                        StuntPerformer stuntPerformer = new StuntPerformer(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4], strWithoutType[5]);
                        Helper.elementAdderToBlank(allPerformerID,strWithoutType[0]);
                        for (int i = 0; i < allStuntPerformer.length; i++) {
                            if (allStuntPerformer[i] == null) {
                                allStuntPerformer[i] = stuntPerformer;
                                object = stuntPerformer;
                                break;
                            }
                        }
                        break;

                    case "Director":
                        Director director = new Director(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4]);
                        for (int i = 0; i < allDirector.length; i++) {
                            if (allDirector[i] == null) {
                                allDirector[i] = director;
                                object = director;
                                break;
                            }
                        }
                        break;

                    case "Writer":
                        Writer writer = new Writer(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3], strWithoutType[4]);
                        for (int i = 0; i < allWriter.length; i++) {
                            if (allWriter[i] == null) {
                                allWriter[i] = writer;
                                object = writer;
                                break;
                            }
                        }
                        break;
                    case "User":
                        User user = new User(strWithoutType[0], strWithoutType[1], strWithoutType[2], strWithoutType[3]);
                        for (int i=0 ;i <allUser.length; i++){
                            if (allUser[i]== null){
                                allUser[i]=user;
                                object=user;
                                break;
                            }
                        }
                        break;
                }
                for (int i=0; i<allPerson.length; i++){
                    if (allPerson[i]==null){
                        allPerson[i]=object;
                        allIdPeople[i]=id;
                        break;
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("oops something went wrong");
            ;
        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                System.out.println("oops something went wrong");
            }
            try {
                if (objReader2!= null)
                    objReader2.close();
            }catch (Exception ex){
                System.out.println("oops something went wrong");
            }
        }
        this.allPerson = allPerson;
        this.allIdPeople = allIdPeople;
        this.allActor = allActor;
        this.allChildActor = allChildActor;
        this.allStuntPerformer = allStuntPerformer;
        this.allDirector = allDirector;
        this.allWriter = allWriter;
        this.allUser = allUser;
        this.allIdPerformer= allPerformerID;
        this.allRatedMovies=new ArrayList<>();
    }

    public Person[] getAllPerson() {
        return allPerson;
    }

    public String[] getAllIdPeople() {
        return allIdPeople;
    }

    public Director[] getAllDirector() {
        return allDirector;
    }

    public Writer[] getAllWriter() {
        return allWriter;
    }

    public String[] getAllIdPerformer() {
        return allIdPerformer;
    }

}