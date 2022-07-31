package cz.maku.spigotcontainers.api;

import cz.maku.spigotcontainers.data.Container;
import cz.maku.spigotcontainers.data.ContaineredPlayer;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerLeaveContainerEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    private final Container container;
    private final ContaineredPlayer containeredPlayer;

    public PlayerLeaveContainerEvent(@NotNull ContaineredPlayer containeredPlayer, Container container) {
        super(containeredPlayer.getBukkit());
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
