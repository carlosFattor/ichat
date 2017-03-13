package br.com.guildacode.ichat_guildacode.callback;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import br.com.guildacode.ichat_guildacode.event.FailureEvent;
import br.com.guildacode.ichat_guildacode.event.MessageEvent;
import br.com.guildacode.ichat_guildacode.models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by carlos on 10/03/17.
 */

public class ListenerMessageCallback implements Callback<Message> {


    private final EventBus eventBus;
    private Context context;

    public ListenerMessageCallback(EventBus eventBus, Context context) {
        this.context = context;
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<Message> call, Response<Message> response) {

        if(response.isSuccessful()){
            Message msg = response.body();
            MessageEvent messageEvent = new MessageEvent(msg);

            this.eventBus.post(messageEvent);
        }
    }

    @Override
    public void onFailure(Call<Message> call, Throwable t) {
        eventBus.post(new FailureEvent());
    }
}
