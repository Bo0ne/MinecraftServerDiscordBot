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

    public onLaunch(String ip) {
        this.ip = ip;
    }

    @Override
    public void onGuildReady(@Nonnull GuildReadyEvent event) {
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    MinecraftPingReply mcping = new MinecraftPing().getPing("151.67.194.190");
                    int players = mcping.getPlayers().getOnline();
                    int total = mcping.getPlayers().getMax();
                    Presence presence = event.getJDA().getPresence();
                    presence.setStatus(OnlineStatus.ONLINE);
                    presence.setActivity(Activity.playing("with " + players + " out of " + total));
                } catch (IOException e) {
                    event.getJDA().getPresence().setStatus(OnlineStatus.OFFLINE);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }
}
