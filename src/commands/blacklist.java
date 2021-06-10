/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class blacklist extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();   
        
        if(args[0].equalsIgnoreCase(main.prefix + "blacklist")){
            if(!member.hasPermission(Permission.ADMINISTRATOR)){
                event.getChannel().sendMessage("You do not have permission to use this command!").queue();
            }else{
                if(args.length < 2){
                    event.getChannel().sendMessage("Please mention the user you want to blacklist!").queue();
                }else{
                    Member target = event.getMessage().getMentionedMembers().get(0);
                    String targetID = target.getId();
                    main.Blacklist.add(targetID);
                    try{
                        PrintWriter out = new PrintWriter(new FileOutputStream(new File("blacklist.txt"), true));
                        out.println(targetID);
                        out.close();
                    }catch (FileNotFoundException ex){
                        Logger.getLogger(blacklist.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    event.getChannel().sendMessage("User Blacklisted!").queue();
                    log(target, event.getMember(), event.getGuild().getTextChannelsByName("blacklist-log", true).get(0));
                    event.getMessage().delete().queue();
                }
            }
        }//end of blacklist
        
        if(args[0].equalsIgnoreCase(main.prefix + "delblacklist")){
            if(!member.hasPermission(Permission.ADMINISTRATOR)){
                event.getChannel().sendMessage("You do not have permission to use this command!").queue();
            }else{
                Member target = event.getMessage().getMentionedMembers().get(0);
                String targetID = target.getId();
                if(!main.Blacklist.contains(targetID)){
                    event.getChannel().sendMessage("That user is not blacklisted!").queue();
                    event.getMessage().delete().queue();
                }else{
                    main.Blacklist.remove(targetID);
                    try{
                        File inputFile = new File("blacklist.txt");
                        File tempFile = new File("tempBlacklist.txt");
                        
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                        
                        String currentLine;
                        
                        while((currentLine = reader.readLine()) != null){
                            String trimmedLine = currentLine.trim();
                            if(trimmedLine.equals(targetID)) continue;
                            writer.write(currentLine + System.getProperty("line.separator"));
                        }
                        writer.close();
                        reader.close();
                        inputFile.delete();
                        tempFile.renameTo(inputFile);
                        
                        System.out.println("Blacklist Updated!");
                        
                    }catch (FileNotFoundException ex){
                        Logger.getLogger(blacklist.class.getName()).log(Level.SEVERE, null, ex);
                    }catch(IOException ex){
                        Logger.getLogger(blacklist.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    event.getChannel().sendMessage("User removed from blacklist!").queue();
                    rlog(target, event.getMember(), event.getGuild().getTextChannelsByName("blacklist-log", true).get(0));
                    event.getMessage().delete().queue();
                }
            }
        }
    }
    
    public void log(Member blacklisted, Member staff, TextChannel channel){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder log = new EmbedBuilder();    
        log.setTitle("Blacklist Report!")
                .setColor(Color.decode("#EA2027"))
                .addField("Blacklisted User: ", blacklisted.getAsMention(), false)
                .addField("Blacklisted By: ", staff.getAsMention(), false);
        channel.sendMessage(log.build()).queue();        
    }

    public void rlog(Member blacklisted, Member staff, TextChannel channel){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder log = new EmbedBuilder();    
        log.setTitle("Unblacklist Report!")
                .setColor(Color.decode("#1dd1a1"))
                .addField("Blacklisted User: ", blacklisted.getAsMention(), false)
                .addField("Blacklisted By: ", staff.getAsMention(), false);
        channel.sendMessage(log.build()).queue();        
    }    
    
}
