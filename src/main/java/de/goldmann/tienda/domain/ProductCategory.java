package de.goldmann.tienda.domain;

public enum ProductCategory {
    FRUITSANDVEGETABLES("Früchte & Gemüse"),
    MEATANDFISH("Fleisch & Fisch"),
    MILKANDCHEESE("Milch & Käse"),
    BREADANDPASTRIES("Brot & Gebäck"),
    INGREDIENTSANDSPICERY("Zutaten & Gewürze"),
    GRAINPRODUCTS("Getreideprodukte"),
    SNACKSANDCANDIES("Snacks & Süsswaren"),
    BEVERAGEANDTOBACCO("Getränke & Tabak"),
    HOUSEHOLDANDHEALTH("Haushalt & Gesundheit"),
    ANIMALNEED("Tierbedarf"),
    CONVENIENCEANDFREEZPRODUCTS("Fertig- & Tiefkühlprodukte");

    private final String caption;

    ProductCategory(final String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

}
