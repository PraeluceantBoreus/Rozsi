package io.github.praeluceantboreus.rozsi.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class CubSerializer
{
	private int lastMat;
	private int aircount;

	public CubSerializer()
	{
		lastMat = -1;
		aircount = 0;
	}

	public void writeLocs(Location loc1, Location loc2, File file)
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
	private byte[] writeBlock(Block block)
	{
		int id = block.getTypeId();
		byte[] ret = new byte[2];
		if (id == Material.AIR.getId())
		{
			if (lastMat != id)
				id = 0xA00B;
			else
				aircount++;
		}
		ret[0] = (byte) (id % 256);
		ret[1] = (byte) (id / 256);
		if (id != Material.AIR.getId() && lastMat == Material.AIR.getId())
		{
			byte[] numberOfAir = horner(aircount, 0);
			byte[] endFlag = horner(0xA00E, 0);
			
			aircount = 0;
		}
		lastMat = id;
		return ret;
	}

	private byte[] horner(int number, int minLength)
	{
		int length = (number / 0x100) + 1;
		length = (length < minLength) ? minLength : length;
		byte[] ret = new byte[length];
		for (int i = 0; i < ret.length; i++)
		{
			int tempNumber = number % 0x100;
			tempNumber -= (tempNumber >= 0x80) ? 0x100 : 0;
			ret[ret.length - 1 - i] = (byte) tempNumber;
		}
		return ret;
	}
}
