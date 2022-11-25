package com.gildedrose;
class GildedRose {
    public static final java.lang.String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String MANA_CAKE = "Conjured Mana Cake";
    Item[] items;

    int qualityModifier = 1;
    int degradeRate;
    public GildedRose(Item[] items) {
        this.items = items;
    }
    void updateQuality() {
        for (Item item : items) {
            degradeRate = item.name.equals(MANA_CAKE) ? -2 : -1;
            handleQualityChanges(item);
            updateSellIn(item);
        }
    }
    private void handleQualityChanges(Item item) {
        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
            if (!item.name.equals(SULFURAS)) {
                ModifyItemQuality(item, degradeRate);
            }
        } else {
            qualityModifier = 1;
            ModifyItemQuality(item, qualityModifier);
            if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.sellIn < 11) {
                    qualityModifier = 1;
                    ModifyItemQuality(item, qualityModifier);
                }
                if (item.sellIn < 6) {
                    qualityModifier = 1;
                    ModifyItemQuality(item, qualityModifier);
                }
            }
        }
    }
    private void ModifyItemQuality(Item item, int qualityModifier) {
        int newQuality = item.quality + qualityModifier;
        boolean isInRange = newQuality <= 50 && newQuality >= 0;
        if (isInRange) {
            item.quality = newQuality;
        }
    }
    private void updateSellIn(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
        handleItemIfExpired(item);
    }
    private void handleItemIfExpired(Item item) {
        if (item.sellIn < 0) {
            handleExpiredItems(item);
        }
    }
    private void handleExpiredItems(Item item) {
        if (!item.name.equals(AGED_BRIE)) {
            if (!item.name.equals(BACKSTAGE_PASSES)) {
                if (!item.name.equals(SULFURAS)) {
                    ModifyItemQuality(item, degradeRate);
                }
            } else {
                qualityModifier = -item.quality;
                ModifyItemQuality(item, qualityModifier);
            }
        } else {
            qualityModifier = 1;
            ModifyItemQuality(item, qualityModifier);
        }
    }
}
