package com.gildedrose.quality;

import com.gildedrose.Item;

import static java.lang.Math.min;

public class AgedBrieQualityControl implements IQualityControl {

    public void updateQualityFor(Item item) {
        item.setQuality(newQualityFor(item));
    }

    private int newQualityFor(Item item) {
        return min(item.getQuality() + DEFAULT_QUALITY_HIKE, MAX_QUALITY_ALLOWED);
    }

}