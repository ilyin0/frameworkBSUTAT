package test;

import exceptions.NotCorrectPizzaDataException;
import model.Pizza;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import service.PizzaCreator;

public class CartTest extends CommonConditions {

    MainPage mainPage;

    @BeforeMethod
    public void openPage() {
        mainPage = new MainPage(driver);
        mainPage.openPage().closeAds();
    }

    @Test
    public void testAddPizzaToCart() throws NotCorrectPizzaDataException {
        Pizza pizza = PizzaCreator.withCredentialsFromProperties();
        mainPage.addPizzaToCard(pizza);

        Assert.assertTrue(pizza.equalsIgnoreCase(PizzaCreator.fromCustomPizza(mainPage.getLastAddedToCartPizza())), "Last added pizza is not equals to expected");
    }

    @Test
    public void testRemovePizzaFromCart() {
        Pizza pizza = PizzaCreator.withCredentialsFromProperties();
        mainPage.addPizzaToCard(pizza);

        int beginPizzasCount = mainPage.pizzasInCartCount();
        mainPage.removePizzaFromCart(pizza);
        int endPizzasCount = mainPage.pizzasInCartCount();

        Assert.assertEquals(endPizzasCount, beginPizzasCount - 1, "Pizzas in cart count didn't get reduced");
    }
}
