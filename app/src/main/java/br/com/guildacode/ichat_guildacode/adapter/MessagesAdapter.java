package br.com.guildacode.ichat_guildacode.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.guildacode.ichat_guildacode.R;
import br.com.guildacode.ichat_guildacode.models.Message;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by carlos on 10/03/17.
 */

public class MessagesAdapter extends BaseAdapter{

    private final Activity activity;
    private final Integer idClient;
    private List<Message> messages;

    @BindView(R.id.tv_text)
    TextView textView;
    @BindView(R.id.iv_avatar_msg)
    ImageView avatar_msg;
    @Inject
    Picasso picasso;

    public MessagesAdapter(Integer idClient, List<Message> messages, Activity activity) {
        this.messages = messages;
        this.activity = activity;
        this.idClient = idClient;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View line = this.activity.getLayoutInflater().inflate(R.layout.messages, viewGroup, false);

        ButterKnife.bind(this, line);

        Message msg = getItem(position);

        picasso.with(this.activity).load("https://api.adorable.io/avatar/60/"+msg.getId()+".png").into(avatar_msg);

        if(idClient != msg.getId()){
            line.setBackgroundColor(Color.CYAN);
        }

        textView.setText(msg.getText());
        return line;
    }
}
