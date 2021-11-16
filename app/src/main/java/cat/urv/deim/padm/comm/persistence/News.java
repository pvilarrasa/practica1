package cat.urv.deim.padm.comm.persistence;


public class News {

    String date;
    String dateUpdate;
    String imageUrl;
    String subtitle;
    Tag listTags[];
    String text;
    String title;

    public News(String date, String dateUpdate, String imageUrl, String subtitle, Tag[] listTags, String text, String title) {
        this.date = date;
        this.dateUpdate = dateUpdate;
        this.imageUrl = imageUrl;
        this.subtitle = subtitle;
        this.listTags = listTags;
        this.text = text;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Tag[] getListTags() {
        return listTags;
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
        this.imageUrl = imageUrl;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setListTags(Tag[] listTags) {
        this.listTags = listTags;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
