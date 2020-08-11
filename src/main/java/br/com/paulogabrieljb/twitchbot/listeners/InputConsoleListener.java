package br.com.paulogabrieljb.twitchbot.listeners;

import br.com.paulogabrieljb.twitchbot.factories.ScannerFactory;
import com.gikk.twirk.Twirk;

import java.util.Scanner;

public class InputConsoleListener {

    public static void readConsole(Twirk twirk){

        Scanner scanner = ScannerFactory.getScanner();
        String line;
        while (!(line = scanner.nextLine()).equals(".quit") && twirk != null)
            switch (line) {
                case ".add" :
                    commandAdd(line, scanner);
                    break;
                default:
                    twirk.channelMessage(line);
            }

        if(twirk != null)
            twirk.close();
        scanner.close();
    }

    private static void commandAdd(String line, Scanner scanner){
        String cm = line.replaceAll(".add", "").trim();

        if(cm.equals("")){
            System.out.print("Enter command type (interact, new): ");
            String str = scanner.nextLine();
            switch(str.trim()) {
                case "interact" : System.out.println("Comando de interação");
                    break;
                default:
                    System.out.println("Invalid option");

            }
        }

    }

}
