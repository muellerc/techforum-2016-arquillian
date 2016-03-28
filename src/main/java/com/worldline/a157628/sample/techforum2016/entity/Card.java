package com.worldline.a157628.sample.techforum2016.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by cmueller on 28/03/16.
 */
@Entity
public class Card {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
