package com.gildedrose;

import com.gildedrose.quality.*;

import java.util.Arrays;
import java.util.Optional;

public enum Items {
    BACKSTAGE_PASS_ITEM_NAME("Backstage passes to a TAFKAL80ETC concert", new BackstagePassQualityControl()),
    SULFURAS_ITEM_NAME("Sulfuras, Hand of Ragnaros", new SulfurasQualityControl()),
    AGED_BRIE_ITEM_NAME("Aged Brie", new AgedBrieQualityControl()),
    CONJURED_ITEM_NAME("Conjured Mana Cake", new ConjuredQualityControl());

    private String value;
    private IQualityControl qualityControl;

    Items(String value, IQualityControl qualityControl) {
        this.value = value;
    }

    public static Optional<Items> fromValue(String name) {
        return Arrays.stream(values())
                .filter(item -> item.value().equals(name))
                .findAny();
    }

    public String value() {
        return value;
    }

    public IQualityControl qualityControl() {
        return qualityControl;
    }
}
