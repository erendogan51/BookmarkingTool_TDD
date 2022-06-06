package pt.ipp.isep.dei.examples.tdd.basic.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class Bookmark implements Comparable<Bookmark>{

    private URL url;
    private String tag;
    private int rating;
    private Set<String> keywords;
    private LocalDateTime localDateTime;



    public void increaseRating() {
        setRating(getRating() + 1);
    }

    public void addKeyWord(String keyword) {
        this.keywords.add(keyword);
    }


    @Override
    public int compareTo(Bookmark o) {
        if (getLocalDateTime() == null || o.getLocalDateTime() == null) {
            return 0;
        }
        return getLocalDateTime().compareTo(o.getLocalDateTime());
    }
}
