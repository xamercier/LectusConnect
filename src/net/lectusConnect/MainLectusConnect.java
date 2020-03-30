package net.lectusConnect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.lectusAPI.grade.Rank;
import net.lectusAPI.utils.TeamUtils;
import net.lectusConnect.events.EventsManager;
import net.lectusConnect.events.MainThread;

public class MainLectusConnect extends JavaPlugin {

	public static MainLectusConnect instance;

	public List<Player> mod = new ArrayList<>();

	public static MainLectusConnect getInstance() {
		return instance;
	}

	public void onEnable() {
		super.onEnable();
		instance = this;
		new EventsManager(this).registerEvents();

		for (Rank rank : Rank.values()) {
			TeamUtils.getInstance().createTeam(rank.getName(), rank.getShortName() + " ");
		}

		for (World world : Bukkit.getWorlds()) {
			String name = world.getName();
			getServer().getWorld(name).setStorm(false);
			getServer().getWorld(name).getEntities().clear();
			getServer().getWorld(name).setTime(0);
			for (Entity en : getServer().getWorld(name).getEntities()) {
				if (!(en instanceof Player)) {
					en.remove();
				}
			}
		}
		
		MainThread t = new MainThread();
		t.run();
		
	}

	public void onDisable() {
		super.onDisable();
		for (Rank rank : Rank.values()) {
			TeamUtils.getInstance().deleteTeam(rank.getName());
		}
	}
}