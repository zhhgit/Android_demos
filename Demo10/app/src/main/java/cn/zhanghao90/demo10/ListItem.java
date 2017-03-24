package cn.zhanghao90.demo10;

public class ListItem {
    private String text;
    private int imgId;

    public ListItem(String text,int imgId) {
        this.imgId = imgId;
        this.text = text;
    }

    public int getImgId(){
        return imgId;
    }

    public String getText(){
        return text;
    }
}
