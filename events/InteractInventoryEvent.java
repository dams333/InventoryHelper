package ch.dams333.inventoryHelper.inventory.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

import ch.dams333.inventoryHelper.inventory.SimpleInventory;

public class InteractInventoryEvent implements Listener {
    private SimpleInventory simpleInventory;
    private InventoryView inventoryView;

    public InteractInventoryEvent(SimpleInventory simpleInventory, InventoryView inventoryView) {
        this.simpleInventory = simpleInventory;
        this.inventoryView = inventoryView;
    }


    public void setSimpleInventory(SimpleInventory simpleInventory) {
        this.simpleInventory = simpleInventory;
    }

    public void setBukkitInventory(InventoryView inventoryView) {
        this.inventoryView = inventoryView;
    }

    @EventHandler
    public void interact(InventoryClickEvent e){
        if(e.getView() == inventoryView){
            e.setCancelled(true);
            if(e.getCurrentItem() != null){
                simpleInventory.interact(e);
            }
        }
    }

}
