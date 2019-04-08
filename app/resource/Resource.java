package resource;

import models.Games;


/**
 * Resource for the API.  This is a presentation class for frontend work.
 */
public class Resource<T> {
    private T object;
    private String link;

    public Resource() {

    }

    public Resource(T object, String link) {
        this.object = object;
        this.link = link;
    }

    public T getObject() {
        return object;
    }

    public String getLink() {
        return link;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
