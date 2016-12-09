package net.DoubleJump.main.Handlers;

import java.util.HashMap;

import net.DoubleJump.main.Main;
import net.DoubleJump.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandHandler implements Listener, CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main plugin;

	public CommandHandler(Main hub) {
		this.plugin = hub;
	}

	public static HashMap<Player, Player> DoubleJump = new HashMap<Player, Player>();
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
		if(command.getName().equalsIgnoreCase("doublejump")){
			if(args.length <= 0){
			if(sender.hasPermission("doublejump.self") || sender.isOp()){
				if(DoubleJump.containsKey(player)){
					DoubleJump.remove(player);
					player.setAllowFlight(false);
					player.setFlying(false);
					sender.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &cYou no longer have access to DoubleJump."));
				}else{
					player.setAllowFlight(true);
					player.setFlying(false);
					DoubleJump.put(player, player);
					sender.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &aYou now have access to DoubleJump."));
				}
			}else{
				sender.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &cYou dont have the permission &bDoubleJump.self"));
			}
			}
			if(args.length == 1){
				if(sender.hasPermission("doublejump.others") || sender.isOp()){
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null){
					sender.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &cYou must supply an online players name."));
				}else{
					if(DoubleJump.containsKey(target)){
						DoubleJump.remove(target);
						target.setAllowFlight(false);
						target.setFlying(false);
						target.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &cYou no longer have access to DoubleJump."));
						sender.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &b" + target.getName() + " &cno longer has access to DoubleJump."));
					}else if(!DoubleJump.containsKey(target)){
						DoubleJump.put(target, target);
						target.setAllowFlight(true);
						target.setFlying(false);
						target.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &aYou now have access to DoubleJump."));
						sender.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &b" + target.getName() + " &anow has access to DoubleJump."));
					}
				}
				}else{
					sender.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &cYou dont have the permission &bDoubleJump.others"));
				}
			}else if(args.length >= 2){
				sender.sendMessage(Utils.color("&8&l[&e&lDoubleJump&8&l] &c/DoubleJump [target]"));
			}
		}
		}
		return true;
	}
	
	
	/*
	 * Type /forcefield to give yourself a forcefield or type /forcefield [target] to give another player a DoubleJump. The permission for giving yourself a forcefield with the /forcefield command is "DoubleJump.self" and the permission for giving other players forcefields with /forcefield [target] is "DoubleJump.others" forcefields make any players that dont have an active forcefield on get thrown back and away from the user with a forcefield if two users have forcefields and they get close to eachother they will not be thrown back and this is for collaboration purposes such as two youtubers using forcefields to keep players away.
	 */

}
