package com.terraboxstudios.instantreplay.util;

import com.terraboxstudios.instantreplay.InstantReplay;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Utils {

	public static Location stringToLocation(String str) {
		String[] strArr = str.split(":");
		Location loc = new Location(Bukkit.getServer().getWorld(strArr[0]), 0, 0, 0);
		loc.setX(Double.parseDouble(strArr[1]));
		loc.setY(Double.parseDouble(strArr[2]));
		loc.setZ(Double.parseDouble(strArr[3]));
		loc.setYaw(Float.parseFloat(strArr[4]));
		loc.setPitch(Float.parseFloat(strArr[5]));
		return loc;
	}

	public static String locationToString(Location loc) {
		return Objects.requireNonNull(loc.getWorld()).getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ() + ":" + loc.getYaw() + ":" + loc.getPitch();
	}

	public static String preciseLocationToString(Location loc) {
		return Objects.requireNonNull(loc.getWorld()).getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch();
	}

	public static String getReplayPrefix() {
		return Config.getConfig().getBoolean("settings.use-plugin-prefix") ? Config.readColouredString("plugin-prefix") : "";
	}

	public static void sendTimestampMessage(Player player, long timestamp) {
		TextComponent component = new TextComponent(TextComponent.fromLegacyText(Utils.getReplayPrefix() + Config.readColouredString("replay-timestamp-output").replace("{TIMESTAMP}", timestamp + "")));
		component.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, timestamp + ""));
		player.spigot().sendMessage(component);
	}

	public static void runOnMainThread(Runnable runnable) {
		Bukkit.getScheduler().runTask(InstantReplay.getPlugin(InstantReplay.class), runnable);
	}

	public static boolean isLocationInReplay(Location locationOne, Location locationTwo, int radius) {
		if (locationOne.getWorld() == null || locationTwo.getWorld() == null) return false;
		return (locationOne.getBlockX() >= locationTwo.getBlockX() - radius && locationOne.getBlockX() <= locationTwo.getBlockX() + radius)
				&& (locationOne.getBlockZ() >= locationTwo.getBlockZ() - radius && locationOne.getBlockZ() <= locationTwo.getBlockZ() + radius)
				&& locationOne.getWorld().getName().equals(locationTwo.getWorld().getName());

	}
	
}
