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
public class AmongUs extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(main.Blacklist.contains(event.getAuthor().getId())) return;
        if(args[0].equalsIgnoreCase(main.prefix + "au")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("754496624700686346");
            String name = event.getMember().getAsMention();
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage("You have been added to the Among Us Role! " + name + ".").queue();
                event.getGuild().addRoleToMember(member, role).complete();   
                event.getMessage()
                        .delete().queue();
            }else{
                event.getChannel().sendMessage("You have been removed from the Among Us Role! " + name + ".").queue();
                event.getGuild().removeRoleFromMember(member, role).complete();                    
                event.getMessage()
                        .delete().queue();
            }
            
        }
    }
    
}
