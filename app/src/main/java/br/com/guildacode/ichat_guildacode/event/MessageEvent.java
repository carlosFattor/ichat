package br.com.guildacode.ichat_guildacode.event;

import br.com.guildacode.ichat_guildacode.models.Message;

/**
 * Created by carlos on 12/03/17.
 */

public class MessageEvent {
    public Message msg;

    public MessageEvent(Message msg) {
        this.msg = msg;
    }
}
