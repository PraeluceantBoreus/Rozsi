package io.github.praeluceantboreus.rozsi.listener;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MarkListener implements Listener
{
	private HashMap<Player, Location> left, right;

	public MarkListener()
	{
		left = new HashMap<>();
		right = new HashMap<>();
	}

	@EventHandler
	public void onMark(PlayerInteractEvent pie)
	{
		if (pie.getAction().equals(Action.RIGHT_CLICK_BLOCK) || pie.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			ItemStack item = pie.getPlayer().getItemInHand();
			if (item != null && item.getType().equals(Material.BONE))
			{
				Player player = pie.getPlayer();
				Location loc = pie.getClickedBlock().getLocation();
				if (pie.getAction().equals(Action.RIGHT_CLICK_BLOCK))
					right.put(player, loc);
				else
					left.put(player, loc);
				player.sendMessage(ChatColor.RED.toString() + pie.getAction() + " on " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ());
			}
		}
	}

	public Location getLeft(Player player)
	{
		return left.get(player);
	}

	public Location getRight(Player player)
	{
		return right.get(player);
	}
}
