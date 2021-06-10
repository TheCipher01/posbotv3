/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class undeafen extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();    
        if(main.Blacklist.contains(event.getAuthor().getId())) return;
        
        if(args[0].equalsIgnoreCase(main.prefix + "undeaf")){
            if(args.length >1){
                event.getChannel().sendMessage("You put to many arguments!").queue();
            }else{
                event.getGuild().deafen(member, false).queue();
                event.getGuild().mute(member, false).queue();
                event.getMessage().delete().queue();
            }
        }
    }   
}
    

