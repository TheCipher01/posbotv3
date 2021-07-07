/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import posv3.main;

/**
 *
 * @author User
 */
public class OGs extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(args[0].equalsIgnoreCase(main.prefix + "ogs")){
            if(main.Blacklist.contains(event.getAuthor().getId())) return;
            
            if(!event.getMember().hasPermission(Permission.ADMINISTRATOR)){
                event.getChannel().sendMessage("You do not have permission to use this command!").queue();
                return;
            }
            //Fornite, Terraria, Valheim & DRG
            event.getChannel().sendMessage("Click the buttons to get or remove the `Fortnite` role")
                    .setActionRow(Button.success("fJoin", "Join Fortnite"), Button.danger("fLeave", "Leave Fortnite"))
                    .queue();
            event.getChannel().sendMessage("Click the buttons to get or remove the `Terraria` role")
                    .setActionRow(Button.success("tJoin", "Join Terraria"), Button.danger("tLeave", "Leave Terraria"))
                    .queue();
            event.getChannel().sendMessage("Click the buttons to get or remove the `Valheim` role")
                    .setActionRow(Button.success("vJoin", "Join Valheim"), Button.danger("vLeave", "Leave Valheim"))
                    .queue();            
            event.getChannel().sendMessage("Click the buttons to get or remove the `DRG` role")
                    .setActionRow(Button.success("dJoin", "Join DRG"), Button.danger("dLeave", "Leave DRG"))
                    .queue();    
            event.getChannel().sendMessage("Click the buttons to get or remove the `Overcooked` role")
                    .setActionRow(Button.success("ocJoin", "Join Overcooked"), Button.danger("ocLeave", "Leave Overcooked"))
                    .queue();
            event.getMessage().delete().queue();
        }
    }
    
    @Override
    public void onButtonClick(ButtonClickEvent event){
        //Fortnite
        if(event.getComponentId().equals("fJoin")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("679832069794562087");
            String name = member.getAsMention();
            
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + ", you have been added to the Fortnite role!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
            }else{
                event.getChannel().sendMessage(name + ", you already have this role!").queue();
            }
        }
        if(event.getComponentId().equals("fLeave")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("679832069794562087");
            String name = member.getAsMention();
            event.getChannel().sendMessage(name + ", you have been removed from the Fornite role!").queue();
            event.getGuild().removeRoleFromMember(member, role).complete();
        }
        //Terraria
        if(event.getComponentId().equals("tJoin")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("852306374556647424");
            String name = member.getAsMention();
            
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + ", you have been added to the Terraria role!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
            }else{
                event.getChannel().sendMessage(name + ", you already have this role!").queue();
            }
        }
        if(event.getComponentId().equals("tLeave")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("852306374556647424");
            String name = member.getAsMention();
            event.getChannel().sendMessage(name + ", you have been removed from the Terraria role!").queue();
            event.getGuild().removeRoleFromMember(member, role).complete();
        }
        //Valheim
        if(event.getComponentId().equals("vJoin")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("818031604999782450");
            String name = member.getAsMention();
            
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + ", you have been added to the Valheim role!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
            }else{
                event.getChannel().sendMessage(name + ", you already have this role!").queue();
            }
        }
        if(event.getComponentId().equals("vLeave")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("818031604999782450");
            String name = member.getAsMention();
            event.getChannel().sendMessage(name + ", you have been removed from the Valheim role!").queue();
            event.getGuild().removeRoleFromMember(member, role).complete();
        }

        //DRG
        if(event.getComponentId().equals("dJoin")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("798105978779992104");
            String name = member.getAsMention();
            
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + ", you have been added to the DRG role! Rock and Stone!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
            }else{
                event.getChannel().sendMessage(name + ", you already have this role!").queue();
            }
        }
        if(event.getComponentId().equals("dLeave")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("798105978779992104");
            String name = member.getAsMention();
            event.getChannel().sendMessage(name + ", you have been removed from the DRG role! You pointy eared leaf lover!").queue();
            event.getGuild().removeRoleFromMember(member, role).complete();
        }     
        
        //overcooked
        if(event.getComponentId().equals("ocJoin")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("862230905979797575");
            String name = member.getAsMention();
            
            if(!member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + ", you have been added to the Overcooked role!").queue();
                event.getGuild().addRoleToMember(member, role).complete();
            }else{
                event.getChannel().sendMessage(name + ", you already have this role!").queue();
            }
        }
        
        if(event.getComponentId().equals("ocLeave")){
            Member member = event.getMember();
            Role role = event.getGuild().getRoleById("862230905979797575");
            String name = member.getAsMention();
            
            if(member.getRoles().contains(role)){
                event.getChannel().sendMessage(name + ", you have been removed from the Overcooked role!").queue();
                event.getGuild().removeRoleFromMember(member, role).complete();
            }else{
                event.getChannel().sendMessage(name + ", you do not have this role!").queue();
            }
        }
    }
    
}
