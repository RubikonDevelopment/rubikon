package dev.rubikon.things.features;

import net.minecraft.item.Items;

/**
 * Represents a class with types of categories.
 *
 * @see Category
 */
public class Categories {
    public static final Category COMBAT = new Category("Combat", Items.DIAMOND_SWORD);
    public static final Category MOVEMENT = new Category("Movement", Items.ELYTRA);
    public static final Category PLAYER = new Category("Player", Items.PLAYER_HEAD);
    public static final Category RENDER = new Category("Render", Items.ITEM_FRAME);
    public static final Category MISC = new Category("Misc", Items.COMMAND_BLOCK);
}
