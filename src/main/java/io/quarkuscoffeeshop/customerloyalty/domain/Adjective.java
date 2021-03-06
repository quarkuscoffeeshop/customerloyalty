package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Adjective extends PanacheEntity {

    @Transient
    static final Logger logger = LoggerFactory.getLogger(Adjective.class);

    public String startsWith;

    @Column(name = "text")
    public String value;

    public Adjective() {
    }

    public Adjective(String startsWith, String value) {
        this.startsWith = startsWith;
        this.value = value;
    }

    public static String getRandomAdjectiveThatStartsWith(final String letter) {
        logger.debug("retrieving adjectives starting with {}", letter);
        List<Adjective> adjectives = Adjective.find("from Adjective where startsWith = ?1", letter.toUpperCase()).list();
        if(logger.isDebugEnabled()){
            logger.debug("returned {} adjectives", adjectives.size());
            adjectives.forEach(adjective -> { logger.debug("{}", adjective.toString());});
        }
        return adjectives.get(new Random().nextInt(adjectives.size())).value;
    }

}
