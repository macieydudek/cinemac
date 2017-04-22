package pl.com.bottega.cinemac.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemac.application.AdminPanel;

import pl.com.bottega.cinemac.application.PricesDto;
import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;

@RestController
public class MovieController {

    private AdminPanel adminPanel;


    public MovieController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @RequestMapping("/movies")
    @PutMapping
    public void create(@RequestBody CreateMovieCommand cmd) {
        adminPanel.createMovie(cmd);
    }

    @RequestMapping("/{movieId}/prices")
    @PutMapping
    public void defineMoviePricing(@PathVariable Long movieId, @RequestBody PricesDto pricesDto) {
        adminPanel.defineMoviePrices(movieId, pricesDto);
    }
}
