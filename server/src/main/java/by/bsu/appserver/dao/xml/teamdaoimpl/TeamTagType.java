package by.bsu.appserver.dao.xml.teamdaoimpl;

/**
 * Created by User on 28.03.2017.
 */
public enum  TeamTagType {
    TEAMS("teams"),
    TEAM("team"),
    ID("id"),
    NAME("name"),
    CITY("city");

    private String value;

    private TeamTagType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TeamTagType parse(String s)
    {
        TeamTagType result=null;
        for(TeamTagType elem : TeamTagType.values())
        {
            if(elem.getValue().equals(s))
            {
                result=elem;
                break;
            }
        }
        if(result == null){
            throw new RuntimeException("NO SUCH TAG!!!");
        }
        return result;
    }
}
