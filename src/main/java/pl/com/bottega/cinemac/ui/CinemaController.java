package pl.com.bottega.cinemac.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.application.CinemaCatalog;
import pl.com.bottega.cinemac.application.CinemaDto;
import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;
import pl.com.bottega.cinemac.model.commands.CreateShowingCommand;

import java.util.List;

@RestController
public class CinemaController {

    private AdminPanel adminPanel;
    private CinemaCatalog cinemaCatalog;

    public CinemaController(AdminPanel adminPanel, CinemaCatalog cinemaCatalog) {
        this.adminPanel = adminPanel;
        this.cinemaCatalog = cinemaCatalog;
    }

    @RequestMapping("/cinemas")
    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd) {
        adminPanel.createCinema(cmd);
    }

    @GetMapping
    public List<CinemaDto> showAll() {
        return cinemaCatalog.getCinemas();
    }


    public void createShowings(CreateShowingCommand cmd){}

}
