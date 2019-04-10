package com.gildedrose;

import com.gildedrose.quality.DefaultQualityControl;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(new SellInService(), new DefaultQualityControl());
        app.updateQuality(Arrays.asList(items));
        assertEquals("foo", items[0].getName());
    }

}
