
public class User extends Person{
    public String[] ratedMovies;
    public User(String id, String name, String surname, String country) {
        super(id, name, surname, country);
        ratedMovies=new String[0];//"{filmTitle rating}"
    }
}

