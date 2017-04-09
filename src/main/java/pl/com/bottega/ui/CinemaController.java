package pl.com.bottega.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.application.AdminPanel;
import pl.com.bottega.application.CinemaDto;
import pl.com.bottega.model.commands.CreateCinemaCommand;
import pl.com.bottega.model.commands.CreateShowingCommand;

import java.util.List;

@RestController
public class CinemaController {

    private AdminPanel adminPanel;

    public CinemaController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @RequestMapping("/cinemas")
    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd) {
        adminPanel.createCinema(cmd);
    }

    public List<CinemaDto> showAll() {
       return null;
    }

    public void createShowings(CreateShowingCommand cmd){}

}
