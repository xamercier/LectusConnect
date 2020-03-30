package net.lectusConnect.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import net.lectusConnect.MainLectusConnect;

public class EventsManager {
	
	public MainLectusConnect pl;
	
	public EventsManager(MainLectusConnect MainLectusHub) {
		this.pl = MainLectusHub;
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new PlayerEvents(), pl);
		pm.registerEvents(new JoinEvent(), pl);
		pm.registerEvents(new WeatherEvent() , pl);
	}
	
}
