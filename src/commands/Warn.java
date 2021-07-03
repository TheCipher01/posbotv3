/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;
/**
 *
 * @author User
 */
public class Warn extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();
        Member target = event.getMessage().getMentionedMembers().get(0);
        int warningID = 0;
        Statement stat = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch (Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection conn = null;
        try{
            conn=
                    DriverManager.getConnection("jbdc:msql://51.79.71.53" + "user=jmfc&password=13b90988dJMFC!~");
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());            
        }
        
        
        
        if(args[0].equalsIgnoreCase(main.prefix + "warn")){
            if(!member.hasPermission(Permission.KICK_MEMBERS)){
                event.getChannel().sendMessage("You do not have permission to use this command!").queue();
            }else{
                if(args.length < 2){
                    event.getChannel().sendMessage("Please mention the user you want to warn!").queue();
                }else if(args.length <3){
                    event.getChannel().sendMessage("Please provide a reason!").queue();
                }else{
                    String wUser = target.getEffectiveName();
                    String staff = member.getAsMention();
                    int newWarningID = warningID++;
                    String reason = "";
                    for(int i = 2; i < args.length; i++){
                        reason += args[i] + " ";
                    }                    
                    
                    String query = "INSERT INTO posbot WARNINGS ('"+ wUser + "','" + staff + "','" + newWarningID + "','" + reason + "`)";
                    try{
                        stat.execute(query);
                    }catch(SQLException ex){
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: " + ex.getSQLState());
                        System.out.println("VendorError: " + ex.getErrorCode());
                    }
                }
                
            }
        }
        
    }
}
