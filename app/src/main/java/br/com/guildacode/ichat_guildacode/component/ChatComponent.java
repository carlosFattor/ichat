package br.com.guildacode.ichat_guildacode.component;

import br.com.guildacode.ichat_guildacode.activity.MainActivity;
import br.com.guildacode.ichat_guildacode.adapter.MessagesAdapter;
import br.com.guildacode.ichat_guildacode.module.ChatModule;
import dagger.Component;

/**
 * Created by carlos on 12/03/17.
 */
@Component(modules= ChatModule.class)
public interface ChatComponent {
    void inject(MainActivity activity);
    void inject(MessagesAdapter adapter);
}
