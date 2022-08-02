package cz.maku.spigotcontainers.service;

import com.google.common.collect.Lists;
import cz.maku.mommons.worker.annotation.BukkitEvent;
import cz.maku.mommons.worker.annotation.Load;
import cz.maku.mommons.worker.annotation.Service;
import cz.maku.spigotcontainers.Containers;
import cz.maku.spigotcontainers.api.AsyncContainerPlayerChatEvent;
import cz.maku.spigotcontainers.api.PlayerJoinContainerEvent;
import cz.maku.spigotcontainers.data.Container;
import cz.maku.spigotcontainers.data.ContaineredPlayer;
import cz.maku.spigotcontainers.data.WorldRestrictedContainer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(listener = true)
public class BukkitListenerService {

    @Load
    private ContainersService containersService;

    @BukkitEvent(value = AsyncPlayerChatEvent.class, priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {
        Optional<ContaineredPlayer> playerInContainers = containersService.findPlayerInContainers(e.getPlayer());
        playerInContainers.ifPresent(containeredPlayer -> {
            Container container = containersService.getContainers().get(containeredPlayer.getCurrentContainer());
            Set<Player> players = container.getPlayers().stream()
                    .map(ContaineredPlayer::getBukkit)
                    .collect(Collectors.toSet());
            Set<Player> recipients = e.getRecipients();
            recipients.removeIf(recipient -> !players.contains(recipient));
            AsyncContainerPlayerChatEvent asyncContainerPlayerChatEvent = new AsyncContainerPlayerChatEvent(true, containeredPlayer, e.getMessage(), recipients, container);
            if (!asyncContainerPlayerChatEvent.isCancelled()) {
                Bukkit.getPluginManager().callEvent(asyncContainerPlayerChatEvent);
            }
        });
    }

    @BukkitEvent(value = PlayerJoinEvent.class, priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        for (Container container : containersService.getContainers().values()) {
            for (Player player : container.getPlayers().stream().map(ContaineredPlayer::getBukkit).collect(Collectors.toList())) {
                player.hidePlayer(Containers.CONTAINERS_PLUGIN, e.getPlayer());
            }
        }
    }

    @BukkitEvent(value = PlayerJoinContainerEvent.class, priority = EventPriority.HIGHEST)
    public void onContainerJoin(PlayerJoinContainerEvent e) {
        List<Player> toHide = Lists.newArrayList();
        toHide.addAll(Bukkit.getOnlinePlayers());
        for (Container container : containersService.getContainers().values()) {
            if (Objects.equals(container.getId(), e.getContainer().getId())) {
                for (Player player : container.getPlayers().stream().map(ContaineredPlayer::getBukkit).collect(Collectors.toList())) {
                    toHide.remove(player);
                    player.showPlayer(Containers.CONTAINERS_PLUGIN, e.getPlayer());
                    e.getPlayer().showPlayer(Containers.CONTAINERS_PLUGIN, player);
                }
            }
        }
        for (Player player : toHide) {
            e.getPlayer().hidePlayer(Containers.CONTAINERS_PLUGIN, player);
        }
    }

    @BukkitEvent(value = PlayerChangedWorldEvent.class, priority = EventPriority.HIGHEST)
    public void onWorldChange(PlayerChangedWorldEvent e) {
        containersService.findPlayerInContainers(e.getPlayer()).ifPresent(containeredPlayer -> {
            if (containersService.getContainers().get(containeredPlayer.getCurrentContainer()) instanceof WorldRestrictedContainer worldRestrictedContainer) {
                Player bukkit = containeredPlayer.getBukkit();
                List<World> restrictedWorlds = worldRestrictedContainer.getRestrictedWorlds();
                if (!restrictedWorlds.contains(bukkit.getWorld())) {
                    if (!restrictedWorlds.contains(e.getFrom())) {
                        bukkit.teleport(restrictedWorlds.get(Containers.RANDOM.nextInt(restrictedWorlds.size())).getSpawnLocation());
                        return;
                    }
                    bukkit.teleport(e.getFrom().getSpawnLocation());
                }
            }
        });
    }
}