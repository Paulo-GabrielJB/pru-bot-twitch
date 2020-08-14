package br.com.paulogabrieljb.twitchbot.listeners;

import br.com.paulogabrieljb.twitchbot.configurations.TwirkConfigure;
import br.com.paulogabrieljb.twitchbot.model.UserFactory;
import br.com.paulogabrieljb.twitchbot.services.ServiceFactory;
import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListener;

import java.io.IOException;

public class ListenerFactory {

    public static TwirkListener getMessageListener()  {
        return new MessageListener(TwirkConfigure.getTwirk(), UserFactory.getUser(), ServiceFactory.getInteractService());
    }

    public static TwirkListener getDisconectListener() {
        return new DisconectListener(TwirkConfigure.getTwirk());
    }

    public static InputConsoleListener getInputConsoleListener() {
        return new InputConsoleListener(TwirkConfigure.getTwirk(), ServiceFactory.getInteractService());
    }

}
