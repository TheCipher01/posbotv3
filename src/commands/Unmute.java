/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class Unmute extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();
        if(args[0].equalsIgnoreCase(main.prefix + "unmute")){
           if(member.hasPermission(Permission.KICK_MEMBERS)){
               if(args.length <= 1){
                   event.getChannel().sendMessage("Please specify who you want to unmute!").queue();
               }else{
                   Role muted = event.getGuild().getRolesByName("Muted", true).get(0);
                   Member target = event.getMessage().getMentionedMembers().get(0);
                   
                   event.getGuild().removeRoleFromMember(target, muted).queue();
                   event.getMessage().delete().queue();
                   log(target, event.getMember(), event.getGuild().getTextChannelsByName("staff-log", true).get(0));
               }
           }else{
               event.getChannel().sendMessage("You cannot use this command!").queue();
           } 
        }
    }
    
    /**
     * 
     * @param unmuted
     * @param staff
     * @param channel 
     */
    public void log(Member unmuted, Member staff, TextChannel channel){
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Unmute Report");
        log.setColor(Color.decode("#009432"));
        log.addField("Unmuted user", unmuted.getAsMention(), false);
        log.addField("Unmuted By: ", staff.getAsMention(), false);
        channel.sendMessage(log.build()).queue();
    }
}
