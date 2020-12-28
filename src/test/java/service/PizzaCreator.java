package service;

import exceptions.NotCorrectPizzaDataException;
import model.Pizza;

public class PizzaCreator {

    public static final String TESTDATA_PIZZA_NAME = "testdata.pizza.name";
    public static final String TESTDATA_PIZZA_SIZE = "testdata.pizza.size";
    public static final String TESTDATA_PIZZA_SIDETYPE = "testdata.pizza.sidetype";

    public static Pizza withCredentialsFromProperties() {
        return new Pizza(TestDataReader.getTestData(TESTDATA_PIZZA_NAME),
                TestDataReader.getTestData(TESTDATA_PIZZA_SIZE),
                TestDataReader.getTestData(TESTDATA_PIZZA_SIDETYPE));
    }

    public static Pizza fromNameAndCustomSizeAndSidetype(String name, String customSize, String customSidetype) throws NotCorrectPizzaDataException {
        String size;
        String sidetype;

        switch (customSidetype) {
            case "Классика": {
                sidetype = "product.pizza.doughtype.classic";
                break;
            }
            case "Хот-Дог борт": {
                sidetype = "product.pizza.doughtype.hotdogboard";
                break;
            }
            case "Ультратонкое": {
                sidetype = "product.pizza.doughtype.ultrathin";
                break;
            }
            case "Сырный борт": {
                sidetype = "product.pizza.doughtype.cheeseboard";
                break;
            }
            case "Тонкое": {
                sidetype = "product.pizza.doughtype.thin";
                break;
            }
            default:
                throw new NotCorrectPizzaDataException("Not correct custom sidetype");
        }

        switch (customSize) {
            case "22 см": {
                size = "product.pizza.diameter.22";
                break;
            }
            case "30 см": {
                size = "product.pizza.diameter.30";
                break;
            }
            case "36 см": {
                size = "product.pizza.diameter.36";
                break;
            }
            default:
                throw new NotCorrectPizzaDataException("Not correct custom size");
        }

        return new Pizza(name, size, sidetype);
    }

    public static Pizza fromCustomPizza(Pizza customPizza) throws NotCorrectPizzaDataException {
        return PizzaCreator.fromNameAndCustomSizeAndSidetype(customPizza.getName(), customPizza.getSize(), customPizza.getSideType());
    }

}
