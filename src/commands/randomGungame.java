/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import posv3.main;

/**
 *
 * @author jdmay
 */
public class randomGungame extends ListenerAdapter{
    public static final List<String> gg = new ArrayList<>();
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        
//        gg.add("The Underworld! Code: 5719-7469-6732");
//        gg.add("Illusion Hotel! Code: 4849-7779-3845");
//        gg.add("Skull Canyon! Code: 6455-8418-0427");
//        gg.add("Cyberpunk! Code: 9631-0136-2100");
//        gg.add("City Runners! Code: 7288-8017-0998");
//        gg.add("Wild West! Code: 6911-3765-5209");
//        gg.add("Night Market! Code: 4134-3784-1977");
//        gg.add("Space Museum! Code: 4241-3939-8295");
//        gg.add("The Odyssey? I don't think that should be in the gun game list! Roll again!");
//        gg.add("Avenue! Code: 8755-7564-7913");
//        gg.add("Nuketown! Code: 6722-4469-6989");
//        gg.add("Golden Age! Code: 0053-2829-2194");
//        gg.add("Overgrown! Code: 8033-9552-5694");
//        gg.add("Ice Dragons Lair! Code: 0315-6756-3460");
//        gg.add("Among Us! Code: 1665-4835-6529");
//        gg.add("Trip Chambers! Code: 1489-5879-1021");
//        gg.add("The Abyss! Code: 2820-0465-2363");
//        gg.add("Spooky Saloon! Code: 6064-4771-8051");
//        gg.add("Honeycomb Heights! Code: 3428-2420-5841");
//        gg.add("Farmageddon! Code: 1613-3549-1319");
//        gg.add("Among Us Mira! Code: 9359-4355-1184");
//        gg.add("Cyber City! Code: 9521-9857-0725");
//        gg.add("Modern Mansion! Code: 2238-4610-2493");
//        gg.add("Jokers Gun Game! Code: 2223-2300-6060");
//        gg.add("Rooftop! Code: 0522-2225-0329");
//        gg.add("Nuketown 2077! Code: 4199-8785-5901");
//        gg.add("The Mandalorian! Code: 1337-8980-5503");
//        gg.add("Star Wars Jedah City! Code: 4619-1110-7182");
//        gg.add("Tower Kingdom! Code: 8210-7532-4876");
//        gg.add("Junk Yard! Code: 9756-2363-7717");
    
        try{
            Scanner in = new Scanner(new File("rgg.txt"));
            while(in.hasNextLine()){
                String map = in.nextLine();
                String code = in.nextLine();
                gg.add(map + "! Code: " + code);
            }
            in.close();
        }catch (FileNotFoundException ex){
            Logger.getLogger(randomGungame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(args[0].equalsIgnoreCase(main.prefix + "rgg")){
            event.getChannel().sendMessage("Click the button to generate a random gun game map!")
                    .setActionRow(Button.success("gen", "Generate a gun game!"), Button.danger("delete", "Delete this message"))
                    .queue();
            event.getMessage().delete().queue();
        }
    }
    
    @Override
    public void onButtonClick(ButtonClickEvent event){
        if(event.getComponentId().equals("gen")){
            int index = new Random().nextInt(gg.size());
            String choice = gg.get(index);
            
            event.getChannel().sendMessage("I picked: " + choice).queue();
            log(event.getMember(), event.getChannel(), "Generate Gungame", event.getGuild().getTextChannelsByName("staff-log", true).get(0));
        }else if(event.getComponentId().equals("delete")){
            event.getChannel().sendMessage("Thanks for playing!").queue();
            log(event.getMember(), event.getChannel(), "Delete RGG message", event.getGuild().getTextChannelsByName("staff-log", true).get(0));
            event.getMessage().delete().queue();
        }
    }
    
    public void log(Member member, MessageChannel used, String button, TextChannel channel){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Button Pressed!");
        log.setColor(Color.decode("#6c5ce7"));
        log.setDescription(member.getAsMention() + " has pushed the** " + button + "** in channel: " + used.getName());
        log.addField("Date", sdf.format(date), false);
        log.addField("Time", stf.format(date), false);
        channel.sendMessage(log.build()).queue();
        
    }
}
