package com.example.exam;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class NameAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String > array_name;
    SQLiteDatabase sqlDB;
    MyDBHelper myDBHelper;
    private String names [];
    private ViewHolder viewHolder;
    private ListView listView;
    public NameAdapter(Context c, ArrayList<String> array_name){
       this.context = c;
       this.array_name = array_name;
    }
    public Context getContext(){
        return context;
    }

    @Override
    public int getCount() {
        return array_name.size();}

    @Override
    public Object getItem(int i) {
        return array_name.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.name_item, viewGroup, false);
        viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        Button btn = (Button)view.findViewById(R.id.card_more_btn);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                PopupMenu p = new PopupMenu(getContext(), v);
                p.getMenuInflater().inflate(R.menu.memu2, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        return true;
                    }
                });
                p.show();
            }
        });

        return view;
    }

    public class ViewHolder{
        private TextView name;
        public ViewHolder(View convertView){
            name=(TextView)convertView.findViewById(R.id.names);

        }

    }


}
