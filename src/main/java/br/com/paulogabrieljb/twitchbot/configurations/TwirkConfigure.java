package br.com.paulogabrieljb.twitchbot.configurations;

import br.com.paulogabrieljb.twitchbot.listeners.DisconectListener;
import br.com.paulogabrieljb.twitchbot.listeners.MessageListener;
import br.com.paulogabrieljb.twitchbot.model.User;
import br.com.paulogabrieljb.twitchbot.repositories.MessageRepository;
import com.gikk.twirk.Twirk;
import com.gikk.twirk.TwirkBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

public class TwirkConfigure {

    private static final Logger LOG = Logger.getLogger(DatabaseConfig.class);

    public static Twirk configureTwirk(String channel, User user) throws IOException {

        LOG.info("Creating twirk...");

        Twirk twirk = new TwirkBuilder(channel, user.getUsername(), user.getToken())
                .setVerboseMode(false)
                .build();

        LOG.info("Adding listeners to twirk...");
        twirk.addIrcListener(new DisconectListener(twirk));

        if(user.getInteract()) {
            LOG.info("Chat interaction activated, adding message listener");
            twirk.addIrcListener(new MessageListener(twirk, user, new MessageRepository(DatabaseConfig.getDatabase())));
        }

        LOG.info("Connecting twirk...");

        try {
            twirk.connect();
        } catch (InterruptedException e){
            LOG.error("Error to connect to twitch!");
            twirk = null;
        }

        return twirk;

    }
}
