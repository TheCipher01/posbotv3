/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import posv3.main;

/**
 *
 * @author User
 */
public class Info extends ListenerAdapter 
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw(); 
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals(main.prefix + "info"))
        {
            MessageChannel channel = event.getChannel();
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("PoS Bot v3");
            info.setDescription("PoS Bot v3 written in Java");
            info.setColor(0xf45642);
            info.setFooter("Created by Cipher!");
            channel.sendMessage(info.build()).queue();
            channel.sendMessage("I live!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
    }
}
