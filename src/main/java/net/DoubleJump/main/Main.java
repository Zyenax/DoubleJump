package net.DoubleJump.main;

import net.DoubleJump.main.Handlers.CommandHandler;
import net.DoubleJump.main.Handlers.DoubleJumpHandler;
import net.DoubleJump.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public void onEnable(){
		Listeners();
		registerCommands();
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &aPlugin Enabled"));
	}
	
	public void onDisable(){
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &cPlugin Disabled"));
	}
	
	public void Listeners(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new DoubleJumpHandler(this), this);
		pm.registerEvents(new CommandHandler(this), this);
		pm.registerEvents(new Utils(this), this);
	}
	
	public void registerCommands(){
		getCommand("doublejump").setExecutor(new CommandHandler(this));
	}
	
}
