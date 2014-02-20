package edu.cmu.mgmt.utils;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import edu.cmu.mgmt.model.flickr.FlickrLocation;
import edu.cmu.mgmt.model.flickr.FlickrPerson;
import edu.cmu.mgmt.model.flickr.FlickrPhoto;
import edu.cmu.mgmt.model.flickr.FlickrPhotoId;
import edu.cmu.mgmt.model.flickr.FlickrPhotos;
import edu.cmu.mgmt.model.flickr.FlickrTag;
import edu.cmu.mgmt.model.flickr.FlickrUser;
import edu.cmu.mgmt.model.flickr.FlickrPhoto.Photo;

public class XMLUtils {

    public static Object reflection(String response, Class<?> c) {
        if (response != null && !response.isEmpty()) {
            try {
                Class<?> cls = Class.forName(c.getName());
                Object obj = null;

                try {
                    JAXBContext context = JAXBContext.newInstance(cls);
                    Unmarshaller un = context.createUnmarshaller();
                    StringReader reader = new StringReader(response);
                    obj = un.unmarshal(reader);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                return obj;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static FlickrPhotos xmlToPhotos(String response) {
        return (FlickrPhotos) reflection(response, FlickrPhotos.class);
    }

    public static FlickrUser xmlToUser(String response) {
        return (FlickrUser) reflection(response, FlickrUser.class);
    }

    public static Photo xmlToPhoto(String response) {
        return ((FlickrPhoto) reflection(response, FlickrPhoto.class))
                .getPhoto();
    }

    public static String xmlToPhotoId(String response) {
        return ((FlickrPhotoId) reflection(response, FlickrPhotoId.class))
                .getPhotoid();
    }

    public static FlickrPerson xmlToPerson(String response) {
        return (FlickrPerson) reflection(response, FlickrPerson.class);
    }

    public static FlickrLocation xmlToLocation(String response) {
        return (FlickrLocation) reflection(response, FlickrLocation.class);
    }

    public static List<String> xmlToTags(String response) {
        return ((FlickrTag) reflection(response, FlickrTag.class)).getTags();
    }
}
