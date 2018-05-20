package uj.jwzp.w4.launchers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import uj.jwzp.w4.logic.MovieLister;
import uj.jwzp.w4.model.Movie;

@Slf4j
public class SpringMain {

    private static ApplicationContext getContext(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("uj.jwzp.w4.logic");

        CommandLinePropertySource properties = new SimpleCommandLinePropertySource(args);

        ctx.getEnvironment().getPropertySources().addFirst(properties);
        ctx.refresh();

        return ctx;
    }

    public static void main(String[] args) {

            ApplicationContext ctx = getContext(args);
            MovieLister lister = (MovieLister) ctx.getBean("movieLister");

            lister.moviesDirectedBy("Hoffman").stream()
                    .map(Movie::toString)
                    .forEach(log::info);
    }
}
