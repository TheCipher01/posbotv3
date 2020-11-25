/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.List;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
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
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();        
        Member target = event.getMessage().getMentionedMembers().get(0);
        
        if(args[0].equalsIgnoreCase(main.prefix + "kick")){
            String name = member.getAsMention();
            if(!member.hasPermission(Permission.KICK_MEMBERS)){
                channel.sendMessage(name + " You do not have permission to kick people!").queue();
            }
            List<Member> mentionedMembers = event.getMessage().getMentionedMembers();
            if(mentionedMembers.isEmpty()){
                channel.sendMessage(name + " You must mention who you want to ban!").queue();
            }else{
                event.getGuild()
                        .kick(member)
                        .reason("Initated by staff member");
                
            }
        }
    }
    
}
