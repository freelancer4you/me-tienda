package de.goldmann.tienda.controller;

import static org.fluentlenium.core.filter.FilterConstructor.withId;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import de.goldmann.tienda.WebTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrdersControllerIntegrationTest extends WebTest {

    @Autowired
    private OrdersController clu;

    @Test
    public void test() {
        try {
            driver.get(HOST_ADRESS);

            assertThat(window().title(), is("Me Tienda"));

            find("a", withText().contains("Orders")).click();
            await().until(find("div", withId("banane"))).present();
            find("div", withId("banane")).click();

            await().until(find("div", withId("birne"))).present();
            find("div", withId("birne")).click();

            find("button", withText().equalTo("Kaufen")).click();

            await().until(find("div", withText("Bestellung wurde abgeschickt."))).present();

            assertThat(clu.getOrders(), hasItem("[\n  \"banane\",\n  \"birne\"\n]"));
        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
    }

}
