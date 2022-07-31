package cz.maku.spigotcontainers.data;

import cz.maku.spigotcontainers.Containers;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class ContaineredPlayer {

    private final Player bukkit;
    private final String nickname;
    private final String currentContainer;

    public ContaineredPlayer(Player player, String currentContainer) {
        this.bukkit = player;
        this.nickname = player.getName();
        this.currentContainer = currentContainer;
    }

    public boolean leave() {
        Container container = Containers.getContainer(currentContainer);
        return container.leave(this);
    }
}
