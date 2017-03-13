package br.com.guildacode.ichat_guildacode.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by carlos on 10/03/17.
 */

public class Message implements Serializable{

    @SerializedName("text")
    private String msg;
    private Integer id;

    public Message(Integer id, String msg) {
        this.msg = msg;
        this.id = id;
    }

    public String getText() {
        return msg;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                ", id=" + id +
                '}';
    }
}
