/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import posv3.main;

/**
 *
 * @author User
 */
public class ticket extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(args[0].equalsIgnoreCase(main.prefix + "ticket")){
            if(args.length <=1){
                event.getChannel().sendMessage("Please give a ticket name").queue();
            }else{
                String channelName = "";
                for(int i = 1; i < args.length; i++){
                    channelName += args[i] + " ";
                }
                Member member = event.getMember();
                long userId = member.getIdLong();
                long allow = Permission.MESSAGE_READ.getRawValue() & Permission.MESSAGE_WRITE.getRawValue();
                long deny = Permission.MESSAGE_MENTION_EVERYONE.getRawValue();
                ChannelAction<Category> category = event.getGuild().createCategory("ticket" + channelName);
                ChannelAction ticket = event.getGuild().createTextChannel(channelName);
                ticket.setParent(ctgr)
            }
        }
        
    }
    
}
