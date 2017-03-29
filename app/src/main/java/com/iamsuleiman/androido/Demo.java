package com.iamsuleiman.androido;

/**
 * Created by suleiman on 28/03/17.
 */

public class Demo {

    String title;
    String description;

    public Demo() {
    }

    public Demo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
