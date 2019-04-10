package com.gildedrose;

import com.gildedrose.quality.DefaultQualityControl;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TexttestFixture {
    public static void main(String[] args) throws IOException {
        Output output = new Output();
        output.append("OMGHAI!");

        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(new SellInService(), new DefaultQualityControl());

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
            app.updateQuality(Arrays.asList(items));
        }

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected.txt")));
        output.printWithDiff(expected);

        if (!StringUtils.equals(output.toString(), expected)) {
            Assert.fail("Output is not same as expected result");
        }
    }


    private static class Output {
        private static final String RED ="\u001B[31m";
        private static final String RESET ="\u001B[0m";

        private List<String> output;

        public Output() {
            output = new ArrayList<>();
        }

        public Output append(String str) {
            this.output.add(str);
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

        public void printWithDiff(String expected) {
            List<String> expectedList = Arrays.asList(expected.split("\n"));
            for (int i = 0; i < output.size(); i++) {
                String outputLine = output.get(i);
                String expectedLine = i < expectedList.size() ? expectedList.get(i): "";
                int indexOfDiff = StringUtils.indexOfDifference(outputLine, expectedLine);

                if(indexOfDiff == -1){
                    System.out.println(StringUtils.rightPad(outputLine, 70, " ") + expectedLine);
                } else {
                    String left = outputLine.substring(0, indexOfDiff);
                    String right = outputLine.substring(indexOfDiff);
                    System.out.println(StringUtils.rightPad(left + RED + right + RESET, 70 + RED.length() + RESET.length(), " ") + expectedLine);
                }

            }
        }

        @Override
        public String toString() {
            return String.join("\n", output);
        }
    }
}
