package br.com.paulogabrieljb.twitchbot.factories;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ScannerFactory {

    private static Scanner scanner = null;

    public static Scanner getScanner() {
        if(scanner == null)
            try {
                scanner = new Scanner(new InputStreamReader(System.in, "UTF-8"));
            } catch (IOException e){
                e.printStackTrace();
            }

        return scanner;
    }
}
