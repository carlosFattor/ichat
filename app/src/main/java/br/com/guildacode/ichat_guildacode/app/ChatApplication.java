package br.com.guildacode.ichat_guildacode.app;

import android.app.Application;

import br.com.guildacode.ichat_guildacode.component.ChatComponent;
import br.com.guildacode.ichat_guildacode.component.DaggerChatComponent;
import br.com.guildacode.ichat_guildacode.module.ChatModule;

/**
 * Created by carlos on 12/03/17.
 */

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent
                .builder()
                .chatModule(new ChatModule(this))
                .build();
    }

    public ChatComponent getComponent(){
        return component;
    }
}
