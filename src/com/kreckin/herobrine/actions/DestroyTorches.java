package com.kreckin.herobrine.actions;

import com.kreckin.herobrine.api.Action;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DestroyTorches extends Action {

    public DestroyTorches() {
        super(true);
    }
    
    @Override
    public String callAction(Player player, Object[] metadata) {
        int torchTotal = 0;
        for (int x = -5; x < 5; x++) {
            for (int z = -5; z < 5; z++) {
                for (int y = -5; y < 5; y++) {
                    Block block = player.getLocation().add(x, y, z).getBlock();
                    if (block.getType().equals(Material.TORCH)) {
                        block.setType(Material.AIR);
                        block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.TORCH));
                        torchTotal++;
                    }
                }
            }
        }
        return ("Destroyed " + torchTotal + " torches.");
    }
}
