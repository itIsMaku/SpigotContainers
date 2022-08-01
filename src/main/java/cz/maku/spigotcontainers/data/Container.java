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
public class Container {

    @Getter
    private final String id;
    private final List<ContaineredPlayer> players;

    public boolean join(Player player) {
        ContaineredPlayer containeredPlayer = new ContaineredPlayer(player, id);
        PlayerJoinContainerEvent playerJoinContainerEvent = new PlayerJoinContainerEvent(containeredPlayer, this);
        Bukkit.getPluginManager().callEvent(playerJoinContainerEvent);
        return players.add(containeredPlayer);
    }

    public boolean leave(ContaineredPlayer containeredPlayer) {
        PlayerLeaveContainerEvent playerLeaveContainerEvent = new PlayerLeaveContainerEvent(containeredPlayer, this);
        Bukkit.getPluginManager().callEvent(playerLeaveContainerEvent);
        return players.remove(containeredPlayer);
    }

    public ImmutableList<ContaineredPlayers> getPlayers() {
        return ImmutableList.copyOf(players);
    }

}
