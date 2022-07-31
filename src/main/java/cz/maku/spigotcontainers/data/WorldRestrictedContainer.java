package cz.maku.spigotcontainers.data;

import lombok.Getter;
import org.bukkit.World;

import java.util.List;

@Getter
public class WorldRestrictedContainer extends Container {

    private final List<World> restrictedWorlds;

    public WorldRestrictedContainer(String id, List<ContaineredPlayer> players, List<World> restrictedWorlds) {
        super(id, players);
        this.restrictedWorlds = restrictedWorlds;
    }


}
