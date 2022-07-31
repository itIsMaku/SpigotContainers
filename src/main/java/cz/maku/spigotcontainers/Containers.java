package cz.maku.spigotcontainers;

import com.google.common.collect.Lists;
import cz.maku.mommons.worker.WorkerReceiver;
import cz.maku.spigotcontainers.data.Container;
import cz.maku.spigotcontainers.data.ContaineredPlayer;
import cz.maku.spigotcontainers.service.ContainersService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public final class Containers {

    public static Plugin CONTAINERS_PLUGIN = Bukkit.getPluginManager().getPlugin("SpigotContainers");
    public static Random RANDOM = new Random();

    private static ContainersService service() {
        ContainersService service = WorkerReceiver.getService(SpigotContainersApplication.class, ContainersService.class);
        if (service == null) {
            throw new RuntimeException("ContainersService service was not found.");
        }
        return service;
    }

    public static Container getContainer(String id) {
        return service().getContainers().get(id);
    }

    public static Container createContainer(String id, List<Player> players) {
        List<ContaineredPlayer> containeredPlayers = Lists.newArrayList();
        for (Player player : players) {
            containeredPlayers.add(new ContaineredPlayer(player, id));
        }
        Container container = new Container(id, containeredPlayers);
        return createContainer(container);
    }

    public static Container createContainer(Container container) {
        ContainersService containersService = service();
        containersService.registerContainer(container);
        return container;
    }

    public static Optional<ContaineredPlayer> findPlayerInContainers(Player player) {
        return service().findPlayerInContainers(player);
    }
}
