package cat.urv.deim.padm.comm.persistence;


import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class News implements Serializable {
    @PrimaryKey
    int id;

    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "dateUpdate")
    String dateUpdate;

    @ColumnInfo(name = "imageURL")
    String imageURL;

    @ColumnInfo(name = "subtitle")
    String subtitle;

    @Ignore
    cat.urv.deim.padm.comm.persistence.Tag tags[];

    @ColumnInfo(name = "text")
    String text;

    @ColumnInfo(name = "title")
    String title;

    public News(int id, String date, String dateUpdate, String imageURL, String subtitle, String text, String title) {
        this.id = id;
        this.date = date;
        this.dateUpdate = dateUpdate;
        this.imageURL = imageURL;
        this.subtitle = subtitle;
        this.text = text;
        this.title = title;
    }

    public News(int id, String date, String dateUpdate, String imageUrl, String subtitle, cat.urv.deim.padm.comm.persistence.Tag[] listTags, String text, String title) {
        this.id = id;
        this.date = date;
        this.dateUpdate = dateUpdate;
        this.imageURL = imageUrl;
        this.subtitle = subtitle;
        this.tags = listTags;
        this.text = text;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public String getImageUrl() {
        return imageURL;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Tag[] getListTags() {
        return tags;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setListTags(Tag[] listTags) {
        this.tags = listTags;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagsAsString(){
        String ret = "";
        for (int i = 0; i < this.tags.length; i++) {
            ret += this.tags[i].getName();
            // si no és l'ultim posem coma
            if(i != this.tags.length - 1){
                ret += ", ";
            }
        }
        return ret;
    }
}
