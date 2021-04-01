package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class deafen extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();    
        
        if(args[0].equalsIgnoreCase(main.prefix + "deaf")){
            if(args.length >1){
                event.getChannel().sendMessage("You put to many arguments!").queue();
            }else{
                event.getGuild().deafen(member, true).queue();
                event.getGuild().mute(member, true).queue();
                event.getMessage().delete().queue();
            }
        }
    }   
}
