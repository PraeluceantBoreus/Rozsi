package io.github.praeluceantboreus.rozsi.main;

import org.bukkit.plugin.java.JavaPlugin;

public class RozsiPlugin extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		registerCommands();
		super.onEnable();
	}
	
	private void registerCommands()
	{
	}
}
