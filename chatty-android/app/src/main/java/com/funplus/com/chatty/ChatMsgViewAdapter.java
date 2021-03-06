package com.funplus.com.chatty;

/**
 * Created by linxiang on 14/12/23.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class ChatMsgViewAdapter extends BaseAdapter {


    public static interface IMsgViewType
    {

        int IMVT_COM_MSG = 0;
        int IMVT_TO_MSG = 1;
    }

    private static final String TAG = ChatMsgViewAdapter.class.getSimpleName();
    private List<ChatMsgEntity> data;
    private Context context;
    private LayoutInflater mInflater;

    public ChatMsgViewAdapter(Context context, List<ChatMsgEntity> data) {
        this.context = context;
        this.data = data;
        //LayoutInflater类似findViewById，只是LayoutInflater是找res/layout/下的.xml文件
        mInflater = LayoutInflater.from(context);
    }


    public int getCount() {
        return data.size();
    }


    public Object getItem(int position) {
        return data.get(position);
    }


    public long getItemId(int position) {
        return position;
    }

    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        ChatMsgEntity entity = data.get(position);

        if (entity.getMsgType())
        {
            return IMsgViewType.IMVT_COM_MSG;
        }else{
            return IMsgViewType.IMVT_TO_MSG;
        }

    }


    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ChatMsgEntity entity = data.get(position);
        boolean isComMsg = entity.getMsgType();

        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            if (isComMsg)
            {
                convertView = mInflater.inflate(R.layout.chatting_item_msg_left, null);
            }else{
                convertView = mInflater.inflate(R.layout.chatting_item_msg_right, null);
            }

            viewHolder = new ViewHolder();
//            viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            viewHolder.isComMsg = isComMsg;

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvUserName.setText(entity.getName());
        viewHolder.tvContent.setText(entity.getText());

        return convertView;
    }

    static class ViewHolder {
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public boolean isComMsg = true;
    }

}
