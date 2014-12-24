package com.funplus.com.chatty;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.funplus.chatty.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class ChatActivity extends ListActivity {

    public final static String NAME = "chatname";
    public final static String ID = "id";

    HashMap hashMap = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        for (User user:MainActivity.list)
        {
            hashMap.put(user.getName(),user.getId());
        }
        Set<String> keyset= hashMap.keySet();

        List<String> list = new ArrayList<String>(keyset);

        ListView listView = (ListView) findViewById(android.R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mMessageClickedHandle);



        Button buttonpum = (Button) findViewById(R.id.buttpum);

        buttonpum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(ChatActivity.this, PublicChat.class);
                intent2.putExtra(NAME,"Public");

                startActivity(intent2);
            }
        });



    }

    public  AdapterView.OnItemClickListener mMessageClickedHandle = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String name=((TextView)view).getText().toString();
            int tempid = (int) hashMap.get(name);

            Intent intent1 = new Intent(ChatActivity.this, PrivateChat.class);
            intent1.putExtra(NAME,name);
            intent1.putExtra(ID,tempid);

            startActivity(intent1);
        }
    };

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

}
