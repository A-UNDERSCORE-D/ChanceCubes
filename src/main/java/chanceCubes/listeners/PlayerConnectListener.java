package chanceCubes.listeners;

import chanceCubes.CCubesCore;
import chanceCubes.profiles.GlobalProfileManager;
import chanceCubes.registry.global.GlobalCCRewardRegistry;
import chanceCubes.rewards.defaultRewards.CustomUserReward;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class PlayerConnectListener
{
	@SubscribeEvent
	public void onPlayerLogin(final PlayerLoggedInEvent event)
	{
		if(event.player.world.isRemote)
			return;

		GlobalProfileManager.loadPlayerProfile(event.player.getUniqueID().toString());
		new Thread(() -> CustomUserReward.getCustomUserReward(event.player.getUniqueID())).start();
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent event)
	{
		if(event.player.world.isRemote)
			return;

		String rewardName = CCubesCore.MODID + ":CR_" + event.player.getCommandSenderEntity().getName();
		if(GlobalCCRewardRegistry.DEFAULT.isRewardEnabled(rewardName))
			GlobalCCRewardRegistry.DEFAULT.unregisterReward(rewardName);

		String playerUUID = event.player.getUniqueID().toString();
		GlobalCCRewardRegistry.DEFAULT.removePlayerRewards(playerUUID);
		GlobalCCRewardRegistry.GIANT.removePlayerRewards(playerUUID);
		GlobalProfileManager.removePlayerProfile(playerUUID);
	}
}