package dev.rubikon.things.features;

import net.minecraft.item.Items;

/**
 * Represents a class with types of categories.
 * <p>
 *     Provides a collection of categories.
 * </p>
 *
 * @see Category
 */
public class Categories {
    /**
     * The combat category.
     * <p>
     *     Contains features that are related to combat.
     * </p>
     */
    public static final Category COMBAT = new Category("Combat", Items.DIAMOND_SWORD);
    /**
     * The movement category.
     * <p>
     *     Contains features that are related to movement.
     * </p>
     */
    public static final Category MOVEMENT = new Category("Movement", Items.ELYTRA);
    /**
     * The player category.
     * <p>
     *     Contains features that are related to the player.
     * </p>
     */
    public static final Category PLAYER = new Category("Player", Items.PLAYER_HEAD);
    /**
     * The render category.
     * <p>
     *     Contains features that are related to rendering.
     * </p>
     */
    public static final Category RENDER = new Category("Render", Items.ITEM_FRAME);
    /**
     * The misc category.
     * <p>
     *     Contains features that are related to other things.
     * </p>
     */
    public static final Category MISC = new Category("Misc", Items.COMMAND_BLOCK);
}
