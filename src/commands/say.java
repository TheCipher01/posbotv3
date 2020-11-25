/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class say extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        Member member = event.getMember();
        
        if(member.hasPermission(Permission.ADMINISTRATOR)){
            if(args[0].equalsIgnoreCase(main.prefix + "say")){
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(event.getMessage().getContentRaw().substring(5)).queue();
                event.getMessage().delete().queue();
            }
        }
    }
    
}
