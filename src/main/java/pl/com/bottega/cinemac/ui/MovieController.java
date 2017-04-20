package pl.com.bottega.cinemac.ui;

import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemac.application.AdminPanel;
        import pl.com.bottega.cinemac.model.Movie;
        import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;

@RestController
public class MovieController {

    private AdminPanel adminPanel;


    public MovieController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @RequestMapping("/movies")
    @PutMapping
    public void put(@RequestBody CreateMovieCommand cmd) {
        adminPanel.createMovie(cmd);
    }


}
