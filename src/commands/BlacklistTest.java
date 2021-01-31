/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.Arrays;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class BlacklistTest extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(main.Blacklist.contains(event.getAuthor().getId())){
            event.getChannel().sendMessage("True").queue();
        }else{
            if(args[0].equalsIgnoreCase(main.prefix + "test"))
                event.getChannel().sendMessage("Testing").queue();
        }
    }
    
}
