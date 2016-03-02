package ir.chbox.model.service;

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
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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


        List<Tab> tabs = new ArrayList<>();
        Holder holder = new Holder(true, "", tabs);
        List<Item> items = new ArrayList<>();
//        Tab tab1 = new Tab(1l, "Stream", items);
        List<Item> items2 = new ArrayList<>();
        List<ChallengeDescriptor> descriptors = ChallengeDescriptorService.getInstance().getTop();
        for (ChallengeDescriptor descriptor : descriptors) {
            Item item = new Item();
            item.setId(descriptor.getId());
            item.setDescription(descriptor.getDescription());
            item.setName(descriptor.getTitle());
            item.setRating(descriptor.getRating());
            item.setImg("https://s3.eu-central-1.amazonaws.com/challengebox/a.jpg");
            items2.add(item);
        }
        Tab tab1 = new Tab(1l, "Stream", items2);
        Tab tab2 = new Tab(2l, "Challenge", items2);
        List<Item> items3 = new ArrayList<>();
        Tab tab3 = new Tab(3l, "Users", items2);
        tabs.add(tab1);
        tabs.add(tab2);
        tabs.add(tab3);
        try {
            String s = new ObjectMapper().writeValueAsString(holder);
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
    public Response newChallenge(@FormDataParam("title") String title, @FormDataParam("Type") String Type, @FormDataParam("fileType") String fileType, @FormDataParam("description") String description, @FormDataParam("Archivefile") InputStream fileInputStream,
                                 @FormDataParam("Archivefile") FormDataContentDisposition fileMetaData) {

        System.out.println("start");
        ChallengeDescriptor challengeDescriptor = new ChallengeDescriptor();
        challengeDescriptor.setTitle(title);
        challengeDescriptor.setDescription(description);
        challengeDescriptor.setType(Type);
        challengeDescriptor.setStream(fileMetaData.getFileName());

        if (fileType.contains("video/mp4")) {
//            VideoUtils videoUtils = new VideoUtils();
//            videoUtils.sizeDetector(fileInputStream);
//            if (videoUtils.getWidth() > 320 || videoUtils.getHeight() > 240) {
                MediaConvertor m = new MediaConvertor();
                byte[] output = m.Convert(fileInputStream);
                java.nio.file.Path p = null;
                try {
                    ImageIO.write(m.getThumbnail(), "jpg", new File("/home/farzad/Documents/s3/thumbnail.jpg"));

                    p = Files.createFile(Paths.get("/home/farzad/Documents/s3/" + fileMetaData.getFileName()));
                    Files.write(p, output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            } else {
//                byte[] fileBytes = new byte[0];
//                try {
//                    fileBytes = IOUtils.toByteArray(fileInputStream);
//                    File f = File.createTempFile(new Date().toString(), ".mp4");
//                    FileOutputStream write = new FileOutputStream(f);
//                    write.write(fileBytes);
//                    write.close();
//                    f = new Watermark().doWatermark(f);
//                    java.nio.file.Path p = null;
//                    try {
//                        p = Files.createFile(Paths.get("/home/farzad/Documents/s3/" + fileMetaData.getFileName()));
//                        Files.write(p, Files.readAllBytes(Paths.get(f.toURI())));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    f.delete();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            System.out.println("end");
        }

//        ChallengeDescriptorService.getInstance().create(challengeDescriptor);

//            }

//        byte[] data = Base64.decodeBase64(s.substring(24, s.length() - 1));
//        BufferedImage b = ImageUtils.convertByteArrayToBufferedImage(data);
//        float scaleRatio = ImageUtils.calculateScaleRatio(b.getWidth(), 768);//todo from properties check is it need to optimize
//        if (scaleRatio > 0 && scaleRatio < 1) {
//            try {
//                BufferedImage sourceBufferedImage = ImageUtils.scaleImage(b, scaleRatio);
//                data = ImageUtils.imageToByteArray(sourceBufferedImage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        new S3Service().doSave(data,".jpg");
        String result = "" + description;
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/getStream")

    public Response newChallenge(@FormDataParam("id") String id) {

        System.out.println("start");

        return Response.status(200).entity("").build();
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
//        data = MediaConvertor.Convert(data);
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
    public String activation(String username) {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.findByUsername(username);
        if (user.getActivationCode().equals("")) {
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
    public User authenticate(String username) {
        User user = new UserDAOImpl().authenticate(username, "");
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

    @POST
    @Path("/signup")
    public Response signup(@FormDataParam("name") String name, @FormDataParam("lastname") String lastname, @FormDataParam("email") String email ,@FormDataParam("password") String password,@FormDataParam("username") String username) {
        System.out.println("sign up"+name);
        try {
            User entity = new User();
            entity.setFirstname(name);
            entity.setPassword(password);
            entity.setUsername(username);
            entity.setLastname(lastname);
            entity.setEmail(email);

            new UserDAOImpl().create(entity);
            String result = "<h1>RESTful Demo Application</h1>In real world application, a collection of users will be returned !!";
            return Response.status(200).entity(result).build();
        } catch (Exception e) {
            System.out.println("null");
        }
        return null;
    }


    @POST
    @Path("/checkuser")
   public Response checkUser(@FormDataParam("password") String password,@FormDataParam("username") String username) {
        System.out.println("sign in"+username);
        try {
            User entity = new User();
            entity.setPassword(password);
            entity.setUsername(username);
            if(new UserDAOImpl().exist(username)){
                System.out.println("user exist");

                User user = new UserDAOImpl().authenticate(username,password);
                if(user!=null){
                    System.out.println("pass ok");
                    String result = "<h1>RESTful Demo Application</h1>"+user.getFirstname()+","+user.getLastname();
                    return Response.status(200).entity(result).build();

                }
                System.out.println("pass fail");
            }
            System.out.println("user not exist");
            String result = "<h1>RESTful Demo Application</h1> login faild";
            return Response.status(200).entity(result).build();



        } catch (Exception e) {
            System.out.println("null");
        }
        return null;
    }
}