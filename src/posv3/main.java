/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posv3;

import deprecated.AmongUs;
import commands.*;
import help.MainHelp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import justforfun.corrections;
import justforfun.jamie;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import tickets.CloseTicket;
import tickets.ticket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;

/**
 *
 * @author User
 */
public class main {
    public static String prefix = "--";
    public static final List<String> oldBlacklist = Arrays.asList();
    public static final ArrayList<String> Blacklist = new ArrayList<String>();
    public static String t = "";
    
    
    public static void main(String[] args) throws LoginException, FileNotFoundException{
        
        Scanner in = new Scanner(new File("blacklist.txt"));
        while(in.hasNextLine()){
            String blacklist = in.nextLine();
            Blacklist.add(blacklist);
        }
        System.out.println(Blacklist);
        in.close();
        
        Scanner Tin = new Scanner(new File("info.txt"));
        while(Tin.hasNextLine()){
            t = Tin.nextLine();
        }
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch (Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        JDA jda = JDABuilder.createDefault(t, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)
                .setActivity(Activity.playing("Jesse is not a bully!"))
                //.setActivity(Activity.playing("In Development"))
                .addEventListeners(new Info())
                .addEventListeners(new AmongUs())
                .addEventListeners(new corrections())
                .addEventListeners(new kai())
                .addEventListeners(new say())
                .addEventListeners(new Gungame())
                .addEventListeners(new clear())
                .addEventListeners(new Mute())
                .addEventListeners(new Unmute())
                .addEventListeners(new jamie())
                .addEventListeners(new Ban())
                .addEventListeners(new kick())
                .addEventListeners(new Userinfo())
                .addEventListeners(new Serverinfo())
                .addEventListeners(new VcDc())
                .addEventListeners(new MainHelp())
                .addEventListeners(new ticket())
                .addEventListeners(new CloseTicket())
                .addEventListeners(new deafen())
                .addEventListeners(new undeafen())
                .addEventListeners(new blacklist())
                .addEventListeners(new ping())
                .addEventListeners(new Games())
                .addEventListeners(new OGs())
                .addEventListeners(new Warn())
                .setStatus(OnlineStatus.ONLINE)
                //.setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
    }
    
}
