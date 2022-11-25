package com.gildedrose;
class GildedRose {
    public static final java.lang.String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String MANA_CAKE = "Conjured Mana Cake";
    Item[] items;

    int qualityModifier = 1;
    boolean doesDegrade;
    public GildedRose(Item[] items) {
        this.items = items;
    }
    void updateQuality() {
        for (Item item : items) {
            boolean isExpired = item.sellIn < 1;
            int degradeRate = getDegradeRate(item, isExpired);
            doesDegrade = !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES) && !item.name.equals(SULFURAS);
            boolean hasDateLimit = !item.name.equals(SULFURAS);

            if (doesDegrade) {
                ModifyItemQuality(item, degradeRate);
            }
            if(item.name.equals(AGED_BRIE)) {
                qualityModifier = isExpired ? 2 : 1;
                ModifyItemQuality(item, qualityModifier);
            }
            if (item.name.equals(BACKSTAGE_PASSES)) {
                handleBackStageQuality(item, isExpired);
            }
            if (hasDateLimit) {
                item.sellIn = item.sellIn - 1;
            }
        }
    }

    private void handleBackStageQuality(Item item, boolean isExpired) {
        qualityModifier = 1;
        ModifyItemQuality(item, qualityModifier);
        if (item.sellIn < 11) {
            qualityModifier = 1;
            ModifyItemQuality(item, qualityModifier);
        }
        if (item.sellIn < 6) {
            qualityModifier = 1;
            ModifyItemQuality(item, qualityModifier);
        }
        if (isExpired) {
            qualityModifier = -item.quality;
            ModifyItemQuality(item, qualityModifier);
        }
    }

    private int getDegradeRate(Item item, boolean isExpired) {
        final int baseDegradeRate = item.name.equals(MANA_CAKE) ? -2 : -1;
        return isExpired ? baseDegradeRate * 2 : baseDegradeRate;
    }

    private void ModifyItemQuality(Item item, int qualityModifier) {
        int newQuality = item.quality + qualityModifier;
        boolean isInRange = newQuality <= 50 && newQuality >= 0;
        if (isInRange) {
            item.quality = newQuality;
        }
    }

}
