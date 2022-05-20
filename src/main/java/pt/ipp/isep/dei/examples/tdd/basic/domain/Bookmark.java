package pt.ipp.isep.dei.examples.tdd.basic.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Bookmark {

    private URL url;
    private String tag;
    private int rating;

    public Bookmark(URL url) {
        this.url = url;
    }

    public void increaseRating() {
        this.setRating(this.getRating()+1);
    }
}
