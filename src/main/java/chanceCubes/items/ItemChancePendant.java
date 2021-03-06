package chanceCubes.items;

import chanceCubes.config.CCubesSettings;
import net.minecraft.item.ItemStack;

public class ItemChancePendant extends BaseChanceCubesItem
{
	private int chanceIncrease;

	public ItemChancePendant(int tier, int chancebonus)
	{
		super("chance_pendant_tier" + tier);
		this.setMaxStackSize(1);
		this.setMaxDamage(CCubesSettings.pendantUses);
		super.showDurabilityBar(new ItemStack(this));
		chanceIncrease = chancebonus;
		super.addLore("Increases the chance of Chance Cubes by:");
		super.addLore("      +" + chanceIncrease + " when the block is broken");
		super.addLore("Only needs to be in the players inventory to work");
		super.addLore("Note, this is NOT a percentage increase.");
	}

	public int getChanceIncrease()
	{
		return chanceIncrease;
	}

	public void damage(ItemStack stack)
	{
		stack.setItemDamage(stack.getItemDamage() + 1);
	}
}