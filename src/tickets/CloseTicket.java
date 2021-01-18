/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class CloseTicket extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(event.getAuthor().isBot()) return;
        Member member = event.getMember();
        
        if(args[0].equalsIgnoreCase(main.prefix + "closeticket")){
            if(!member.hasPermission(event.getChannel(), Permission.MANAGE_CHANNEL, null)){
                event.getChannel().sendMessage("You can't use this command!").queue();
            }else{
                event.getChannel().sendMessage("Closing Ticket!").queue();
                event.getChannel().delete().queue();
            }
        }
    }
    
}
