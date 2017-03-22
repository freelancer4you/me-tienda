package de.goldmann.tienda.controller;

import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.goldmann.tienda.WebTest;
import de.goldmann.tienda.domain.ProductCategory;

@RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrdersControllerIntegrationTest extends WebTest {

    // @Autowired
    private OrdersController clu;

    @Test
    public void test() {
        try {
            driver.get(HOST_ADRESS + "/orders");

            assertThat(window().title(), is("Me Tienda"));

            find("a", withText().contains(ProductCategory.FRUITSANDVEGETABLES.getCaption())).click();

            // banane Cart hinzufügen
            waitUntilElementIsDisplayed("#banane");
            $("#banane").click();

            // birne Cart hinzufügen
            waitUntilElementIsDisplayed("#birne");
            $("#birne").click();

            // prüfen, ob sich banane in Cart befindet
            waitUntilElementIsDisplayed("#bananecart");
            // prüfen, ob sich birne in Cart befindet
            waitUntilElementIsDisplayed("#birnecart");

            // Bestellung abschicken
            find("button", withText().equalTo("Bestellen")).click();

            // Anmeldung gegen KeyCloak durchführen
            waitUntilElementIsDisplayed("#username");
            $("#username").write("demo-user");
            $("#password").write("blade23");
            $("#kc-login").click();

            // Wieder zurück auf der Orderseite
            waitUntilElementIsDisplayed("#actualOrders");

            // Prüfung der Bestellung
            await().until(find("p", withText().equalTo("1 x Banane"))).displayed();
            await().until(find("p", withText().equalTo("1 x Birne"))).displayed();

            System.out.println("Stop");
            // await().until(find("div", withText("Bestellung wurde
            // abgeschickt."))).present();
            //
            // assertThat(clu.getOrders(), hasItem("[\n \"banane\",\n
            // \"birne\"\n]"));

        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
    }

}
