package cn.zhanghao90.demo11;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListItemAdapter3 extends RecyclerView.Adapter<ListItemAdapter3.ViewHolder>{
    private List<ListItem> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View listItem;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            listItem = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.imageview3);
            textView = (TextView) itemView.findViewById(R.id.textview3);
        }
    }

    public ListItemAdapter3(List<ListItem> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item3,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ListItem listItem = mList.get(position);
                Toast.makeText(v.getContext(),"该项文字是："+listItem.getText(),Toast.LENGTH_LONG).show();
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ListItem listItem = mList.get(position);
                Toast.makeText(v.getContext(),"点击了图片，对应文字是："+listItem.getText(),Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = mList.get(position);
        holder.imageView.setImageResource(listItem.getImgId());
        holder.textView.setText(listItem.getText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
