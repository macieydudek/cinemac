package pl.com.bottega.cinemac.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;

import java.util.Arrays;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ReservationProcessTest {

    @Autowired
    AdminPanel adminPanel;

    @Test
    public void shouldCalculateTotalPrice() {
        //given
        given().prepareMovie();
    }


    private ElementAssembler given() {
        return new ElementAssembler();
    }

    class ElementAssembler {

        void prepareMovie() {
            CreateMovieCommand cmd = new CreateMovieCommand();
            cmd.setTitle("Test title");
            cmd.setGenres(new HashSet<>(Arrays.asList("test genre")));
            cmd.setActors(new HashSet<>(Arrays.asList("test actor")));
            cmd.setDescription("test description");
            cmd.setLength(100);
            cmd.setMinAge(13);
            adminPanel.createMovie(cmd);
        }
    }

}

