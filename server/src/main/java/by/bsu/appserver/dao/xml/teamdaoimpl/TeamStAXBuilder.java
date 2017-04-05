package by.bsu.appserver.dao.xml.teamdaoimpl;

import by.bsu.common.entity.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28.03.2017.
 */
public class TeamStAXBuilder {
    private static final Logger LOG = LogManager.getLogger();
    private List<Team> teams = new ArrayList<Team>();
    private XMLInputFactory inputFactory;
    private String fileName = "D:\\3kurs\\6 сем\\SpecKursJava\\MultyModule\\server\\data\\teams.xml";

    public TeamStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Team> buildTeams() {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (TeamTagType.parse(name) == TeamTagType.TEAM) {
                        Team team = buildTeam(reader);
                        teams.add(team);
                    }

                }
            }
        } catch (XMLStreamException ex) {
            LOG.error(ex);
        } catch (FileNotFoundException ex) {
            LOG.error(ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error(e);
            }
        }

        return teams;
    }

    private Team buildTeam(XMLStreamReader reader) throws XMLStreamException {

        Team team = new Team();


        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TeamTagType.parse(name)) {
                        case ID:
                            team.setId(Integer.valueOf(getXMLText(reader)));
                            break;
                        case NAME:
                            team.setTeamName(getXMLText(reader));
                            break;
                        case CITY:
                            team.setCity(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TeamTagType.parse(name) == TeamTagType.TEAM) {
                        return team;
                    }
                    break;
            }
        }
        throw new RuntimeException("XML parsing exception");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    public static void main(String[] args) {
        TeamStAXBuilder teamStAXBuilder = new TeamStAXBuilder();
        System.out.println( teamStAXBuilder.buildTeams());
    }
}
