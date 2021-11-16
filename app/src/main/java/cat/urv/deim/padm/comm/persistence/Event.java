package cat.urv.deim.padm.comm.persistence;

public class Event {

    String description;
    String imageURL;
    String name;
    cat.urv.deim.padm.comm.persistence.Tag listTags[];
    String type;
    String webURL;


    public Event(String description, String imageURL, String name, cat.urv.deim.padm.comm.persistence.Tag[] listTags, String type, String webURL) {
        this.description = description;
        this.imageURL = imageURL;
        this.name = name;
        this.listTags = listTags;
        this.type = type;
        this.webURL = webURL;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public cat.urv.deim.padm.comm.persistence.Tag[] getListTags() {
        return listTags;
    }

    public String getType() {
        return type;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListTags(cat.urv.deim.padm.comm.persistence.Tag[] listTags) {
        this.listTags = listTags;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }
}
