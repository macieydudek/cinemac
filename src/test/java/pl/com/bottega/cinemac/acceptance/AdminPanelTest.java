package pl.com.bottega.cinemac.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.application.CinemaCatalog;
import pl.com.bottega.cinemac.application.CinemaDto;
import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class AdminPanelTest {

    @Autowired
    AdminPanel adminPanel;

    @Autowired
    CinemaCatalog cinemaCatalog;


    @Test
    public void shouldCreateCinema() {
        //given
        CreateCinemaCommand cmd1 = new CreateCinemaCommand();
        cmd1.setName("Kosmos");
        cmd1.setCity("Lublin");

        CreateCinemaCommand cmd2 = new CreateCinemaCommand();
        cmd2.setName("Bajka");
        cmd2.setCity("Lublin");

        CreateCinemaCommand cmd3 = new CreateCinemaCommand();
        cmd3.setName("Kosmos");
        cmd3.setCity("Łódź");
        //when
        adminPanel.createCinema(cmd1);
        adminPanel.createCinema(cmd2);
        adminPanel.createCinema(cmd3);
        //then
        List<CinemaDto> cinemas = cinemaCatalog.getCinemas();
        assertThat(cinemas.size()).isEqualTo(3);
    }

    @Test(expected = InvalidUserActionException.class)
    public void shouldNotAllowToAddSameCinemaTwice() {
        //given
        CreateCinemaCommand cmd1 = new CreateCinemaCommand();
        cmd1.setName("Kosmos");
        cmd1.setCity("Lublin");
        CreateCinemaCommand cmd2 = new CreateCinemaCommand();
        cmd2.setName("Kosmos");
        cmd2.setCity("Lublin");
        //when
        adminPanel.createCinema(cmd1);
        adminPanel.createCinema(cmd2);
    }
}

