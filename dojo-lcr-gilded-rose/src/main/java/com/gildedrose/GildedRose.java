package com.gildedrose;

import com.gildedrose.quality.DefaultQualityControl;
import com.gildedrose.quality.IQualityControl;

import java.util.List;

public class GildedRose {
    private final SellInService sellInService;
    private final DefaultQualityControl defaultQualityControl;

    public GildedRose(SellInService sellInService, DefaultQualityControl defaultQualityControl) {
        this.sellInService = sellInService;
        this.defaultQualityControl = defaultQualityControl;
    }


    public void updateQuality(List<Item> items) {
        for (Item item : items) {
            udpateSellInFor(item);
            updateQualityFor(item);
        }
    }

    private void updateQualityFor(Item item) {
        getQualityControl(item)
                .updateQualityFor(item);
    }

    private IQualityControl getQualityControl(Item item) {
        return Items.fromValue(item.getName())
                .map(Items::qualityControl)
                .orElse(defaultQualityControl);
    }

    private void udpateSellInFor(Item item) {
        sellInService.updateSellInFor(item);
    }

}