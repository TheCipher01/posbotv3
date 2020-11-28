/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justforfun;

import java.awt.Color;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class jamie extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Member member = event.getMember();
        
        if(args[0].equalsIgnoreCase(main.prefix + "jamie")){
            if(!member.hasPermission(Permission.ADMINISTRATOR)){
                event.getChannel().sendMessage("You cannot use this command!").queue();
                if(args.length <= 1){
                   event.getChannel().sendMessage("Tag jamie you dingus").queue();
                   event.getMessage().delete().queue();
                }
            if(args.length <=1){
                   event.getChannel().sendMessage("Tag jamie you dingus").queue();
                   event.getMessage().delete().queue();                
            }else{
                Role jamie = event.getGuild().getRoleById("727753015418814606");
                Member target = event.getMessage().getMentionedMembers().get(0);
                
                if(target.getRoles().contains(jamie)){
                    event.getGuild().removeRoleFromMember(target, jamie).queue();
                    event.getChannel().sendMessage("Jamie has been ungrounded...you're going to regret that").queue();
                    event.getMessage().delete().queue();
                }else{
                    event.getGuild().addRoleToMember(target, jamie).queue();
                    event.getChannel().sendMessage("Jamie has been grounded...probably for posting cursed shit").queue();
                    event.getMessage().delete().queue();
                }
//                
//                event.getGuild().addRoleToMember(target, jamie).queue();
//                event.getChannel().sendMessage("Jamie has been grounded..probably for posting cursed shit").queue();
//                event.getMessage().delete().queue();
            }
        }
     }
    }
}
