package ir.chbox.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.chbox.model.dao.UserDAOImpl;
import ir.chbox.model.entity.core.User;
import ir.chbox.utils.ImageUtils;
import org.apache.commons.codec.binary.Base64;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Path("/service")
public class UserManagementModule {
    @POST
    @Path("/receiveMedia")
    public Response getAllUsers(String s) {
        boolean picture=true;
        byte[] data = Base64.decodeBase64(s.substring(24,s.length()-1));
        if(picture) {
            BufferedImage b = ImageUtils.convertByteArrayToBufferedImage(data);
            float scaleRatio = ImageUtils.calculateScaleRatio(b.getWidth(), 768);//todo from properties
            if (scaleRatio > 0 && scaleRatio < 1) {
                try {
                    BufferedImage sourceBufferedImage = ImageUtils.scaleImage(b, scaleRatio);
                    data=ImageUtils.imageToByteArray(sourceBufferedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{

        }

        new S3Service().doSave(data);
        String result = "<h1>A test File saved in the bucket</h1>";
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/createUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(User entity) {
        try {
            new UserDAOImpl().create(entity);
        } catch (Exception e) {
            System.out.println("null");
        }
    }

    @POST
    @Path("/createTestUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTestUser() {
        try {
            User entity = new User();
            entity.setFirstname("farzad");
            entity.setPassword("123");
            entity.setUsername("farzad");
            entity.setLastname("sedaghat");

            new UserDAOImpl().create(entity);
            String result = "<h1>RESTful Demo Application</h1>In real world application, a collection of users will be returned !!";
            return Response.status(200).entity(result).build();
        } catch (Exception e) {
            System.out.println("null");
        }
        return null;
    }

    @POST
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String authenticate(User userEntity) {
        User user;
        user = new UserDAOImpl().authenticate(userEntity.getUsername(), userEntity.getPassword());
        try {
            if (user == null)
                return new ObjectMapper().writeValueAsString(new User());
            String result = new ObjectMapper().writeValueAsString(user);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}