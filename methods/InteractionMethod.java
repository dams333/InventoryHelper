package ch.dams333.inventoryHelper.inventory.methods;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

public interface InteractionMethod<P, A> {
    void interact (Player p, InventoryAction action);
}
