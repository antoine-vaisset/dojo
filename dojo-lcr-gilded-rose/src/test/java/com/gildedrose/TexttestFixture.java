package com.gildedrose;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TexttestFixture {
    public static void main(String[] args) throws IOException {
        Output output = new Output();
        output.append("OMGHAI!");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            output.append("-------- day " + i + " --------");
            output.append("name, sellIn, quality");
            for (Item item : items) {
                output.append(item);
            }
            output.append();
            app.updateQuality();
        }

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected.txt")));
        System.out.println(StringUtils.difference(output.toString(), expected));

        if(!StringUtils.equals(output.toString(), expected)){
            Assert.fail("Output is not same as expected result");
        }
    }


    private static class Output {
        private String ouput;

        public Output() {
            ouput = "";
        }

        public Output append(String str){
            this.ouput = ouput.concat(str).concat("\n");
            return this;
        }

        public Output append(Object item) {
            this.append(item == null ? "null" : item.toString());
            return this;
        }

        public Output append() {
            this.append("");
            return this;
        }

        @Override
        public String toString() {
            return ouput;
        }
    }

    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }
        if (str2 == null) {
            return str1;
        }
        int at = indexOfDifference(str1, str2);
        if (at == -1) {
            return "";
        }
        return str2.substring(at);
    }

    public static int indexOfDifference(String str1, String str2) {
        if (str1 == str2) {
            return -1;
        }
        if (str1 == null || str2 == null) {
            return 0;
        }
        int i;
        for (i = 0; i < str1.length() && i < str2.length(); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }
        if (i < str2.length() || i < str1.length()) {
            return i;
        }
        return -1;
    }
}
