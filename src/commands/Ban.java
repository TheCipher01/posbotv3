/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class Ban extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();
        
        if(args[0].equalsIgnoreCase(main.prefix + "ban")){
            if(!member.hasPermission(Permission.BAN_MEMBERS)){
                event.getChannel().sendMessage("You do not have permission to ban people in this server!").queue();
            }else{
                if(args.length <=1){
                    sendErrorMessage(event.getChannel(), event.getMember());
                    event.getMessage().delete().queue();
                }else{
                    Member target = event.getMessage().getMentionedMembers().get(0);
                    event.getGuild().ban(target, 0).queue();
                    event.getMessage().delete().queue();
                    
                    if(args.length >= 3){
                        String reason = "";
                        for(int i = 2; i < args.length; i++){
                            reason = args[2] + " ";
                        }
                        log(target, event.getMember(), reason, event.getGuild().getTextChannelsByName("staff-log", true).get(0));
                    }else{
                        log(target, event.getMember(), "No reason provided", event.getGuild().getTextChannelsByName("staff-log", true).get(0));
                    }
                }
            }
        }
        
    }
    
    
        /**
         * Banned user, staff member who banned the user, the reason and log channel.
         * @param banned 
         * @param staff 
         * @param reason
         * @param channel 
         */
        public void log(Member banned, Member staff, String reason, TextChannel channel){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Ban Report");
        log.setColor(Color.decode("#EA2027"));
        log.addField("Banned User", banned.getAsMention(), false);
        log.addField("Banned by", staff.getAsMention(), false);
        log.addField("Reason", reason, false);
        log.addField("Date", sdf.format(date), false);
        log.addField("Time", stf.format(date), false);
        channel.sendMessage(log.build()).queue();
    }
        
    /**
     * Gives an error message in the channel if the command is run incorrectly.
     * @param channel
     * @param member 
     */    
    public void sendErrorMessage(TextChannel channel, Member member){
        EmbedBuilder error = new EmbedBuilder();
        error.setTitle("Invalid Usage");
        error.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        error.setColor(Color.decode("#EA2027"));
        error.setDescription("{} = Required [] = Optional");
        error.addField("Proper usage: --ban {@user} [reason]", "", false);
        channel.sendMessage(error.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }        
    
}
