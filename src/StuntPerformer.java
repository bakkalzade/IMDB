
public class StuntPerformer extends Performer{
    private String height;
    private String RealActorsId;

    public StuntPerformer(String id, String name, String surname, String country, String height, String realActorsId) {
        super(id, name, surname, country);
        this.height = height;
        RealActorsId = realActorsId;
    }

    public String getHeight() {
        return height;
    }
}
