/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class Mute extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();
        if(args[0].equalsIgnoreCase(main.prefix + "mute")){
            if(args.length <=1){
                sendErrorMessage(event.getChannel(), event.getMember());
            }else{
                Role muted = event.getGuild().getRolesByName("Muted", true).get(0);
                Member target = event.getMessage().getMentionedMembers().get(0);
                
                event.getGuild().addRoleToMember(target, muted).queue();
               
                if(args.length >=3){
                    String reason = "";
                    for(int i = 2; i < args.length; i++){
                        reason += args[2] + " ";
                    }
                    log(target, event.getMember(), reason, event.getGuild().getTextChannelById("760722277595349012"));
                }else{
                    log(target, event.getMember(), "No reason specified", event.getGuild().getTextChannelById("760722277595349012"));
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
    
    public void log(Member muted, Member muter, String reason, TextChannel channel){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Mute Report");
        log.setColor(Color.decode("#0652DD"));
        log.addField("Muted User", muted.getAsMention(), false);
        log.addField("Muter", muter.getAsMention(), false);
        log.addField("Reason", reason, false);
        log.addField("Date", sdf.format(date), false);
        log.addField("Time", stf.format(date), false);
        channel.sendMessage(log.build()).queue();
    }
    
}
