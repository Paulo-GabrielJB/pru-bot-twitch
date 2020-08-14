package br.com.paulogabrieljb.twitchbot.configurations;

import br.com.paulogabrieljb.twitchbot.db.DatabaseFactory;
import br.com.paulogabrieljb.twitchbot.listeners.DisconectListener;
import br.com.paulogabrieljb.twitchbot.listeners.ListenerFactory;
import br.com.paulogabrieljb.twitchbot.listeners.MessageListener;
import br.com.paulogabrieljb.twitchbot.model.User;
import br.com.paulogabrieljb.twitchbot.dao.impl.InteractDaoImpl;
import br.com.paulogabrieljb.twitchbot.model.UserFactory;
import com.gikk.twirk.Twirk;
import com.gikk.twirk.TwirkBuilder;
import org.apache.log4j.Logger;
import org.sqlite.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TwirkConfigure {

    private static Twirk twirk = null;
    private static final Logger LOG = Logger.getLogger(DatabaseFactory.class);

    public static Twirk getTwirk() {

        LOG.info("Creating twirk...");

        if(twirk == null) {
            try {
                System.out.print("Enter the channel to enter: ");
                Scanner scanner = new Scanner(new InputStreamReader(System.in, "UTF-8"));
                String channel = "#" + scanner.nextLine();
                User user = UserFactory.getUser();

                twirk = new TwirkBuilder(channel, user.getUsername(), user.getToken())
                        .setVerboseMode(false)
                        .build();

                LOG.info("Adding listeners to twirk...");
                twirk.addIrcListener(ListenerFactory.getDisconectListener());

                if (user.getInteract()) {
                    LOG.info("Chat interaction activated, adding message listener");
                    twirk.addIrcListener(ListenerFactory.getMessageListener());
                }

                LOG.info("Connecting twirk...");

                twirk.connect();

            } catch (InterruptedException e){
                LOG.error("Error to connect to twitch!");
                twirk = null;
            } catch (IOException e){
                LOG.error(String.format("Error %s", e.getMessage().toLowerCase()), e);
            }
        }
        return twirk;

    }

}
