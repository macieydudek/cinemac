package pl.com.bottega.cinemac.infrastructure.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;
import pl.com.bottega.cinemac.model.commands.Validatable;

@Component
@Aspect
public class ValidationAspect {

    @Before("execution(* pl.com.bottega.cinemac.application..*.*(..)) " +
            "&& args(pl.com.bottega.cinemac.model.commands.Validatable)" +
            "&& args(validatable)")
    public void validate(Validatable validatable) {
        Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
        validatable.validate(errors);
        if (!errors.isValid()) {
            throw new InvalidCommandException(errors);
        }
    }
}
