package pt.ipp.isep.dei.examples.tdd.basic.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Bookmark {

    private URL url;
    private String tag;
    private int rating;
    private Set<String> keywords;


    public void increaseRating() {
        this.setRating(this.getRating()+1);
    }

    public void addKeyWord(String keyword){
        this.keywords.add(keyword);
    }
}
