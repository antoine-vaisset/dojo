package com.gildedrose.quality;

import com.gildedrose.Item;

public class SulfurasQualityControl implements IQualityControl {

    public void updateQualityFor(Item item) {
        // Do nothing. Sulfuras never decrease in quality.
    }

}