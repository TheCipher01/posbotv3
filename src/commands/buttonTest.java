/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.Collection;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import posv3.main;

/**
 *
 * @author User
 */
public class buttonTest extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(main.Blacklist.contains(event.getAuthor().getId())) return;
        
        if(args[0].equalsIgnoreCase(main.prefix + "au2")){
            event.getChannel().sendMessage("Click the buttons to either yourself the Among Us Role or to Remove it!")
                    .setActionRow(Button.success("give", "Get the among us role!"))
                    //.setActionRow(Button.danger("remove", "Remove the among us role!"))
                    .queue();
        }
    }
    @Override
    public void onButtonClick(ButtonClickEvent event){
        if(event.getComponentId().equals("give")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("760705745582293013");
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage("You have been added to the Among Us Role!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
            }
//        }else if(event.getComponentId().equals("remove")){
//            Member member = event.getMember();
//            Role role = event.getGuild().getRoleById("760705745582293013");            
//            event.getChannel().sendMessage("You have been removed from the Among Us Role!").queue();
//            event.getGuild().removeRoleFromMember(member, role).complete();
//        }
    }
    
}
