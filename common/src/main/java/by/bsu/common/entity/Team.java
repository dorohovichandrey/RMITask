package by.bsu.common.entity;

/**
 * Created by User on 21.03.2017.
 */
public class Team extends Entity<Integer> {

    private String teamName;
    private String city;

    public Team() {
    }

    public Team(String teamName, String city) {
        this.teamName = teamName;
        this.city = city;
    }

    public Team(Integer id, String teamName, String city) {
        super(id);
        this.teamName = teamName;
        this.city = city;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
