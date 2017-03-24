package cn.zhanghao90.demo10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ListItemAdapter extends ArrayAdapter<ListItem> {
    private int resourceId;
    private View v;
    private ViewHolder viewHolder;

    public ListItemAdapter(Context context, int resource, List<ListItem> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem listItem = getItem(position);
        if(convertView == null){
            v = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) v.findViewById(R.id.imageview1);
            viewHolder.textView = (TextView) v.findViewById(R.id.textview1);
            v.setTag(viewHolder);
        }
        else{
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.imageView.setImageResource(listItem.getImgId());
        viewHolder.textView.setText(listItem.getText());
        return v;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
