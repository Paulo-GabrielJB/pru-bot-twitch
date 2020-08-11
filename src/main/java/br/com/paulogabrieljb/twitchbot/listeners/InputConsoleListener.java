package br.com.paulogabrieljb.twitchbot.listeners;

import com.gikk.twirk.Twirk;

import java.util.Scanner;

public class InputConsoleListener {

    public static void readConsole(Twirk twirk, Scanner scanner){

        String line;
        while (!(line = scanner.nextLine()).matches(".quit"))
            switch (line) {
                case ".add" :
                    commandAdd(line, scanner);
                    break;
                default:
                    twirk.channelMessage(line);
            }

        twirk.close();
        scanner.close();
    }

    private static void commandAdd(String line, Scanner scanner){
        String cm = line.replaceAll(".add", "").trim();

        if(cm.equals("")){
            System.out.print("Enter command type (interact, new): ");
            String str = scanner.nextLine();
            if(str.trim().equals("interact"))
                System.out.println("Comando de interação");

        }

    }

}
