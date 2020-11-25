/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posv3;

import commands.Info;
import commands.AmongUs;
import commands.kai;
import commands.say;
import javax.security.auth.login.LoginException;
import justforfun.corrections;
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
        JDA jda = JDABuilder.createDefault("NjA1NTQ3NjMwOTYwODM2NjY4.XT-GLQ.A4ZeFWs5Qlqt6_OdyKXjnj_d85k")
                .setActivity(Activity.playing("Jesse is not a bully!"))
                //.setActivity(Activity.playing("Jesse is not a bully!"))
                .addEventListeners(new Info())
                .addEventListeners(new AmongUs())
                .addEventListeners(new corrections())
                .addEventListeners(new kai())
                .addEventListeners(new say())
                .setStatus(OnlineStatus.ONLINE)
                //.setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
    }
    
}
