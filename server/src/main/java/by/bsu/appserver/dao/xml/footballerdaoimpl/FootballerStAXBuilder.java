package by.bsu.appserver.dao.xml.footballerdaoimpl;


import by.bsu.common.entity.Footballer;
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
import java.util.*;

/**
 * Created by User on 19.11.2016.
 */

public class FootballerStAXBuilder {

    private static final Logger LOG = LogManager.getLogger();
    private List<Footballer> footballers = new ArrayList<Footballer>();
    private XMLInputFactory inputFactory;
    private String fileName = "D:\\3kurs\\6 сем\\SpecKursJava\\MultyModule\\server\\data\\footballers.xml";

    public FootballerStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public List<Footballer> getFootballers() {
        return footballers;
    }

    public List<Footballer> buildFootballers() {
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
                    if (FootballerTagType.parse(name) == FootballerTagType.FOOTBALLER) {
                        Footballer footballer = buildFootballer(reader);
                        footballers.add(footballer);
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

        return footballers;
    }

    private Footballer buildFootballer(XMLStreamReader reader) throws XMLStreamException {

            Footballer footballer = new Footballer();


        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FootballerTagType.parse(name)) {
                        case ID:
                            footballer.setId(Integer.valueOf(getXMLText(reader)));
                            break;
                        case NAME:
                            footballer.setName(getXMLText(reader));
                            break;
                        case SURNAME:
                            footballer.setSurname(getXMLText(reader));
                            break;
                        case AGE:
                            footballer.setAge(Integer.valueOf(getXMLText(reader)));
                            break;
                        case TEAM_ID:
                            Team team = new Team(Integer.valueOf(getXMLText(reader)), null, null);
                            //footballer.setTeam(new Team("Barca", "Barcelona"));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FootballerTagType.parse(name) == FootballerTagType.FOOTBALLER) {
                        return footballer;
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
        FootballerStAXBuilder footballerStAXBuilder = new FootballerStAXBuilder();
        System.out.println(footballerStAXBuilder.buildFootballers());
    }
}
