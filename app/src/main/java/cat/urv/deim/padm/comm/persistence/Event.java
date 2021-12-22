package cat.urv.deim.padm.comm.persistence;

public class Event {

    int id;
    String description;
    String imageURL;
    String name;
    cat.urv.deim.padm.comm.persistence.Tag tags[];
    String type;
    String webURL;


    public Event(int id, String description, String imageURL, String name, cat.urv.deim.padm.comm.persistence.Tag[] tags, String type, String webURL) {
        this.id = id;
        this.description = description;
        this.imageURL = imageURL;
        this.name = name;
        this.tags = tags;
        this.type = type;
        this.webURL = webURL;
    }

    public int getId() {
        return id;
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
        return tags;
    }

    public String getType() {
        return type;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setId(int id) {
        this.id = id;
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
        this.tags = listTags;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
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
