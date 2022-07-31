package cz.maku.spigotcontainers.service;

import com.google.common.collect.Maps;
import cz.maku.mommons.worker.annotation.Initialize;
import cz.maku.mommons.worker.annotation.Service;
import cz.maku.spigotcontainers.data.Container;
import cz.maku.spigotcontainers.data.ContaineredPlayer;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContainersService {

    @Getter
    private Map<String, Container> containers;

    @Initialize
    private void init() {
        containers = Maps.newConcurrentMap();
    }

    public Container registerContainer(Container container) {
        return containers.put(container.getId(), container);
    }

    public Container unregisterContainer(String id) {
        return containers.remove(id);
    }

    public Optional<ContaineredPlayer> findPlayerInContainers(Player player) {
        for (Container container : containers.values()) {
            for (ContaineredPlayer containeredPlayer : container.getPlayers()) {
                if (Objects.equals(containeredPlayer.getNickname(), player.getName())) {
                    return Optional.of(containeredPlayer);
                }
            }
        }
        return Optional.empty();
    }
}
