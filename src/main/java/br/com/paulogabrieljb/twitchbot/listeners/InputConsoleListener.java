package br.com.paulogabrieljb.twitchbot.listeners;

import br.com.paulogabrieljb.twitchbot.db.DatabaseFactory;
import br.com.paulogabrieljb.twitchbot.factories.ScannerFactory;
import br.com.paulogabrieljb.twitchbot.model.Generic;
import br.com.paulogabrieljb.twitchbot.services.InteractService;
import com.gikk.twirk.Twirk;

import java.util.Arrays;
import java.util.Scanner;

public class InputConsoleListener {

    private final Twirk twirk;
    private final InteractService interactService;
    private static final String[] COMMANDS = new String[]{
            ".commands - add commands/configure commands",
            ".quit - disconect"
    };

    public InputConsoleListener(final Twirk twirk, final  InteractService interactService){
        this.twirk = twirk;
        this.interactService = interactService;
    }

    public void readConsole(){

        Scanner scanner = ScannerFactory.getScanner();
        String line;
        while (!(line = scanner.nextLine()).equals(".quit") && twirk != null)
            switch (line) {
                case ".commands" :
                    commandAdd(line, scanner);
                    break;
                case ".help":
                    System.out.println("----List of commands-----");
                    Arrays.stream(COMMANDS).forEach(System.out::println);
                    break;
                default:
                    twirk.channelMessage(line);
            }

        if(twirk != null)
            twirk.close();
        scanner.close();
        DatabaseFactory.close(null, null, DatabaseFactory.getConnection());
    }

    private void commandAdd(String line, Scanner scanner){
        String cm = line.replaceAll(".commands", "").trim();

        if(cm.equals("")){
            System.out.print("Enter command type (interact, new): ");
            String str = scanner.nextLine();
            switch(str.trim()) {
                case "interact" : System.out.println("\n------Interact command------\n");
                    System.out.print("Enter option to messages (add, delete): ");
                    cm = scanner.nextLine().toLowerCase();
                    switch (cm){
                        case "add" :
                            System.out.print("Enter message: ");
                            cm = scanner.nextLine();
                            Generic obj = interactService.insert(new Generic(null, cm, null, null));
                            System.out.println("Message insert, id of message: " + obj.getId());
                            break;
                        case "delete":
                            listInteractMessage();
                            System.out.print("Enter the id of message: ");
                            Long id = scanner.nextLong();
                            scanner.nextLine();
                            interactService.deleteById(id);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                default:
                    System.out.println("Invalid option");

            }
        }

    }

    private void listInteractMessage(){
        interactService.findAll().forEach(im -> {
            System.out.printf("ID: %d, message: %s%n", im.getId(), im.getMessage());
        });
    }

}
