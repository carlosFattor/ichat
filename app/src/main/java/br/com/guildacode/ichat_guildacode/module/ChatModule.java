package br.com.guildacode.ichat_guildacode.module;

import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.guildacode.ichat_guildacode.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by carlos on 11/03/17.
 */

@Module
public class ChatModule {

    private static String END_POINT = "https://ichat-guildacode.herokuapp.com/";
    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }

    @Provides
    public ChatService getChatService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatService chatService = retrofit.create(ChatService.class);
        return chatService;
    }

    @Provides
    public Picasso getPicasso() {
        Picasso picasso = new Picasso.Builder(this.app).build();
        return picasso;
    }

    @Provides
    public EventBus getEventBus(){
        return EventBus.builder().build();
    }

    @Provides
    public InputMethodManager getInputMethodManager(){
        InputMethodManager input = (InputMethodManager) this.app.getSystemService(this.app.INPUT_METHOD_SERVICE);
        return input;
    }
}
