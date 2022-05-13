package pt.ipp.isep.dei.examples.tdd.basic.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@AllArgsConstructor
@Getter
@Setter
public class Bookmark {

    private URL url;
    private String tag;

    public Bookmark(URL url) {
        this.url = url;
    }

}
