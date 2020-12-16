package me.geo52rey.cpslimiter;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntSupplier;

public class PlayerInteractListener implements Listener {

    private final Map<Player, Map<Action, Set<Timer>>> tracker = new ConcurrentHashMap<>();
    private final IntSupplier getMaxCPS;

    public PlayerInteractListener(IntSupplier getMaxCPS) {
        this.getMaxCPS = getMaxCPS;
    }

    private static TimerTask createTimeout(Set<Timer> timers, Timer timer) {
        return new TimerTask() {
            @Override
            public void run() {
                timers.remove(timer);
            }
        };
    }

    @EventHandler
    public void onPlayerHit(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!tracker.containsKey(p)) {
            tracker.put(p, new ConcurrentHashMap<>());
        }
        Map<Action, Set<Timer>> actionTracker = tracker.get(p);
        Action a = event.getAction();
        if (!actionTracker.containsKey(a)) {
            actionTracker.put(a, ConcurrentHashMap.newKeySet());
        }
        Set<Timer> timers = actionTracker.get(a);
        if (timers.size() >= this.getMaxCPS.getAsInt()) {
            event.setCancelled(true);
        } else {
            Timer t = new Timer();
            TimerTask task = createTimeout(timers, t);
            timers.add(t);
            t.schedule(task, 1000);
        }
    }
}
