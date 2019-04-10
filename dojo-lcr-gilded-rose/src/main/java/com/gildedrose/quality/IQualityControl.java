package com.gildedrose.quality;

import com.gildedrose.Item;

public interface IQualityControl {

    int MAX_QUALITY_ALLOWED = 50;
    int DEFAULT_QUALITY_HIKE = 1;
    int DEFAULT_QUALITY_DROP = 1;

    void updateQualityFor(Item item);
}
