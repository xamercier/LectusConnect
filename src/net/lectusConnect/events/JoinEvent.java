package net.lectusConnect.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;

import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;
import net.lectusAPI.utils.BukkitPermissionsUtils;
import net.lectusAPI.utils.SendPlayerViaBungee;
import net.lectusAPI.utils.TeamUtils;

public class JoinEvent implements Listener {

	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		Rank playerRank = PlayerCache.getCacheByPlayer(p).getRank();
		if (playerRank == Rank.ADMIN || playerRank == Rank.BUILDEUR || playerRank == Rank.OWNER) {
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.difficulty");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.effect");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.gamerule");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.give");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.setblock");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.fill");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.setworldspawn");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.spawnpoint");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.time");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.weather");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.xp");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "worldedit.*.");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "worldedit.*");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "multiverse.*.");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "multiverse.*");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "voxelsniper.sniper");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "voxelsniper.ignorelimitations");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "voxelsniper.goto");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "voxelsniper.brush.*");
		}
		p.setGameMode(GameMode.CREATIVE);
		p.teleport(Bukkit.getWorld("world").getSpawnLocation());
		SendPlayerViaBungee.sendPlayer(p, "hub");
	}

	@EventHandler
	public void quit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		for (PermissionAttachmentInfo attachmentInfo : p.getEffectivePermissions()) {
			try {
				attachmentInfo.getAttachment().unsetPermission(attachmentInfo.getPermission());
			} catch (Exception ex) {
				p.sendMessage("FATAL ERROR: Could not remove permission: " + attachmentInfo.getPermission());
			}
		}
		// SCOREBOARD and team
		TeamUtils.getInstance().removePlayerOfTeam(p, PlayerCache.getCacheByPlayer(p).getRank().getName());
		// SCOREBOARD and team
		e.setQuitMessage(null);
	}

}
