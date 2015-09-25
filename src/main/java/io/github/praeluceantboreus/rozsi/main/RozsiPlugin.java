package io.github.praeluceantboreus.rozsi.main;

import io.github.praeluceantboreus.rozsi.listener.MarkListener;
import io.github.praeluceantboreus.rozsi.writer.CubSerializer;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RozsiPlugin extends JavaPlugin
{
	private MarkListener marking;

	@Override
	public void onEnable()
	{
		registerCommands();
		initListeners();
		super.onEnable();
	}

	private void registerCommands()
	{
	}

	private void initListeners()
	{
		marking = new MarkListener();
		getServer().getPluginManager().registerEvents(marking, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (label.equalsIgnoreCase("savecons"))
		{
			if (!(sender instanceof Player))
				return false;
			if (args.length >= 1)
			{
				File file = new File(args[0]);
				CubSerializer cs = new CubSerializer();
				cs.writeLocs(marking.getLeft((Player) sender), marking.getRight((Player) sender), file);
				sender.sendMessage(ChatColor.GREEN + "Saved...");
				return true;
			}
		}
		return super.onCommand(sender, command, label, args);
	}
}
