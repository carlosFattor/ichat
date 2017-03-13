package br.com.guildacode.ichat_guildacode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import br.com.guildacode.ichat_guildacode.R;
import br.com.guildacode.ichat_guildacode.adapter.MessagesAdapter;
import br.com.guildacode.ichat_guildacode.app.ChatApplication;
import br.com.guildacode.ichat_guildacode.callback.ListenerMessageCallback;
import br.com.guildacode.ichat_guildacode.callback.SendMessageCallback;
import br.com.guildacode.ichat_guildacode.component.ChatComponent;
import br.com.guildacode.ichat_guildacode.event.FailureEvent;
import br.com.guildacode.ichat_guildacode.event.MessageEvent;
import br.com.guildacode.ichat_guildacode.models.Message;
import br.com.guildacode.ichat_guildacode.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {


    private Integer idClient = new Random().nextInt();

    @BindView(value = R.id.txt_msg)
    TextView msg;

    @BindView(value = R.id.btn_send)
    Button btn;

    @BindView(value = R.id.lv_messages)
    ListView listMessages;

    @BindView(value = R.id.iv_avatar_user)
    ImageView avatar;
    private List<Message> messages;

    @Inject
    ChatService chatService;
    @Inject
    Picasso picasso;
    @Inject
    EventBus eventBus;
    @Inject
    InputMethodManager input;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.messages = new ArrayList<>();

        try{
            Serializable messagesSerializable = savedInstanceState.getSerializable("messages_old");
            if(messagesSerializable != null){
                this.messages = (List<Message>) messagesSerializable;
            }
        } catch (Exception e){
            Log.d("Bundle=>", "null");
        }


        ButterKnife.bind(this);

        picasso.with(this).load("https://api.adorable.io/avatar/60/"+idClient+".png").into(avatar);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        MessagesAdapter adapter = new MessagesAdapter(idClient, this.messages, this);
        listMessages.setAdapter(adapter);

        Call<Message> call = chatService.receiver();
        call.enqueue(new ListenerMessageCallback(eventBus, this));

        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("messages_old", (ArrayList<Message>) messages);
    }

    @OnClick(R.id.btn_send)
    public void sendMessage(){
        Message msg = new Message(idClient, this.msg.getText().toString());

        //implementação da classe para tratar o callback
        chatService.send(msg).enqueue(new SendMessageCallback(this));

        this.msg.setText("");

        //hide keyboard
        input.hideSoftInputFromInputMethod(this.msg.getWindowToken(), 0);
    }

    @Subscribe
    public void insertMsg(MessageEvent msgE) {
        messages.add(msgE.msg);
        MessagesAdapter adapter = new MessagesAdapter(idClient, messages, this);

        listMessages.setAdapter(adapter);

    }

    @Subscribe
    public void listenerMessage(MessageEvent msgE){
        Call<Message> call = chatService.receiver();

        call.enqueue(new ListenerMessageCallback(eventBus, this));
    }

    @Subscribe
    public void caseError(FailureEvent event) {
        listenerMessage(null);
    }

}
