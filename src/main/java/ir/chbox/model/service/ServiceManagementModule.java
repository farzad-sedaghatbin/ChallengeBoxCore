package ir.chbox.model.service;

import ir.chbox.model.dao.UserDAOImpl;
import ir.chbox.model.entity.core.User;
import ir.chbox.utils.ActivationCodeGenerator;
import ir.chbox.utils.ImageUtils;
import ir.chbox.utils.video.MediaConvertor;
import org.apache.commons.codec.binary.Base64;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Path("/service")
public class ServiceManagementModule {

    @POST
    @Path("/receivePicture")
    public Response receivePicture(String s) {
        byte[] data = Base64.decodeBase64(s.substring(24, s.length() - 1));
        BufferedImage b = ImageUtils.convertByteArrayToBufferedImage(data);
        float scaleRatio = ImageUtils.calculateScaleRatio(b.getWidth(), 768);//todo from properties
        if (scaleRatio > 0 && scaleRatio < 1) {
            try {
                BufferedImage sourceBufferedImage = ImageUtils.scaleImage(b, scaleRatio);
                data = ImageUtils.imageToByteArray(sourceBufferedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new S3Service().doSave(data,".jpg");
        String result = "<h1>A test File saved in the bucket</h1>";
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/receiveVideo")
    public Response receiveVideo(String s) {
        byte[] data = Base64.decodeBase64(s.substring(24, s.length() - 1));
       data= MediaConvertor.Convert(data);
        new S3Service().doSave(data,".mp4");
        String result = "<h1>A test File saved in the bucket</h1>";
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/createUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(User entity) {
        try {

            entity.setActivationCode(ActivationCodeGenerator.generator());
            new UserDAOImpl().create(entity);
        } catch (Exception e) {
            return "false";
        }
        return "true";
    }

    @POST
    @Path("/updateUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User entity) {
        entity.setActivationCode(ActivationCodeGenerator.generator());
        new UserDAOImpl().update(entity);
    }

    @GET
    @Path("/activation")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String activation(String username, String code) {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.findByUsername(username);
        if (user.getActivationCode().equals(code)) {
            user.setActivated(true);
            userDAO.update(user);
            return "true";
        } else {
            return "false";
        }

    }

    @GET
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public User authenticate(String username, String password) {
        User user = new UserDAOImpl().authenticate(username, password);
        if (user == null)
            return new User();
        return user;
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
}