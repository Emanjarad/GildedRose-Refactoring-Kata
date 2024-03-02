package com.gildedrose;

class GildedRose {
    Item[] items;
    public GildedRose(Item[] items) {
        this.items = items;
    }
    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }
    private void updateItemQuality(Item item) {
        if (isNormalItem(item)) {
            updateNormalItemQuality(item);
        } else if (isAgedBrie(item)) {
            updateAgedBrieQuality(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePassQuality(item);
        } else if (!isSulfuras(item)) {
            updateDefaultItemQuality(item);
        }
        updateSellIn(item);
        if (item.sellIn < 0) {
            handleExpiredItem(item);
        }
    }
    private boolean isNormalItem(Item item) {
        return !isAgedBrie(item)
            && !isBackstagePass(item);
    }
    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }
    private void updateSellIn(Item item) {
        if (!isSulfuras(item)) {
            item.sellIn--;
        }
    }
    private void updateAgedBrieQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
    private void updateNormalItemQuality(Item item) {
        if (item.quality > 0 && !isSulfuras(item)) {
            item.quality--;
        }
    }
    private void updateDefaultItemQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
    private void updateBackstagePassQuality(Item item) {
        if (item.sellIn < 11 && item.quality < 50) {
            item.quality++;
        }
        if (item.sellIn < 6 && item.quality < 50) {
            item.quality++;
        }
    }
    private void handleExpiredItem(Item item) {
        if (!isAgedBrie(item)) {
            if (!isBackstagePass(item)) {
                updateNormalItemQuality(item);
            } else {
                item.quality = 0;
            }
        } else {
            updateAgedBrieQuality(item);
        }
    }
}
