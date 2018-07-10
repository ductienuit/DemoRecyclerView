package com.example.ductien.viewlist.models;

/**
 * Created by DucTien on 09/07/2018.
 */

public class TopicObject {
    private String topicImage;
    private String topicName;
    private String topicDescription;

    public TopicObject(String topicImage, String topicName, String topicDescription) {
        this.topicImage = topicImage;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
    }

    public String getTopicImage() {
        return topicImage;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }
}

