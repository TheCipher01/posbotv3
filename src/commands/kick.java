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
public class kick extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();
        
        if(args[0].equalsIgnoreCase(main.prefix + "kick")){
            if(!member.hasPermission(Permission.KICK_MEMBERS)){
                event.getChannel().sendMessage("You do not have permission to kick users!").queue();
            }else{
                if(args.length <= 1){
                    sendErrorMessage(event.getChannel(), event.getMember());
                    event.getMessage().delete().queue();
                }else{
                    if(args.length >=3){
                        String reason =" ";
                        for(int i = 2; i <args.length; i++){
                            reason += args[i] + " ";
                        }
                        Member target = event.getMessage().getMentionedMembers().get(0);
                        event.getGuild().kick(target).queue();
                        event.getMessage().delete().queue();
                        log(target, event.getMember(), reason, event.getGuild().getTextChannelsByName("staff-log", true).get(0));
                    }
                }
            }
        }
    }
    
    
        public void sendErrorMessage(TextChannel channel, Member member){
        EmbedBuilder error = new EmbedBuilder();
        error.setTitle("Invalid Usage");
        error.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        error.setColor(Color.decode("#EA2027"));
        error.setDescription("{} = Required [] = Optional");
        error.addField("Proper usage: --mute {@user} [reason]", "", false);
        channel.sendMessage(error.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }
        public void log(Member kicked, Member staff, String reason, TextChannel channel){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Kick Report");
        log.setColor(Color.decode("#F79F1F"));
        log.addField("Kicked User", kicked.getAsMention(), false);
        log.addField("Kicked by", staff.getAsMention(), false);
        log.addField("Reason", reason, false);
        log.addField("Date", sdf.format(date), false);
        log.addField("Time", stf.format(date), false);
        channel.sendMessage(log.build()).queue();
    }
}
