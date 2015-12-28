package ir.chbox.model.service;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.chbox.model.dao.UserDAOImpl;
import ir.chbox.model.entity.challenge.ChallengeDescriptor;
import ir.chbox.model.entity.core.User;
import ir.chbox.model.jsonpojo.Holder;
import ir.chbox.model.jsonpojo.Item;
import ir.chbox.model.jsonpojo.Tab;
import ir.chbox.utils.ActivationCodeGenerator;
import ir.chbox.utils.ImageUtils;
import ir.chbox.utils.video.MediaConvertor;
import org.apache.commons.codec.binary.Base64;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/service")
public class ServiceManagementModule {
    /*
    @FormParam("username") String name,
                                 @FormParam("descriptin") String descriptin,
                                 @FormParam("Type")String type,
                                 @Form("Archivefile") InputStream uploadedInputStream,
                                 @FormDataParam("Archivefile") FormDataContent
     */

    @POST
    @Path("/topChallenge")
    @Produces(MediaType.TEXT_PLAIN)
    public String topChallenge() {


        List<Tab> tabs= new ArrayList<>();
        Holder holder= new Holder(true,"",tabs);
        List<Item> items= new ArrayList<>();
        Tab tab1= new Tab(1l,"Stream", items);
        List<Item> items2= new ArrayList<>();
        List<ChallengeDescriptor> descriptors=ChallengeDescriptorService.getInstance().getTop();
        for (ChallengeDescriptor descriptor : descriptors) {
            Item item= new Item();
            item.setDescription(descriptor.getDescription());
            item.setName(descriptor.getTitle());
            item.setRating(descriptor.getRating());
            item.setImg(descriptor.getThumbnail());
            items2.add(item);
        }
        Tab tab2 = new Tab(2l, "Challenge", items2);
        List<Item> items3= new ArrayList<>();
        Tab tab3= new Tab(3l,"Users",items3);
        tabs.add(tab1);
        tabs.add(tab2);
        tabs.add(tab3);
        try {
            String s=new ObjectMapper().writeValueAsString(holder);
            System.out.println(s);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";

    }
    @POST
    @Path("/newChallenge")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response newChallenge(MultipartFormDataInput input) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("Archivefile");
        String UPLOADED_FILE_PATH = "/home/farzad";
        String title;
        String description = "";
        try {
             title = new String(uploadForm.get("username").get(0).getBody(String.class, null).getBytes("us-ascii"), "UTF-8");

            String category = uploadForm.get("Type").get(0).getBody(String.class, null);
            description = uploadForm.get("descriptin").get(0).getBody(String.class, null);
            System.out.println(title);

            for (InputPart inputPart : inputParts) {


                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = getFileName(header);

                //convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                byte[] bytes = IOUtils.toByteArray(inputStream);

                //constructs upload file path
                fileName = UPLOADED_FILE_PATH + fileName;


                System.out.println("Done");
//                ChallengeDescriptor challengeDescriptor = new ChallengeDescriptor();
//                challengeDescriptor.setTitle(title);
//                challengeDescriptor.setDescription(description);
//                challengeDescriptor.setType(category);
//                challengeDescriptor.setStream(fileName);
//                ChallengeDescriptorService.getInstance().create(challengeDescriptor);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        byte[] data = Base64.decodeBase64(s.substring(24, s.length() - 1));
//        BufferedImage b = ImageUtils.convertByteArrayToBufferedImage(data);
//        float scaleRatio = ImageUtils.calculateScaleRatio(b.getWidth(), 768);//todo from properties
//        if (scaleRatio > 0 && scaleRatio < 1) {
//            try {
//                BufferedImage sourceBufferedImage = ImageUtils.scaleImage(b, scaleRatio);
//                data = ImageUtils.imageToByteArray(sourceBufferedImage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        new S3Service().doSave(data,".jpg");
        String result= "" + description;
        return Response.status(200).entity(result).build();
    }

    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

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
        new S3Service().doSave(data, ".jpg");
        String result = "<h1>A test File saved in the bucket</h1>";
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/receiveVideo")
    public Response receiveVideo(String s) {
        byte[] data = Base64.decodeBase64(s.substring(24, s.length() - 1));
        data = MediaConvertor.Convert(data);
        new S3Service().doSave(data, ".mp4");
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