package cn.zhanghao90.demo11;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder>{
    private List<ListItem> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview1);
            textView = (TextView) itemView.findViewById(R.id.textview1);
        }
    }

    public ListItemAdapter(List<ListItem> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,false);
        ViewHolder holder = new ViewHolder(view);
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
