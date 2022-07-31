package cz.maku.spigotcontainers.api;

import cz.maku.spigotcontainers.data.Container;
import cz.maku.spigotcontainers.data.ContaineredPlayer;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
public class AsyncContainerPlayerChatEvent extends AsyncPlayerChatEvent {

    private static final HandlerList handlers = new HandlerList();

    private final Container container;
    private final ContaineredPlayer containeredPlayer;

    public AsyncContainerPlayerChatEvent(boolean async, @NotNull ContaineredPlayer containeredPlayer, @NotNull String message, @NotNull Set<Player> players, Container container) {
        super(async, containeredPlayer.getBukkit(), message, players);
        this.containeredPlayer = containeredPlayer;
        this.container = container;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }
}