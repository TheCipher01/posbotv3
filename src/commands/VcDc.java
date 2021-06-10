/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.awt.Color;
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
public class VcDc extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();
        
        if(main.Blacklist.contains(event.getAuthor().getId())) return;
        if(args[0].equalsIgnoreCase(main.prefix + "dc")){
            if(!member.hasPermission(Permission.KICK_MEMBERS)){
                event.getChannel().sendMessage("You do not have permission!").queue();
            }else{
                if(args.length <=1){
                    sendErrorMessage(event.getChannel(), event.getMember());
                    event.getMessage()
                            .delete().queue();
                }else{
                    Member target = event.getMessage().getMentionedMembers().get(0);
                    if(!target.getVoiceState().inVoiceChannel()){
                        event.getChannel().sendMessage("That user is not in a voice chat!").queue();
                        event.getMessage()
                                .delete().queue();
                    }else{
                        event.getGuild().kickVoiceMember(target).queue();
                        event.getMessage()
                                .delete().queue();
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
        error.addField("Proper usage: --dc {@user}", "", false);
        error.addField("Disconnects a user from a voice channel.", "", false);
        channel.sendMessage(error.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }   
    
}
