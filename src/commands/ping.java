/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class ping extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(main.Blacklist.contains(event.getAuthor().getId())) return;
        
        if(args[0].equalsIgnoreCase(main.prefix + "ping")){
            long before = System.currentTimeMillis();
            event.getChannel().sendMessage("Pinging. . .").queue(message -> {
                message.editMessage("Gateway ping: `" + event.getJDA().getGatewayPing() + "`ms\nPing: `" +  (System.currentTimeMillis()-before) + "' ms").queue();
            });
            event.getMessage().delete().queue();
        }
    }
    
}
