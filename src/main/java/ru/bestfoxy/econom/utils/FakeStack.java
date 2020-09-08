package ru.bestfoxy.econom.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTException;

public class FakeStack {
	
	private final String item;
	private final short metadata;
	private final int count;
	private final String nbt;
	private ItemStack cacheStack;
	
	public FakeStack(String i, short m, int c) {
		this(i, m, c, null);
	}
	
	public FakeStack(String i, short m, int c, String nbtTag) {
		item = i;
		metadata = m;
		count = c;
		nbt = nbtTag;
	}
	
	public String getItem() {
		return item;
	}

	public short getMetadata() {
		return metadata;
	}

	public int getCount() {
		return count;
	}

	public net.minecraft.nbt.NBTTagCompound getTagCompound() {
		if ((nbt == null) || (nbt.isEmpty())) {
			return null;
		}
		try {
			return (net.minecraft.nbt.NBTTagCompound)net.minecraft.nbt.JsonToNBT.func_150315_a(nbt);
		} catch (NBTException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	



	public String getNbtString() {
		return nbt;
	}
	
	public ItemStack toItemStack() {
		if (cacheStack == null) {
			ItemStack itemStack = new ItemStack(getItemByText(item), count, metadata);
			itemStack.setTagCompound(getTagCompound());
			cacheStack = itemStack;
		}
		return cacheStack;
	}
	
	public static Item getItemByText(String id) {
		Item item = (Item)Item.itemRegistry.getObject(id);
		
		if (item == null) {
			try {
				item = Item.getItemById(Integer.parseInt(id));
			}
			catch (NumberFormatException localNumberFormatException) {}
		}
		return item;
	}
}
