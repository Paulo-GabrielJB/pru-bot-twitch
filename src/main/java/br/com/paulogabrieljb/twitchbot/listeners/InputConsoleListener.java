package br.com.paulogabrieljb.twitchbot.listeners;

import com.gikk.twirk.Twirk;

import java.util.Scanner;

public class InputConsoleListener {

    public static void readConsole(Twirk twirk, Scanner scanner){

        String line;
        while (!(line = scanner.nextLine()).matches(".quit"))
            twirk.channelMessage(line);


        twirk.close();

        scanner.close();
    }

}
