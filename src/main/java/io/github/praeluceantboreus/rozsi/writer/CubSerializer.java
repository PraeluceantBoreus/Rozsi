package io.github.praeluceantboreus.rozsi.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class CubSerializer
{
	public static void writeLocs(Location loc1, Location loc2, File file)
	{
		try (FileOutputStream fos = new FileOutputStream(file))
		{
			for (int x = loc1.getBlockX(); (loc1.getBlockX() < loc2.getBlockX() == x <= loc2.getBlockX()); x += (loc1.getBlockX() < loc2.getBlockX()) ? 1 : -1)
			{
				for (int y = loc1.getBlockY(); (loc1.getBlockY() < loc2.getBlockY() == y <= loc2.getBlockY()); y += (loc1.getBlockY() < loc2.getBlockY()) ? 1 : -1)
				{
					for (int z = loc1.getBlockZ(); (loc1.getBlockZ() < loc2.getBlockZ() == z <= loc2.getBlockZ()); z += (loc1.getBlockZ() < loc2.getBlockZ()) ? 1 : -1)
					{
						fos.write(writeBlock(new Location(loc1.getWorld(), x, y, z).getBlock()));
					}
				}
			}
			fos.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static byte[] writeBlock(Block block)
	{
		byte[] ret = new byte[8];
		ret[0] = (byte) block.getTypeId();
		return ret;
	}
}
