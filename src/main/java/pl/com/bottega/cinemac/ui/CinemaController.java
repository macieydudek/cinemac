package pl.com.bottega.cinemac.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.application.CinemaCatalog;
import pl.com.bottega.cinemac.application.CinemaDto;
import pl.com.bottega.cinemac.application.MovieShowingsDto;
import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;
import pl.com.bottega.cinemac.model.commands.CreateShowingsCommand;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private AdminPanel adminPanel;
    private CinemaCatalog cinemaCatalog;

    public CinemaController(AdminPanel adminPanel, CinemaCatalog cinemaCatalog) {
        this.adminPanel = adminPanel;
        this.cinemaCatalog = cinemaCatalog;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd) {
        adminPanel.createCinema(cmd);
    }

    @GetMapping
    public List<CinemaDto> showAll() {
        return cinemaCatalog.getCinemas();
    }


    @PutMapping("/{cinemaId}/shows")
    public void createShowings(@RequestBody CreateShowingsCommand cmd, @PathVariable Long cinemaId) {
            cmd.setCinemaId(cinemaId);
            adminPanel.createShowings(cmd);
    }

    @GetMapping("{cinemaId}/movies")
    public List<MovieShowingsDto> getShowings(@PathVariable Long cinemaId, @DateTimeFormat(pattern = "yyyy/MM/dd") @RequestParam LocalDate date) {
        return cinemaCatalog.getShowings(cinemaId, date);
    }

}
