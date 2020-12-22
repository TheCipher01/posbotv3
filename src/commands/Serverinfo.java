/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class Serverinfo extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(args[0].equalsIgnoreCase(main.prefix + "server")){
            Guild server = event.getGuild();
            int members = server.getMemberCount();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            EmbedBuilder SI = new EmbedBuilder()
                    .setColor(Color.ORANGE)
                    .setTitle("Server Information for " + server.getName())
                    .addField("Server Owner: ", "Cipher", true)
                    .addField("Server Region: ", server.getRegionRaw(), true)
                    .addField("Server Creation Date", server.getTimeCreated().format(fmt), true)
                    .addField("Member Count: ", String.valueOf(members), true);
            event.getChannel().sendMessage(SI.build()).queue();
            event.getMessage().delete().queue();
            
        }
    }
    
}
