package cat.urv.deim.padm.comm.persistence;


public class News {

    String date;
    String dateUpdate;
    String imageURL;
    String subtitle;
    Tag tags[];
    String text;
    String title;

    public News(String date, String dateUpdate, String imageUrl, String subtitle, Tag[] listTags, String text, String title) {
        this.date = date;
        this.dateUpdate = dateUpdate;
        this.imageURL = imageUrl;
        this.subtitle = subtitle;
        this.tags = listTags;
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
}
