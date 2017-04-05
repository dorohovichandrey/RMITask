import by.bsu.appserver.dao.xml.teamdaoimpl.TeamXmlDAO;
import by.bsu.common.entity.Team;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by User on 28.03.2017.
 */
public class XmlDAOTest {
    @Test
    public void findTeamByIdTest() throws Exception {
        TeamXmlDAO teamXmlDAO = new TeamXmlDAO();
        Team team = teamXmlDAO.findById(1);
        Assert.assertEquals(1, (long)team.getId());

    }
}
