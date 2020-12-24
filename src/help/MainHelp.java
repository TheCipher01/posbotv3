/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class MainHelp extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(args[0].equalsIgnoreCase(main.prefix + "help")){
           EmbedBuilder help = new EmbedBuilder();
           help.setTitle("PoS Bot Help")
                   .setDescription("List of commands I know. My prefix is --")
                   .setColor(Color.decode("#4834d4"))
                   .addField("au", "Will add you to the Among Us role. If you already have the role you will be removed.", true)
                   .addField("ban", "Admin Only. Bans a user from the guild", true)
                   .addField("gungame", "Display a list of fortnite gun game maps", true)
                   .addField("info", "Gives very basic information about the bot.", true)
                   .addField("Mute", "Staff only - Mutes a user", true)
                   .addField("server", "Get information about the server", true)
                   .addField("unmute", "Staff Only - Unmutes a muted user", true)
                   .addField("userinfo", "Gives Information about the mentioned user", true)
                   .addField("dc", "Staff Only - Disconnects a user from a voice channel", true)
                   .addField("clear", "Staff Only - Clears a certain amount of messages in a channel", true)
                   .addField("kai", "Get added to the notifyme role for when Kai goes live and uploads a video", true)
                   .addField("kick", "Staff Only - Kicks a user from the guild", true)
                   .addField("say", "Admin Only - Sends a message as the bot", true)
                   .addField("help", "Show this embed", true)
                   .setFooter("PoS Clan Bot Created by Cipher");
           event.getChannel().sendMessage(help.build()).queue();
           event.getMessage().delete().queue(); 
                 
        }
    }
    
}
