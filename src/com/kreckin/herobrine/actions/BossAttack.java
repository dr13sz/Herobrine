package com.kreckin.herobrine.actions;

import com.kreckin.herobrine.api.Action;
import com.kreckin.herobrine.mobs.FallenAngel;
import com.kreckin.herobrine.mobs.HellsGuardian;
import com.kreckin.herobrine.mobs.ReligiousFollower;
import com.kreckin.herobrine.mobs.UnknownDemon;
import java.util.Random;
import org.bukkit.entity.Player;

public class BossAttack extends Action {
    
    private final Random random = new Random();

    public BossAttack() {
        super(true);
    }

    @Override
    public String callAction(Player player, Object[] metadata) {
        int mobId = random.nextInt(4);
        if (mobId == 0) {
            return ("Spawned: " + new HellsGuardian(player.getLocation()).getEntity().getCustomName());
        } else if (mobId == 1) {
            return ("Spawned: " + new FallenAngel(player.getLocation()).getEntity().getCustomName());
        } else if (mobId == 2) {
            return ("Spawned: " + new ReligiousFollower(player.getLocation()).getEntity().getCustomName());
        } else {
            return ("Spawned: " + new UnknownDemon(player.getLocation()).getEntity().getCustomName());
        }
    }
}
