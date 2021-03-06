/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import posv3.main;

/**
 *
 * @author User
 */
public class Gungame extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(main.Blacklist.contains(event.getAuthor().getId())) return;
        
        if(args[0].equalsIgnoreCase(main.prefix + "gungame")){
            MessageChannel channel = event.getChannel();
            EmbedBuilder gunGame = new EmbedBuilder();
            gunGame.setTitle("Gun Game Maps");
            gunGame.setDescription("All the gun game maps that we have enjoyed and played");
            gunGame.addField("5719-7469-6732", "The Underworld - Gun Game | 2-16 players | First to 30 wins", true);
            gunGame.addField("4849-7779-3845", "Illusion Hotel | The Spooky Gun Game", true);
            gunGame.addField("7786-8539-6085", "Paradox | 2-16 players", true);
            gunGame.addField("6455-8418-0427", "Skull Canyon - Gun Game | 20 weapons | Siphon", true);
            gunGame.addField("9631-0136-2100", "Cyberpunk - Gun Game | 2-8 players", true);
            gunGame.addField("7288-8017-0998", "City Runners - Gun Game | First to 25 kills", true);
            gunGame.addField("6911-3765-5209", "Wild West - Gun Game | FFA | First to 20 kills | Siphon", true);
            gunGame.addField("4134-3784-1977", "Night Market - Gun Game | 2-8 players | First to 20", true);
            gunGame.addField("4241-3939-8295", "Space Museum - Gun Game | First to 20 kills", true);
            gunGame.addField("3555-6478-7916", "The Odyssey - Adventure Map", true);
            gunGame.addField("8755-7564-7913", "Avenue - Gun Game", true);
            gunGame.addField("6722-4469-6989", "Nuketown - Gun Game", true);
            gunGame.addField("0053-2829-2194", "Golden Age - Gun Game", true);
            gunGame.addField("8033-9552-5694", "Overgrown - Gun Fight | Team Fight | Even number of people is best", true);
            gunGame.addField("0315-6756-3460", "Ice Dragons Lair - Gun Fight | FFA Gun Fight best of 5", true);
            gunGame.addField("1665-4835-6529", "Among Us - Gun Game | First to 25 kills", true);
            gunGame.addField("1489-5879-1021", "Trip Chambers - Gun Game | 2-16 Players | First to 25 kills", true);
            gunGame.addField("2820-0465-2363", "The Abyss - Gun Game | 2-16 Players", true);
            gunGame.addField("6064-4771-8051", "Spooky Saloon", true);
            gunGame.addField("3428-2420-5841", "Honeycomb Hiehgts - Gun Game | 15 kills to win", true);
            gunGame.addField("1613-3549-1319", "Farmageddon - Gun Game | 2 rounds close combat then FFA", true);
            gunGame.addField("9359-4355-1184", "Among Us Mira HQ - Gun Game | 2-18 players", true);
            gunGame.addField("9521-9857-0725", "Cyber City | First to 30 kills", true);
            gunGame.addField("2238-4610-2493", "Modern Mansion | First to 20 kills", true);
            gunGame.addField("2223-2300-6060", "Jokers Gun Game | First to **50** kills", true);
            gunGame.setColor(0xfcac32);
            gunGame.setFooter("1/2 | PoS Bot Created by Cipher");
            
            EmbedBuilder gunGame2 = new EmbedBuilder();
            gunGame2.setColor(0xfcac32);
            gunGame2.setTitle("Gun Game Maps");
            gunGame2.setDescription("All the gun game maps that we havee enjoyed and played");
            gunGame2.addField("0522-2225-0329", "Rooftop Gun Game | First to 25 Kills", true);
            gunGame2.addField("4199-8785-5901", "Nuketown 2077 | Multiple Gamemode - Gun Game, Gun Swap, Bounty, Last Man Standing, FFA", true);
            gunGame2.addField("1337-8980-5503", "The Mandalorian | Two 6 minute rounds different guns each round!", true);
            gunGame2.setFooter("2/2 | PoS Bot Created by Cipher");
            
            channel.sendMessage(gunGame.build()).queue();
            channel.sendMessage(gunGame2.build()).queue();
            event.getMessage().delete().queue();
                    
        }
    }
    
}
