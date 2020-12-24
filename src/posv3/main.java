/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posv3;

import commands.Info;
import commands.AmongUs;
import commands.Ban;
import commands.Gungame;
import commands.Mute;
import commands.Serverinfo;
import commands.Unmute;
import commands.Userinfo;
import commands.VcDc;
import commands.clear;
import commands.kai;
import commands.kick;
import commands.say;
import help.MainHelp;
import javax.security.auth.login.LoginException;
import justforfun.corrections;
import justforfun.jamie;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

/**
 *
 * @author User
 */
public class main {
    public static String prefix = "--";
    
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("")
                .setActivity(Activity.playing("Jesse is not a bully!"))
                //.setActivity(Activity.playing("Jesse is not a bully!"))
                .addEventListeners(new Info())
                .addEventListeners(new AmongUs())
                .addEventListeners(new corrections())
                .addEventListeners(new kai())
                .addEventListeners(new say())
                .addEventListeners(new Gungame())
                .addEventListeners(new clear())
                .addEventListeners(new Mute())
                .addEventListeners(new Unmute())
                .addEventListeners(new jamie())
                .addEventListeners(new Ban())
                .addEventListeners(new kick())
                .addEventListeners(new Userinfo())
                .addEventListeners(new Serverinfo())
                .addEventListeners(new VcDc())
                .addEventListeners(new MainHelp())
                .setStatus(OnlineStatus.ONLINE)
                //.setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
    }
    
}
