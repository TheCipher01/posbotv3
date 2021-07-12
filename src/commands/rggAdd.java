/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author jdmay
 */
public class rggAdd extends ListenerAdapter{
    
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        if(args[0].equalsIgnoreCase(main.prefix + "rggadd")){
            String name = args[1];
            String code = args[2];
            
            try{
                PrintWriter out = new PrintWriter(new FileOutputStream(new File("rgg.txt"), true));
                out.println(name);
                out.println(code);
                out.close();
                event.getChannel().sendMessage("Random Gungame list updated!").queue();
                event.getMessage().delete().queue();
                System.out.println("gun game list updated!");
            }catch (FileNotFoundException ex){
                Logger.getLogger(blacklist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
