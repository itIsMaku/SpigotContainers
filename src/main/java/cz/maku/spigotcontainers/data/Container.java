package cz.maku.spigotcontainers.data;

import cz.maku.mommons.worker.annotation.BukkitEvent;
import cz.maku.spigotcontainers.api.PlayerJoinContainerEvent;
import cz.maku.spigotcontainers.api.PlayerLeaveContainerEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Container {

    private final String id;
    private final List<ContaineredPlayer> players;

    public boolean join(ContaineredPlayer containeredPlayer) {
        PlayerJoinContainerEvent playerJoinContainerEvent = new PlayerJoinContainerEvent(containeredPlayer, this);
        Bukkit.getPluginManager().callEvent(playerJoinContainerEvent);
        return players.add(containeredPlayer);
    }

    public boolean leave(ContaineredPlayer containeredPlayer) {
        PlayerLeaveContainerEvent playerLeaveContainerEvent = new PlayerLeaveContainerEvent(containeredPlayer, this);
        Bukkit.getPluginManager().callEvent(playerLeaveContainerEvent);
        return players.remove(containeredPlayer);
    }
}
