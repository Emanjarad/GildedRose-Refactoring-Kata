package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private static void updateBackstagePass(Item item) {
        if (item.sellIn < 11 && item.quality < 50) {
            item.quality++;
        }

        if (item.sellIn < 6 && item.quality < 50) {
            item.quality++;
        }
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (isNormalItem(item)) {
            updateNormalItem(item);
        } else if (isAgedBrie(item)) {
            updateAgedBrie(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePass(item);
        } else if (!isSulfuras(item)) {
            updateDefaultItem(item);

        }

        updateSellIn(item);

        if (item.sellIn < 0) {
            handleExpiredItem(item);
        }
    }

    private void handleExpiredItem(Item item) {
        if (!isAgedBrie(item)) {
            if (!isBackstagePass(item)) {
                updateNormalItem(item);
            } else {
                item.quality = 0;
            }
        } else {
            updateAgedBrie(item);
        }
    }

    private void updateSellIn(Item item) {
        if (!isSulfuras(item)) {
            item.sellIn--;
        }
    }

    private void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void updateNormalItem(Item item) {
        if (item.quality > 0 && !isSulfuras(item)) {
            item.quality--;
        }
    }

    private void updateDefaultItem(Item item) {
        if (item.quality > 0) {
            item.quality--;
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
}
