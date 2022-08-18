
public class Director extends Artist{
    private String agent;

    public Director(String id, String name, String surname, String country, String agent) {
        super(id, name, surname, country);
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }
}
