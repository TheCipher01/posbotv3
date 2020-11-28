/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.List;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class clear extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");    
        
        if(args[0].equalsIgnoreCase(main.prefix + "clear")){
            if(args.length <= 2){
                event.getChannel().sendMessage("Improper Usage! --clear {channel} {# of messages}").queue();
            }else{
                TextChannel target = event.getChannel();
                purgeMessage(target, Integer.parseInt(args[2]));
            }
        }
    }
    
    private void purgeMessage(TextChannel channel, int num){
        MessageHistory history = new MessageHistory(channel);
        List<Message> msgs;
        msgs = history.retrievePast(num).complete();
        channel.deleteMessages(msgs).queue();
    }
}
