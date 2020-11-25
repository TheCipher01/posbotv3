/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class kai extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(args[0].equalsIgnoreCase(main.prefix + "kai")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("771893147017543720");
            String name = event.getMember().getAsMention();
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + " You have been added to the notify me role! You will be pinged when Kai goes live!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
            }else{
                event.getChannel().sendMessage(name + " You have been removed from the notify me role! You will no longer be pinged when Kai goes live!").queue();
                event.getGuild().removeRoleFromMember(member, role).complete();                
            }
        }
    }
    
}
