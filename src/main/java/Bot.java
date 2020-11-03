import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) {
        Properties conf = new Properties();

        try (InputStream input = new FileInputStream("config.properties")) {
            conf.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("config.properties not found");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String token = conf.getProperty("token");
        String ip = conf.getProperty("serverIP");
        if (token == null) {
            System.out.println("Token not found in config.properties");
            System.exit(1);
        } else if (ip == null) {
            System.out.println("ServerIP not found in config.properties");
            System.exit(1);
        }

        JDABuilder builder = JDABuilder.createDefault(token);

        builder.addEventListeners(new onLaunch(ip, Boolean.parseBoolean(conf.getProperty("showIP"))));
        JDA jda = null;
        try {
            jda = builder.build();
        } catch (LoginException e) {
            System.out.println("Could not login to Discord > " + e.getMessage());
            System.exit(1);
        }
        Scanner in = new Scanner(System.in);
        while (true) {
            String cmd = in.nextLine();
            if (cmd.equals("stop")) {
                jda.shutdownNow();
                System.exit(0);
            } else System.out.println("Invalid command - use \"stop\" to kill the program");
        }
    }
}
