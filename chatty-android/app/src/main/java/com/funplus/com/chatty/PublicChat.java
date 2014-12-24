package com.funplus.com.chatty;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.funplus.chatty.event.PublicMessage;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PublicChat extends ActionBarActivity {



    public ArrayList<HashMap<String,Object>> arrayList = new ArrayList<HashMap<String, Object>>();
    public SimpleAdapter listadapter;

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
        setContentView(R.layout.activity_public_chat);

        MainActivity.chatClient.addSubscriber(this);

        Intent intent2 = getIntent();
        final String name11 = intent2.getStringExtra(ChatActivity.NAME);
        TextView textView1=(TextView) findViewById(R.id.textView1);
        textView1.setText(name11);

        ListView listView = (ListView) findViewById(R.id.listView2);

        ChatMsgEntity entity = new ChatMsgEntity();
        entity.setMsgType(false);
        entity.setName(name11);
        entity.setText("Hello");

        mDataArrays.add(entity);
        mAdapter = new ChatMsgViewAdapter(PublicChat.this,mDataArrays);
        listView.setAdapter(mAdapter);


        Button button = (Button) findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String pubmessage = editText.getText().toString();
                MainActivity.chatClient.sendPublicMessage(pubmessage);

                ChatMsgEntity entity = new ChatMsgEntity();
                entity.setMsgType(false);
                entity.setName(MainActivity.chatClient.getUserManager().getSelf().getName());
                entity.setText(pubmessage);
                mDataArrays.add(entity);

                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        });

//        HashMap<String,Object> hashMap = new HashMap<String,Object>();
//        hashMap.put("name","jack");
//        hashMap.put("Message","hi");
//        arrayList.add(hashMap);
//
//        listadapter = new SimpleAdapter(this,arrayList,R.layout.listviewitem,
//                new String[]{"name","Message"},new int[]{R.id.ItemTitle,R.id.ItemText});
//
//        listView.setAdapter(listadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_public_chat, menu);
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
    public void handlePublicMessage(PublicMessage event) {
        if(event.getError() != null){
            Log.d("Chat", event.getError());

        }else {


            Log.d("Chat", "pub message received");
            PublicMessage publicMessage= new PublicMessage(event.getSender(),event.getMessage());
            String name =publicMessage.getSender().getName();
            String message = publicMessage.getMessage();

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
