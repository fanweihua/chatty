package com.funplus.com.chatty;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.funplus.chatty.event.PrivateMessage;
import com.funplus.chatty.event.PublicMessage;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class PrivateChat extends ActionBarActivity {


    private int friendid;
    private ChatMsgViewAdapter mAdapter;
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                mAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);
        
        MainActivity.chatClient.addSubscriber(this);

        Intent intent1 = getIntent();
        String friendname = intent1.getStringExtra(ChatActivity.NAME);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setText(friendname);


        friendid = intent1.getIntExtra(ChatActivity.ID,-1);


        ListView listView = (ListView) findViewById(R.id.listView111);

        ChatMsgEntity entity = new ChatMsgEntity();
        entity.setMsgType(false);
        entity.setName(MainActivity.chatClient.getUserManager().getSelf().getName());
        entity.setText("Hello");

        mDataArrays.add(entity);
        mAdapter = new ChatMsgViewAdapter(PrivateChat.this,mDataArrays);
        listView.setAdapter(mAdapter);

        Button button =  (Button) findViewById(R.id.privatesend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) findViewById(R.id.privatemessage);
                String pvm=editText.getText().toString();
                MainActivity.chatClient.sendPrivateMessage(pvm,friendid);

                ChatMsgEntity entity = new ChatMsgEntity();
                entity.setMsgType(false);
                entity.setName(MainActivity.chatClient.getUserManager().getSelf().getName());

                entity.setText(pvm);
                mDataArrays.add(entity);

                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_private_chat, menu);
        return true;
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

    @Subscribe
    public void handlePrivateMessage(PrivateMessage event) {
        if(event.getError() != null){
            System.out.println(event.getError());

        }else {

            PrivateMessage privateMessage = new PrivateMessage(event.getSender(),event.getRecepient(),event.getMessage());
            String name = privateMessage.getSender().getName();
            String message = privateMessage.getMessage();

            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setMsgType(true);
            entity.setName(name);
            entity.setText(message);
            mDataArrays.add(entity);

            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }

    }

}


