package com.worldline.a157628.sample.techforum2016.boundary;

import com.worldline.a157628.sample.techforum2016.ApplicationConfig;
import com.worldline.a157628.sample.techforum2016.entity.Card;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by cmueller on 28/03/16.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class CardsResourceIT {

    @ArquillianResource
    private URL baseUrl;

    private Client client;

    @Deployment(testable = false)
    public static Archive<WebArchive> deploy() throws Exception {
        return ShrinkWrap.create(WebArchive.class, "techforum-2016-arquillian.war")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("jboss-ds.xml")
                //.addAsWebInfResource("web.xml", "web.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                //.addAsResource("META-INF/persistence.xml")
                .addClasses(Card.class, CardsResource.class, CardsRepository.class, ApplicationConfig.class);
    }

    @Before
    public void setup() throws Exception {
        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() {
        if (client != null) {
            client.close();
        }
    }

    @Test
    @InSequence(1)
    public void createFirstCard() throws Exception {
        Card card = new Card();
        card.setEmbossingLine1("CHRISTIAN MUELLER");
        card.setCardNumber("4567890123456789");
        card.setExpirationDate("07/2021");
        card.setCvv("123");

        Response response = client.target(new URL(baseUrl, "v1/cards").toString())
                .request()
                .post(Entity.entity(card, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(201));
        assertThat(response.hasEntity(), is(false));
        assertThat(response.getHeaderString("Location"), containsString("http://127.0.0.1:8080/techforum-2016-arquillian/v1/cards/"));
    }

    @Test
    @InSequence(2)
    public void getOneCard() throws Exception {
        Response response = client.target(new URL(baseUrl, "v1/cards").toString())
                .request()
                .get();

        List<Card> cards = response.readEntity(new GenericType<List<Card>>(){});

        response = client.target(new URL(baseUrl, "v1/cards").toString())
                .path("{id}")
                .resolveTemplate("id", cards.get(0).getId())
                .request()
                .get();

        Card card = response.readEntity(Card.class);

        assertThat(response.getStatus(), is(200));
        assertThat(card.getEmbossingLine1(), is("CHRISTIAN MUELLER"));
        assertThat(card.getCardNumber(), is("4567890123456789"));
        assertThat(card.getExpirationDate(), is("07/2021"));
        assertThat(card.getCvv(), is("123"));
    }

    @Test
    @InSequence(3)
    public void createSecondCard() throws Exception {
        Card card = new Card();
        card.setEmbossingLine1("MAX MUSTERMANN");
        card.setCardNumber("5678901234567890");
        card.setExpirationDate("12/2017");
        card.setCvv("456");

        Response response = client.target(new URL(baseUrl, "v1/cards").toString())
                .request()
                .post(Entity.entity(card, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(201));
        assertThat(response.hasEntity(), is(false));
        assertThat(response.getHeaderString("Location"), containsString("http://127.0.0.1:8080/techforum-2016-arquillian/v1/cards/"));
    }

    @Test
    @InSequence(4)
    public void getAllCards() throws Exception {
        Response response = client.target(new URL(baseUrl, "v1/cards").toString())
                .request()
                .get();

        List<Card> cards = response.readEntity(new GenericType<List<Card>>(){});

        assertThat(response.getStatus(), is(200));
        assertThat(cards.size(), is(2));
    }

    @Test
    @InSequence(5)
    public void updateCard() throws Exception {
        Response response = client.target(new URL(baseUrl, "v1/cards").toString())
                .request()
                .get();

        List<Card> cards = response.readEntity(new GenericType<List<Card>>(){});

        response = client.target(new URL(baseUrl, "v1/cards").toString())
                .path("{id}")
                .resolveTemplate("id", cards.get(0).getId())
                .request()
                .put(Entity.entity(cards.get(0), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(202));
        assertThat(response.hasEntity(), is(false));
    }

    @Test
    @InSequence(6)
    public void deleteCard() throws Exception {
        Response response = client.target(new URL(baseUrl, "v1/cards").toString())
                .request()
                .get();

        List<Card> cards = response.readEntity(new GenericType<List<Card>>(){});

        response = client.target(new URL(baseUrl, "v1/cards").toString())
                .path("{id}")
                .resolveTemplate("id", cards.get(0).getId())
                .request()
                .delete();

        assertThat(response.getStatus(), is(202));
        assertThat(response.hasEntity(), is(false));
    }
}