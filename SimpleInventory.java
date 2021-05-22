package ch.dams333.inventoryHelper.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitRunnable;

import ch.dams333.inventoryHelper.InventoryHelper;
import ch.dams333.inventoryHelper.inventory.events.CloseInventoryEvent;
import ch.dams333.inventoryHelper.inventory.events.InteractInventoryEvent;
import ch.dams333.inventoryHelper.inventory.exception.ContentException;
import ch.dams333.inventoryHelper.inventory.methods.UpdateMethod;

public class SimpleInventory extends BukkitRunnable{

    private String id;
    private String title;
    private int rows;
    private boolean closeable;
    private Consumer<Player> closeMethod;
    private ContentManager contentManager;
    private int maxTime;
    private int time = 1;
    private UpdateMethod<Player, Integer> updateMethod;

    public int getMaxTime() {
        return this.maxTime;
    }

    public SimpleInventory(){
        this.runTaskTimer(InventoryHelper.INSTANCE, 5, 5);
    }

    private CloseInventoryEvent closeEvent;

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getRows() {
        return this.rows;
    }

    public boolean getCloseable() {
        return this.closeable;
    }

    public boolean isCloseable() {
        return this.closeable;
    }

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateCloseable(boolean closeable){
        this.closeable = closeable;
    }

    private InteractInventoryEvent interactEvent;

    public void open(Player p){
        this.opened.put(p, true);
        Inventory inv = Bukkit.createInventory(null, rows * 9, title);

        try {
            contentManager.init(rows, inv, this);
        } catch (ContentException e) {
            e.printStackTrace();
        }

        InventoryView view = p.openInventory(inv);
        if(closeEvent == null){
            closeEvent = new CloseInventoryEvent(this, view);
            InventoryHelper.INSTANCE.getServer().getPluginManager().registerEvents(closeEvent, InventoryHelper.INSTANCE);
        }else{
            closeEvent.setBukkitInventory(view);
        }
        if(interactEvent == null){
            interactEvent = new InteractInventoryEvent(this, view);
            InventoryHelper.INSTANCE.getServer().getPluginManager().registerEvents(interactEvent, InventoryHelper.INSTANCE);
        }else{
            interactEvent.setBukkitInventory(view);
        }
    }

    private Map<Player, Boolean> opened = new HashMap<>();

    public void close(Player p){
        this.opened.put(p, false);
        p.closeInventory();
    }

    public void update(Player p){
        this.opened.put(p, false);
        Inventory inv = Bukkit.createInventory(null, rows * 9, title);

        try {
            contentManager.update(rows, inv, this);
        } catch (ContentException e) {
            e.printStackTrace();
        }

        InventoryView view = p.openInventory(inv);
        closeEvent.setBukkitInventory(view);
        interactEvent.setBukkitInventory(view);
        this.opened.put(p, true);
    }

    public void wasClosed(InventoryCloseEvent e) {
        if(!closeable){
            if(this.opened.get(e.getPlayer())){
                InventoryHelper.INSTANCE.getServer().getScheduler().scheduleSyncDelayedTask(InventoryHelper.INSTANCE, new Runnable() {
                    public void run() {
                        InventoryView view = e.getPlayer().openInventory(e.getInventory());
                        closeEvent.setBukkitInventory(view);
                        interactEvent.setBukkitInventory(view);
                    }
                }, 1L);
            }
        }else{
            this.opened.put((Player) e.getPlayer(), false);
            if(closeMethod != null){
                closeMethod.accept((Player) e.getPlayer());
            }
        }
    }

    public void interact(InventoryClickEvent e) {
        contentManager.interact(e);
    }

    public static Builder builder() { 
        return new Builder(); 
    }

    public static final class Builder{
        private String id = "unknow";
        private String title = "";
        private int rows = 3;
        private boolean closeable = false;
        private Consumer<Player> closeMethod;
        private ContentManager contentManager = new ContentManager();
        private int maxTime = 1;
        private UpdateMethod<Player, Integer> updateMethod;

        private Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder rows(int rows) {
            if(rows <= 6){
                this.rows = rows;
            }
            return this;
        }

        public Builder closeable(boolean closeable) {
            this.closeable = closeable;
            return this;
        }

        public Builder onClose(Consumer<Player> method){
            this.closeMethod = method;
            return this;
        }

        public Builder setContentManager(ContentManager contentManager){
            this.contentManager = contentManager;
            return this;
        }

        public Builder setMaxTime(int time){
            this.maxTime = time;
            return this;
        }

        public Builder setUpdateMethod(UpdateMethod<Player, Integer> method){
            this.updateMethod = method;
            return this;
        }

        public SimpleInventory build() {

            SimpleInventory inv = new SimpleInventory();
            inv.id = this.id;
            inv.title = this.title;
            inv.rows = this.rows;
            inv.closeable = this.closeable;
            inv.closeMethod = this.closeMethod;
            inv.contentManager = this.contentManager;
            inv.maxTime = this.maxTime;
            inv.updateMethod = this.updateMethod;

            return inv;
        }
    }

    @Override
    public void run() {
        time++;
        if(time > maxTime) time = 1;
        if(updateMethod != null){
            for(Player p : this.opened.keySet()){
                if(this.opened.get(p)){
                    updateMethod.update(p, time);;
                }
            }
        }
    }
    
}
