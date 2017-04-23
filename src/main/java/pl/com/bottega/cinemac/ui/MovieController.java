package pl.com.bottega.cinemac.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemac.application.AdminPanel;

import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;
import pl.com.bottega.cinemac.model.commands.DefineMoviePricingCommand;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private AdminPanel adminPanel;


    public MovieController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateMovieCommand cmd) {
        adminPanel.createMovie(cmd);
    }

    @PutMapping("/{movieId}/prices")
    public void defineMoviePricing(@PathVariable Long movieId, @RequestBody Map<String, BigDecimal> prices) {
        DefineMoviePricingCommand cmd = new DefineMoviePricingCommand(movieId, prices);
        adminPanel.defineMoviePrices(cmd);
    }
}
