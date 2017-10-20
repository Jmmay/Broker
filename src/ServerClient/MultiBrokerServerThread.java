/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author may
 */
public class MultiBrokerServerThread extends Thread {

    private Socket socket = null;
//    Proxy proxy = new Proxy();

    public MultiBrokerServerThread() {
    }

    public MultiBrokerServerThread(Socket socket) {
        super("MultiBrokerServerThread");
        this.socket = socket;
    }

    @Override
    public void run() {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

            String inputLine;
            JSONObject jsonResponse;

            //Peticion del cliente String format
            inputLine = in.readLine();

            //Peticion del cliente Json Format
            JSONParser parser = new JSONParser();
            JSONObject jsonRequest = (JSONObject) parser.parse(inputLine);

//            verificacion del host
            if (verificationHost(jsonRequest)) {
                out.println(jsonRequest);
                socket.close();

            } else {
                System.out.println("Lo sentimos tu peticion es invalida");
            }

        } catch (IOException | ParseException e) {
            System.out.println("Hubo un error " + e);
        }
    }

    public boolean verificationHost(JSONObject inputLine) throws FileNotFoundException {
        boolean flag = false;
        ArrayList<String> lista = null;
        String palabra;
        lista = leer("log.txt");

        for (int i = 0; i < lista.size(); i++) {
            palabra = lista.get(i);
//           palabrahost =Integer.parseInt(palabra);
            if (inputLine.get("ip").equals(palabra)) {
                flag = true;
                return flag;
            }
        }
        
        return flag;
    }

    public ArrayList<String> leer(String Archivo) throws FileNotFoundException {
        ArrayList<String> lista = new ArrayList<String>();

        Scanner lectura = null;
        String linea;
        lectura = new Scanner(new FileReader(Archivo));

        while (lectura.hasNextLine()) {
            linea = lectura.nextLine();
            lista.add(linea);
        }

        lectura.close();
        System.out.println("archivo leido con exito");

        return lista;
    }

      
}
