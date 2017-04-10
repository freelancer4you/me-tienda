package de.goldmann.tienda.controller;

import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.junit4.SpringRunner;

import de.goldmann.tienda.WebTest;
import de.goldmann.tienda.domain.ProductCategory;

@RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrdersControllerIntegrationTest extends WebTest {

    private static final String ORDERS = "/orders";
    private static final String ORDERS_OVERVIEW = "/orders-overview";
    // @Autowired
    private OrdersController clu;

    @Test
    public void test() {
        WebDriver tiendaWebsiteDriver = null;
        try {
            // Client-Seite
            driver.get(HOST_ADRESS + ORDERS);
            assertThat(window().title(), is("Me Tienda"));

            // Tienda, separes Fenster, deshalb wird ein neues Driver-Objekt
            // erstellt.
            tiendaWebsiteDriver = setupDriver();
            tiendaWebsiteDriver.get(HOST_ADRESS + ORDERS_OVERVIEW);
            // TODO wir brauchen hier einen anderen Benutzer, demo-user darf
            // Tienda nicht sehen
            // Tienda-Login
            loginTienda(tiendaWebsiteDriver, "demo-user", "blade23");
            tiendaWebsiteDriver.findElement(By.linkText("Tiendas")).click();
            tiendaWebsiteDriver.findElement(By.linkText("Orders-Overview")).click();

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
            login("demo-user", "blade23");

            // Nur bei Neuanmeldung ist die Eingabe der Adresse notwendig
            final FluentList<FluentWebElement> streetElement = find("#street");
            if (!streetElement.isEmpty()) {
                $("#street").write("Straße");
                $("#houseNr").write("39");
                $("#zipcode").write("10245");
                $("#city").write("Berlin");
                $("#phonenumber").write("031434323");
                find("button", withText().equalTo("Anmelden")).click();
            }

            // Prüfung der Bestellung auf Client-Seite
            pruefeClientBestellungUebersicht();

            // Prüfung der Bestellung auf Tienda-Seite
            pruefeTiendaBestellungenUebersicht(tiendaWebsiteDriver);

        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
        finally {
            if (tiendaWebsiteDriver != null) {
                tiendaWebsiteDriver.close();
            }
        }
    }

    private void loginTienda(final WebDriver tiendaWebsiteDriver, final String username, final String password) {
        tiendaWebsiteDriver.findElement(By.id("username")).sendKeys(username);
        tiendaWebsiteDriver.findElement(By.id("password")).sendKeys(password);
        tiendaWebsiteDriver.findElement(By.id("kc-login")).click();
    }

    private void login(final String username, final String password) {
        waitUntilElementIsDisplayed("#username");
        $("#username").write(username);
        $("#password").write(password);
        $("#kc-login").click();
    }

    private void pruefeClientBestellungUebersicht() {
        // Wieder zurück auf der Orderseite
        waitUntilElementIsDisplayed("#actualOrders");

        await().until(find("p", withText().equalTo("1 x Banane"))).displayed();
        await().until(find("p", withText().equalTo("1 x Birne"))).displayed();
    }

    private void pruefeTiendaBestellungenUebersicht(final WebDriver tiendaWebsiteDriver) {
        tiendaWebsiteDriver.findElement(By.linkText("Details")).click();
        final List<WebElement> paragraphs = tiendaWebsiteDriver.findElements(By.tagName("p"));
        final WebElement firstParagraph = paragraphs.get(0);
        final LocalDate localDate = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final String formattedString = localDate.format(formatter);
        assertEquals(
                "Bestellung von 'demo@example.com' am "
                        + formattedString
                        + ", Entfernung (GPS-Berechnung: Weg Kunde-Tienda).",
                        firstParagraph.getText()
                );

        final WebElement secondParagraph = paragraphs.get(1);
        assertEquals("1 x Banane", secondParagraph.getText());

        final WebElement thirdParaGraph = paragraphs.get(2);
        assertEquals("1 x Birne", thirdParaGraph.getText());
    }

}
