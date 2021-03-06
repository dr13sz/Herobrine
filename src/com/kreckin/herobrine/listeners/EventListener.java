package com.kreckin.herobrine.listeners;

import com.kreckin.herobrine.Herobrine;
import com.kreckin.herobrine.actions.AltarSummon;
import com.kreckin.herobrine.api.CustomEntity;
import com.kreckin.herobrine.util.Util;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
    
    private final Random random = new Random();
    private final List<String> people = Arrays.asList(
        "cadester177",
        "deanfvjr",
        "arksy"
    );
    
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (!event.getCause().equals(BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL)) {
            return;
        }
        Block nether = event.getBlock().getLocation().subtract(0D, 1D, 0D).getBlock();
        Block moss = nether.getLocation().subtract(0D, 1D, 0D).getBlock();
        if (nether.getType().equals(Material.NETHERRACK) && moss.getType().equals(Material.MOSSY_COBBLESTONE)) {
            new AltarSummon().checkAction(event.getPlayer(), new Object[] { nether });
        }
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        CustomEntity entity = Herobrine.getEntityManager().getEntities().get(event.getEntity().getEntityId());
        if (entity != null) {
            entity.onKilled();
            event.setDroppedExp(0);
            event.getDrops().clear();
            if (random.nextInt(Herobrine.getConfigFile().getInt("Herobrine.customItemDropChance")) == 0) {
                event.getDrops().add(entity.getDrop());
            }
            Herobrine.getEntityManager().removeEntity(entity.getEntity().getEntityId());
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (people.contains(event.getPlayer().getName().toLowerCase())) {
            event.getPlayer().sendMessage(Util.formatString("Hey, just wanted to tell you, I " + ChatColor.RED + "<3" + ChatColor.WHITE + " you! :)"));
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (Herobrine.getEntityManager().getEntities().get(event.getEntity().getEntityId()) != null && !event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
            event.setCancelled(true);
            event.setDamage(0);
            if (event.getEntity().getFireTicks() > 0) {
                event.getEntity().setFireTicks(0);
            }
        }
    }
}
