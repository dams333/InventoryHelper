package ch.dams333.inventoryHelper.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import ch.dams333.inventoryHelper.inventory.exception.ContentException;

public class ContentManager {

    private Map<Integer, Map<Integer, InventoryItemStack>> items;
    private Map<Integer, Integer> state;
    private SimpleInventory inv;

    public ContentManager() {
        this.items = new HashMap<>();
        state = new HashMap<>();
    }

    public SimpleInventory getInventory() {
        return this.inv;
    }

    public int getState(int slot){
        return state.get(slot);
    }

    public void setDefaultItem(int index, InventoryItemStack item){
        setItemForStep(index, 0, item);
    }

    public void setItemForStep(int index, int step, InventoryItemStack item){
        if(!this.items.keySet().contains(index)){
            this.items.put(index, new HashMap<>());
        }
        HashMap<Integer, InventoryItemStack> map = (HashMap<Integer, InventoryItemStack>) this.items.get(index);
        map.put(step, item);
        this.items.put(index, map);
    }

    public InventoryItemStack getCurrentItem(int slot){
        return this.items.get(slot).get(this.state.get(slot));
    }

    public void changeState(int slot, int step, Player p){
        if(this.items.keySet().contains(slot)){
            if(this.items.get(slot).keySet().contains(step)){
                this.state.put(slot, step);
                inv.update(p);
            }
        }
    }

    public void init(int rows, Inventory inv, SimpleInventory simpleInv) throws ContentException {
        this.inv = simpleInv;
        int size = rows*9;
        for(int index : items.keySet()){
            this.state.put(index, 0);
            if(index < size){
                inv.setItem(index, getCurrentItem(index).getItemStack());
            }else{
                throw new ContentException("The index is to big (max is " + (size - 1) + ")");
            }
        }
    }

    public void update(int rows, Inventory inv, SimpleInventory simpleInv) throws ContentException {
        int size = rows*9;
        for(int index : items.keySet()){
            if(index < size){
                inv.setItem(index, getCurrentItem(index).getItemStack());
            }else{
                throw new ContentException("The index is to big (max is " + (size - 1) + ")");
            }
        }
    }

    public void interact(InventoryClickEvent e) {
        for(int index : items.keySet()){
            if(e.getSlot() == index){
                if(getCurrentItem(index).hasInteraction()){
                    getCurrentItem(index).getInteractionMethod().interact((Player) e.getWhoClicked(), e.getAction());
                }
            }
        }
    }
    
}
