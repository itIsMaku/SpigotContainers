package cz.maku.spigotcontainers;

import com.google.common.collect.Lists;
import cz.maku.mommons.worker.annotation.Plugin;
import cz.maku.mommons.worker.plugin.WorkerPlugin;
import cz.maku.spigotcontainers.service.BukkitListenerService;
import cz.maku.spigotcontainers.service.ContainersService;

import java.util.List;

@Plugin(
        name = "SpigotContainers",
        description = "Application for seperate server containers",
        main = "cz.maku.spigotcontainers.SpigotContainersApplication",
        apiVersion = "1.19",
        authors = "maku",
        depends = "MommonsLoader"
)
public class SpigotContainersApplication extends WorkerPlugin {

    @Override
    public List<Class<?>> registerServices() {
        return Lists.newArrayList(ContainersService.class, BukkitListenerService.class);
    }

    @Override
    public List<Class<?>> registerSpecialServices() {
        return Lists.newArrayList();
    }

    @Override
    public void onLoad() {}

    @Override
    public void onUnload() {}
}
