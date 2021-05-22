package ch.dams333.inventoryHelper.inventory;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.inventory.InventoryAction;
import ch.dams333.inventoryHelper.inventory.methods.InteractionMethod;

public class InventoryItemStack {
    
    private ItemStack itemStack;
    private InteractionMethod<Player, InventoryAction> interactionMethod;


    public InteractionMethod<Player, InventoryAction> getInteractionMethod() {
        return this.interactionMethod;
    }

    public InventoryItemStack setInteractionMethod(InteractionMethod<Player, InventoryAction> interactionMethod) {
        this.interactionMethod = interactionMethod;
        return this;
    }

    public InventoryItemStack(ItemStack itemStack){
        this.itemStack = itemStack;
    }
    public InventoryItemStack(Material material){
        this.itemStack = new ItemStack(material);
    }
    public InventoryItemStack(Material material, byte data){
        this.itemStack = new ItemStack(material, data);
    }
    public InventoryItemStack(Material material, int quantity){
        this.itemStack = new ItemStack(material, quantity);
    }
    public InventoryItemStack(Material material, byte data, int quantity){
        this.itemStack = new ItemStack(material, quantity, data);
    }
    public InventoryItemStack(Material material, byte data, String name){
        ItemStack it = new ItemStack(material, data);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, int quantity, String name){
        ItemStack it = new ItemStack(material, quantity);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, String name){
        ItemStack it = new ItemStack(material);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, byte data, int quantity, String name){
        ItemStack it = new ItemStack(material, quantity, data);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, List<String> lore){
        ItemStack it = new ItemStack(material);
        ItemMeta meta = it.getItemMeta();
        meta.setLore(lore);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, int quantity, List<String> lore){
        ItemStack it = new ItemStack(material, quantity);
        ItemMeta meta = it.getItemMeta();
        meta.setLore(lore);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, byte data, List<String> lore){
        ItemStack it = new ItemStack(material, data);
        ItemMeta meta = it.getItemMeta();
        meta.setLore(lore);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, byte data, int quantity, List<String> lore){
        ItemStack it = new ItemStack(material, quantity, data);
        ItemMeta meta = it.getItemMeta();
        meta.setLore(lore);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, String name, List<String> lore){
        ItemStack it = new ItemStack(material);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, int quantity, String name, List<String> lore){
        ItemStack it = new ItemStack(material, quantity);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, byte data, String name, List<String> lore){
        ItemStack it = new ItemStack(material, data);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        it.setItemMeta(meta);
        this.itemStack = it;
    }
    public InventoryItemStack(Material material, byte data, int quantity, String name, List<String> lore){
        ItemStack it = new ItemStack(material, quantity, data);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        it.setItemMeta(meta);
        this.itemStack = it;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public boolean hasInteraction() {
        return interactionMethod != null;
    }

}
