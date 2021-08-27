package com.terraboxstudios.instantreplay.containers;

import com.terraboxstudios.instantreplay.services.EventLoggingService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;
import java.util.UUID;

@EqualsAndHashCode
public abstract class EventContainer {

    private final String world;
    private final double x, y, z;
    @Getter
    private final UUID uuid;
    @Getter
    @Setter
    private long time;

    public EventContainer(UUID uuid, Location location, long time) {
        this.uuid = uuid;
        this.world = Objects.requireNonNull(location.getWorld()).getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        setTime(time);
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public World getWorld() {
        return Objects.requireNonNull(Bukkit.getWorld(world));
    }

    public void log() {
        EventLoggingService.getInstance().logEvent(this);
    }


}
