package com.gildedrose;


import static com.gildedrose.Items.SULFURAS_ITEM_NAME;

public class SellInService {

    private static final int DEFAULT_DECREASE = 1;
    private static final int NO_DECREASE = 0;

    public void updateSellInFor(Item item) {
        item.setSellIn(item.getSellIn() - sellInDecreaseFor(item));
    }

    private int sellInDecreaseFor(Item item) {
        return SULFURAS_ITEM_NAME.value().equals(item.getName()) ? NO_DECREASE : DEFAULT_DECREASE;
    }

}
