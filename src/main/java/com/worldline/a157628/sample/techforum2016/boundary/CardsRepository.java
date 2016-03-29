package com.worldline.a157628.sample.techforum2016.boundary;

import com.worldline.a157628.sample.techforum2016.entity.Card;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Singleton()
public class CardsRepository {

    @PersistenceContext(name = "techforum")
    private EntityManager em;

    public List<Card> getAll() {
        List<Card> cards = em.createQuery("SELECT c FROM Card c").getResultList();

        return cards;
    }

    public Card get(String id) {
        Card card = em.find(Card.class, id);

        return card;
    }

    public Card create(Card card) {
        card.setId(UUID.randomUUID().toString());
        card = em.merge(card);

        return card;
    }

    public Card update(Card card) {
        card = em.merge(card);

        return card;
    }

    public void delete(String id) {
        em.remove(get(id));
    }
}
