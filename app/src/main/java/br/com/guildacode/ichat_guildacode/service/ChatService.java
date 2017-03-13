package br.com.guildacode.ichat_guildacode.service;

import br.com.guildacode.ichat_guildacode.models.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by carlos on 10/03/17.
 */

public interface ChatService {

    @POST("polling")
    Call<Void> send(@Body Message message);

    @GET("polling")
    Call<Message> receiver();
}
