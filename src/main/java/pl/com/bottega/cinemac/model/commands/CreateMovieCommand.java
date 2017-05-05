package pl.com.bottega.cinemac.model.commands;

import java.util.Set;

public class CreateMovieCommand implements Validatable {

    private String title;
    private String description;
    private Set<String> actors;
    private Set<String> genres;
    private Integer minAge = 0;
    private Integer length;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }



    @Override
    public void validate(ValidationErrors errors) {
        if (isEmpty(title)) {
            errors.add("title", "can't be blank");
        }
        if (isEmpty(description)) {
            errors.add("description", "can't be blank");
        }
        validateMinAge(errors);
        validateLength(errors);
        validateActors(errors);
        validateGenres(errors);
    }
    private void validateGenres(ValidationErrors errors) {
        if(genres == null || genres.isEmpty()){
            errors.add("actors", "can't be blank");
            return;
        }
        for (String  actor : genres){
            if(isEmpty(actor))
                errors.add("genres","can't be blank");
        }
    }
    private void validateActors(ValidationErrors errors) {
        if(actors == null || actors.isEmpty()){
            errors.add("actors", "can't be blank");
            return;
        }
        for (String  actor : actors){
            if(isEmpty(actor))
                errors.add("actors","can't be blank");
        }
    }
    private void validateLength(ValidationErrors errors) {
        if(length == null){
            errors.add("length","has to be defined");
            return;
        }
        if(length <= 0)
            errors.add("length","invalid value");
    }
    private void validateMinAge(ValidationErrors errors) {
        if(minAge == null){
            errors.add("minAge","has to be defined");
            return;
        }
        if(minAge <= 0)
            errors.add("minAge","invalid value");
    }

}
