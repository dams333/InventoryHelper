package ch.dams333.inventoryHelper.inventory.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;

import ch.dams333.inventoryHelper.inventory.SimpleInventory;

public class CloseInventoryEvent implements Listener{
    
    private SimpleInventory simpleInventory;
    private InventoryView inventoryView;

    public CloseInventoryEvent(SimpleInventory simpleInventory, InventoryView inventoryView) {
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
    public void close(InventoryCloseEvent e){
        if(e.getView() == inventoryView){
            simpleInventory.wasClosed(e);
        }
    }

}
