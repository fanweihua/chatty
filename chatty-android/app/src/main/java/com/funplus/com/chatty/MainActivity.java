package com.funplus.com.chatty;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.funplus.chatty.ChatClient;
import com.funplus.chatty.entity.User;
import com.funplus.chatty.event.LoginEvent;
import com.google.common.eventbus.Subscribe;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static ChatClient chatClient;
    public static List<User> list;
    public static int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Init Client
        chatClient = new ChatClient("192.168.23.109", 8080);
        chatClient.addSubscriber(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loginIn(View view) {

        EditText name = (EditText) findViewById(R.id.nameText);
        EditText pwd = (EditText) findViewById(R.id.password);
        String nameString = name.getText().toString();
        String pwdString = pwd.getText().toString();

        chatClient.login(nameString, pwdString);

    }

    @Subscribe
    public void handleLogin(LoginEvent event) {
        if(event.getError() != null){
            System.out.println(event.getError());

        }else {
            list= chatClient.getUserManager().getUserList();
            id = chatClient.getUserManager().getSelf().getId();

            Intent intent = new Intent(this, ChatActivity.class);

            this.startActivity(intent);

            Log.d("Login", "Logged in");
        }

    }
}
