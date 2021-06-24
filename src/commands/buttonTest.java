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
        
        if(args[0].equalsIgnoreCase(main.prefix + "games")){
            event.getChannel().sendMessage("Click the buttons to either yourself the Among Us Role or to Remove it!")
                    .setActionRow(Button.success("give", "Get the Among Us Role!"), Button.danger("remove", "Remove the Among Us Role!"))
                    .queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Click the buttons to get or remove the `Overwatch` role!")
                    .setActionRow(Button.success("gow", "Join Overwatch"), Button.danger("rOW", "Leave Overwatch!"))
                    .queue();
        }
    }
    @Override
    public void onButtonClick(ButtonClickEvent event){
        if(event.getComponentId().equals("give")){
            if(main.Blacklist.contains(event.getMember().getId())){
                return;
            }            
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("754496624700686346");
            String name = member.getAsMention();
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + ", You have been added to the Among Us Role!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
                //event.getMessage().delete().queue();
            }else{
                event.getChannel().sendMessage(name + ", you already have this role!").queue();
            }
        }else if(event.getComponentId().equals("remove")){
            if(main.Blacklist.contains(event.getMember().getId())){
                return;
            }
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("754496624700686346");
            String name = member.getAsMention();
            event.getChannel().sendMessage(name + ", You have been removed from the Among Us Role!").queue();
            event.getGuild().removeRoleFromMember(member, role).complete();
            //event.getMessage().delete().queue();
        }else if(event.getComponentId().equals("gow")){
            if(main.Blacklist.contains(event.getMember().getId())){
                return;
            }
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("782816295556874270");
            String name = member.getAsMention();
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + ", you have been added to the Overwatch role!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
            }else{
                event.getChannel().sendMessage(name + ", you already have this role!").queue();
            }
        }else if(event.getComponentId().equals("rOW")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("782816295556874270");
            String name = member.getAsMention();
            event.getChannel().sendMessage(name + ", you have been removed from the Overwatch role!").queue();
            event.getGuild().removeRoleFromMember(member, role).complete();
        }
    }
    
}
