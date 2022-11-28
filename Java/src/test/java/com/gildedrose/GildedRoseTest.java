package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void GIVEN_NormalItem_WHEN_QualityDecreases_THEN_CheckIfNotNegative(){
        Item[] items = new Item[1];
        items[0] = new Item("+5 Dexterity Vest",0,2);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("+5 Dexterity Vest",-1,0);
        assertEquals(expected.toString(),actual.toString());
    }
    @Test
    void GIVEN_AgedBrie_WHEN_updateQuality_THEN_ModifyItemQuality(){
        Item[] items = new Item[1];
        items[0] = new Item("Aged Brie",2,2);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("Aged Brie",1,3);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void GIVEN_AgedBrie_WHEN_PastSellIn_THEN_DegradeRateDouble(){
        Item[] items = new Item[1];
        items[0] = new Item("Aged Brie",0,2);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("Aged Brie",-1,4);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void GIVEN_AgedBrie_WHEN_QualityLimit_THEN_StopIncreasing(){
        Item[] items = new Item[1];
        items[0] = new Item("Aged Brie",2,50);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("Aged Brie",1,50);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void GIVEN_LegendaryItem_WHEN_ChangesHappen_THEN_StayTheSame(){
        Item[] items = new Item[1];
        items[0] = new Item("Sulfuras, Hand of Ragnaros",0,80);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("Sulfuras, Hand of Ragnaros",0,80);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void GIVEN_BackStagePass_WHEN_SellInOver10_THEN_QualityIncreaseIsNormal(){
        Item[] items = new Item[1];
        items[0] = new Item("Backstage passes to a TAFKAL80ETC concert",25,1);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert",24,2);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void GIVEN_BackStagePass_WHEN_SellInUnder11_THEN_QualityIncreaseIsTwo(){
        Item[] items = new Item[1];
        items[0] = new Item("Backstage passes to a TAFKAL80ETC concert",10,4);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert",9,6);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void GIVEN_BackStagePass_WHEN_SellInUnder6_THEN_QualityIncreaseIsThree(){
        Item[] items = new Item[1];
        items[0] = new Item("Backstage passes to a TAFKAL80ETC concert",5,7);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert",4,10);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void GIVEN_BackStagePass_WHEN_SellInExpires_THEN_QualityDepletes(){
        Item[] items = new Item[1];
        items[0] = new Item("Backstage passes to a TAFKAL80ETC concert",0,50);
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item actual = gildedRose.getItems()[0];
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert",-1,0);
        assertEquals(expected.toString(),actual.toString());
    }

}
