package pl.com.bottega.cinemac.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBCleaner {

    @Autowired
    private JdbcTemplate template;

    private final String[] tables = {"Movie_Actors", "Movie_Genres", "Showing", "Movie", "Cinema"};

    public void clean() {
        for (String table : tables)
            template.execute("DELETE FROM " + table);
    }
}
