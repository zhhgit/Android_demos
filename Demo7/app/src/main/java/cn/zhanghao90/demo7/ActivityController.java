package cn.zhanghao90.demo7;

import android.app.Activity;
import java.util.LinkedList;
import java.util.List;

public class ActivityController {
    private static List<Activity> list = new LinkedList<>();

    public static void addActivity(Activity activity){
        list.add(activity);
    }

    public static void removeActivity(Activity activity){
        list.remove(activity);
    }

    public static void finishAll(){
        for(int i=0;i<list.size();i++){
            if(!list.get(i).isFinishing()){
                list.get(i).finish();
            }
        }
    }
}
