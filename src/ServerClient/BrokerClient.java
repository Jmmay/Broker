/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

/**
 *
 * @author weyler
 */

import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BrokerClient {
    public static void main(String[] args) throws IOException, ParseException{
        
       String hostName = "127.0.0.1";
       int portNumber = 3000;
       

        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            
           String request = in.readLine();
           
           JSONParser parser = new JSONParser();
           JSONObject jsonRequest = (JSONObject) parser.parse(request);
          
            conection(jsonRequest);
           
           //Conexion Servconection()er 8080
           
           //out.JSONREUQEST
           
           
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
        
      
        
        
        
    }
    
   public static void conection(JSONObject inputLine) throws ParseException {
        String hostName = "127.0.0.1";
        int portNumber = 8080;

        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));) {

            
            out.println(inputLine);
            String jsonResponse = in.readLine();
            
            out.println(jsonResponse);
            reconection();
            
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }

    }
     
    public static void reconection() throws ParseException {
       String hostName = "127.0.0.1";
       int portNumber = 3000;
       

        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            
           String request = in.readLine();
           
            out.println(in.readLine());
            String jsonResponse = in.readLine();
            
            out.println(jsonResponse);
           
           JSONParser parser = new JSONParser();
           JSONObject jsonRequest = (JSONObject) parser.parse(request);
          
            
           
           //Conexion Servconection()er 8080
           
           //out.JSONREUQEST
           
           
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
        
        
    }
    
    
    }
    

