import mcping.MinecraftPing;
import mcping.MinecraftPingReply;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.Presence;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class onLaunch extends ListenerAdapter {
    final String ip;
    final boolean showIP;

    public onLaunch(String ip, boolean showIP) {
        this.ip = ip;
        this.showIP = showIP;
    }

    @Override
    public void onGuildReady(@Nonnull GuildReadyEvent event) {
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    MinecraftPingReply mcping = new MinecraftPing().getPing(ip);
                    int players = mcping.getPlayers().getOnline();
                    int total = mcping.getPlayers().getMax();
                    Presence presence = event.getJDA().getPresence();
                    presence.setStatus(OnlineStatus.ONLINE);
                    presence.setActivity(Activity.playing("with " + players + " out of " + total));
                    if (showIP) {
                        Thread.sleep(5000);
                        presence.setActivity(Activity.playing("at " + ip));
                    }
                } catch (IOException e) {
                    event.getJDA().getPresence().setStatus(OnlineStatus.OFFLINE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10000);
    }
}
