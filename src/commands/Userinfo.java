/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class Userinfo extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(args[0].equalsIgnoreCase(main.prefix + "userinfo")){
            if(args.length < 1){
                event.getChannel().sendMessage("Please provide a name!").queue();
            }else{
                Member name = null;
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try{
                    name = event.getMessage().getMentionedMembers().get(0);
                    EmbedBuilder eb = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setThumbnail(name.getUser().getAvatarUrl())
                        //.setAuthor("User Information on ", name.getUser().getName(), name.getUser().getAvatarUrl());
                        .setTitle("User Information on " + name.getUser().getName())
                        .setDescription(name.getUser().getName() + " joined on " + name.getTimeJoined().format(fmt))
                        .addField("Status ", name.getOnlineStatus().toString(), true)
                        .addField("Nickname: ", name.getNickname() == null ? "No Nickname" : name.getNickname(), true)
                        .addField("Discord Account Creation Date: ", name.getTimeCreated().format(fmt), true)
                        .addField("Roles: ", getRolesAsString(name.getRoles()), true);
                
                     event.getChannel().sendMessage(eb.build()).queue();
                     event.getMessage().delete().queue();                
                }catch(IndexOutOfBoundsException ex){
                    System.out.println("Exception Occured in Userinfo Command");
                    event.getChannel().sendMessage(event.getMember().getAsMention() + " you need to provide the name as a mention!").queue();
                }
            }
        }
    }
    private String getRolesAsString(List rolesList){
        String roles; 
        if(!rolesList.isEmpty()){
            Role tempRole = (Role) rolesList.get(0);
            roles = tempRole.getName();
            for(int i = 1; i < rolesList.size(); i++){
                tempRole = (Role) rolesList.get(i);
                roles += ", " + tempRole.getName();
            }
        }else{
            roles = "No Roles";
        }
        
        
        return roles;
    }
    
}
