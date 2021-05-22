package ch.dams333.inventoryHelper.inventory.methods;

import org.bukkit.entity.Player;

public interface UpdateMethod<P, T> {
    void update (Player p, Integer time);
}
