package edu.cmu.mgmt.model.flickr;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rsp")
public class FlickrTag {
    @XmlAttribute
    private String stat;

    private Photo photo;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public List<String> getTags() {
        return photo.getTags().getTags();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType
    public static class Photo {
        @XmlAttribute
        private String id;
        private Tags tags;
        
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Tags getTags() {
            return tags;
        }

        public void setTags(Tags tags) {
            this.tags = tags;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType
        public static class Tags {
            @XmlElement(name = "tag", type = String.class)
            private List<String> tags = new ArrayList<String>();

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }
        }
    }
}
