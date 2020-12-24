/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
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
        
        if(args[0].equalsIgnoreCase(main.prefix + "dc")){
            if(!member.hasPermission(Permission.KICK_MEMBERS)){
                event.getChannel().sendMessage("You do not have permission!").queue();
            }else{
                if(args.length <=1){
                    event.getChannel().sendMessage("Temporary Error Message. Please provide arguments").queue();
                }else{
                    Member target = event.getMessage().getMentionedMembers().get(0);
                    if(!target.getVoiceState().inVoiceChannel()){
                        event.getChannel().sendMessage("That user is not in a voice chat!").queue();
                    }else{
                        event.getGuild().kickVoiceMember(target).queue();
                        event.getMessage()
                                .delete().queue();
                    }
                }
            }
        }
    }
    
}
