/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.Collection;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import posv3.main;

/**
 *
 * @author User
 */
public class buttonTest extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(main.Blacklist.contains(event.getAuthor().getId())) return;
        
        if(args[0].equalsIgnoreCase(main.prefix + "button")){
            event.getChannel().sendMessage("Press the button to say hi!")
                    .setActionRows((Collection<? extends ActionRow>) Button.primary("hello", "click me!"))
                    .queue();
        }
    }
    @Override
    public void onButtonClick(ButtonClickEvent event){
        if(event.getComponentId().equals("hello")){
            event.reply("Hello!").queue();
        }
    }
    
}
