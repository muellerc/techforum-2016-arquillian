package com.worldline.a157628.sample.techforum2016.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by cmueller on 28/03/16.
 */
@Entity
public class Card {

    @Id
    @Column(name = "ID", nullable = false, length = 42)
    private String id;
    @Column(name = "EMBOSSING_LINE", nullable = false, length = 40)
    private String embossingLine1;
    @Column(name = "CARD_NUMBER", nullable = false, length = 21)
    private String cardNumber;
    @Column(name = "EXPIRATION_DATE", nullable = false)
    private String expirationDate;
    @Column(name = "CVV", nullable = false, length = 3)
    private String cvv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmbossingLine1() {
        return embossingLine1;
    }

    public void setEmbossingLine1(String embossingLine1) {
        this.embossingLine1 = embossingLine1;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
