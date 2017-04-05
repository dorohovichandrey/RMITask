package by.bsu.appserver.dao.xml.footballerdaoimpl;

/**
 * Created by User on 17.11.2016.
 */
public enum FootballerTagType {
    FOOTBALLERS("footballers"),
    FOOTBALLER("footballer"),
    ID("id"),
    NAME("name"),
    SURNAME("surname"),
    AGE("age"),
    TEAM_ID("teamId");



    private String value;

    private FootballerTagType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FootballerTagType parse(String s)
    {
       FootballerTagType result=null;
        for(FootballerTagType elem : FootballerTagType.values())
        {
            if(elem.getValue().equals(s))
            {
                result=elem;
                break;
            }
        }
        return result;
    }
}
