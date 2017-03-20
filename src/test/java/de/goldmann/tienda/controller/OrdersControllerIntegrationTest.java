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
            await().until($("#banane")).displayed();
            $("#banane").click();

            // birne Cart hinzufügen
            await().until($("#birne")).displayed();
            $("#birne").click();

            // prüfen, ob sich banane in Cart befindet
            await().until($("#bananecart")).displayed();
            // prüfen, ob sich birne in Cart befindet
            await().until($("#birnecart")).displayed();

            // Bestellung abschicken
            find("button", withText().equalTo("Kaufen")).click();

            // Loginseite wird angezeigt
            await().until(find("h3", withText().equalTo("Anmeldung notwendig"))).displayed();

            // Anmelden
            find("button", withText().equalTo("Anmelden")).click();

            // Anmeldung gegen KeyCloak durchführen
            await().until($("#username")).displayed();
            $("#username").write("demo-user");
            $("#password").write("blade23");
            $("#kc-login").click();

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
