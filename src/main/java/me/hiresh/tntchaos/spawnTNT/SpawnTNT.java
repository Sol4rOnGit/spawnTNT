package me.hiresh.tntchaos.spawnTNT;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.Listener;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.Location;

import java.util.Objects;

public final class SpawnTNT extends JavaPlugin {

    private static boolean isEnabled = false;

    public void Enable(){
        isEnabled = true;
    }

    public void Disable(){
        isEnabled = false;
    }

    private static int delaySeconds = 10;
    private static int delayTicks = delaySeconds * 20;

    public void SetDelay(int newDelaySeconds){
        delaySeconds = newDelaySeconds;
        delayTicks = delaySeconds * 20;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        isEnabled = false;

        BukkitScheduler scheduler = this.getServer().getScheduler();

        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if(!isEnabled){
                    return;
                }

                for ( Player player : Bukkit.getOnlinePlayers()){
                    Location currentLocation = player.getLocation();
                    player.getWorld().spawnEntity(currentLocation, EntityType.TNT);
                }
            }
        }, delayTicks, delayTicks);

        Objects.requireNonNull(getCommand("tntspawner")).setExecutor(new SpawnTNTCommands(this));
        Objects.requireNonNull(getCommand("tntspawner")).setTabCompleter(new SpawnTNTTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
