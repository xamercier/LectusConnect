package net.lectusConnect.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.lectusAPI.utils.SendPlayerViaBungee;

public class MainThread extends Thread{

	public void run() {
		try {
			for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
				SendPlayerViaBungee.sendPlayer(pl, "hub");
			}
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}