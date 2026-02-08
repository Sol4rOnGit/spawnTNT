package me.hiresh.tntchaos.spawnTNT;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public class SpawnTNTCommands implements CommandExecutor {
    private final SpawnTNT plugin;

    public SpawnTNTCommands(SpawnTNT plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /tntspawner <set|start|stop>");
            return false;
        }

        String subcommand = args[0].toLowerCase();

        switch (subcommand) {
            case "start":
                plugin.Enable();
                Bukkit.broadcast(Component.text("TNT spawner started!"));
                return true;
            case "stop":
                plugin.Disable();
                Bukkit.broadcast(Component.text("TNT spawner stopped!"));
                return true;
            case "set":
                if(args.length < 2){
                    sender.sendMessage("Usage: /tntspawner set [timeInSeconds]");
                    return false;
                }

                try {
                    int time = Integer.parseInt(args[1]);
                    plugin.SetDelay(time);
                    sender.sendMessage("Interval set to: " + time);
                    return true;
                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid number: " + args[1]);
                    return false;
                }
        }
        return false;
    }
}
