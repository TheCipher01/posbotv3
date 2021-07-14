/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justforfun;


import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 *
 * @author User
 */
public class corrections extends ListenerAdapter{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        MessageChannel channel = event.getChannel();
        @SuppressWarnings("null")
        String bs = "";
        if(event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw(); 
        
        if(content.toLowerCase().contains("traitor")){
            String name = event.getMember().getAsMention();
            channel.sendMessage(name + " You mean Jamie? Fuck Jamie!").queue();
        }
        
        if(content.toLowerCase().contains("tonic")){
            String name = event.getMember().getAsMention();
            channel.sendMessage(name + " It's toxic").queue();
        }
        
        if(content.toLowerCase().contains("hesse")){
            String name = event.getMember().getAsMention();
            channel.sendMessage(name + " It's **Jesse**").queue();
        }
        
        if(content.toLowerCase().contains("simp")){
            String name = event.getMember().getAsMention();
            channel.sendMessage(name + " Why are you talking about Brandon?").queue();
        }
        
    }
}
