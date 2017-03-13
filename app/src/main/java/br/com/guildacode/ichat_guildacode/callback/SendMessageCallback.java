package br.com.guildacode.ichat_guildacode.callback;

import android.app.Activity;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by carlos on 10/03/17.
 */

public class SendMessageCallback implements Callback<Void> {

    private Activity context;

    public SendMessageCallback(Activity context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        Toast.makeText(context, "Message sent", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
}
