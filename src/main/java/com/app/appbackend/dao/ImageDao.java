package com.app.appbackend.dao;

import com.app.appbackend.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.app.appbackend.utils.Utils.DEFAULT_PIC;
import static com.app.appbackend.utils.Utils.SERVER_ADD;
import static com.app.appbackend.utils.Utils.UPLOAD_ROOT;

@Service
public class ImageDao {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    Environment environment;

    private Pattern pattern;
    private Matcher matcher;

    @PersistenceContext
    public EntityManager em;


    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";


    public Resource findOneImage(String filename) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + filename);
    }


    @Transactional
    public void createImage(MultipartFile file) throws IOException {

        pattern = Pattern.compile(IMAGE_PATTERN);

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);

        matcher = pattern.matcher(fileName);
        System.out.println(matcher.matches());




        System.out.println(file.getSize());

        boolean check = new File(UPLOAD_ROOT, file.getOriginalFilename()).exists();

        if (!check) {

            if (!file.isEmpty()) {
                Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            }
        }

        Image image = new Image();
        image.setName(SERVER_ADD + ":" + environment.getProperty("server.port") + "/" + UPLOAD_ROOT + file.getOriginalFilename());
        image.setUserId(1L);
        image.setDateCreated(LocalDateTime.now());
        em.persist(image);

    }

    public List<Image> getUserImages(Long userId) {

        TypedQuery<Image> query1 = em.createQuery("SELECT i FROM Image i WHERE i.userId = :userId ORDER BY i.dateCreated DESC ", Image.class);
        query1.setParameter("userId", userId);
        List<Image> resultList = query1.getResultList();

        if (resultList.isEmpty()) {
            resultList.add(new Image(SERVER_ADD + ":" + environment.getProperty("server.port") + DEFAULT_PIC, userId, LocalDateTime.now()));
        }

        return resultList;
    }


//    @Bean
//    CommandLineRunner setUp(ImageRepository imageRepository) throws IOException {
//
//        return (args) -> {
////            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));
//
////            Files.createDirectory(Paths.get(UPLOAD_ROOT));
//            FileCopyUtils.copy("Test file", new FileWriter(UPLOAD_ROOT + "/test"));
//            imageRepository.save(new Image("test"));
//
//            FileCopyUtils.copy("Test file2", new FileWriter(UPLOAD_ROOT + "/test2"));
//            imageRepository.save(new Image("test2"));
//
//            FileCopyUtils.copy("Test file3", new FileWriter(UPLOAD_ROOT + "/test3"));
//            imageRepository.save(new Image("test3"));
//
//        };
//
//    }

//    public void deleteImage(String filename) throws IOException {
//
//        final Image byName = imageRepository.findByName(filename);
//        imageRepository.delete(byName);
//        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
//    }
//
//    public Page<Image> findPage(Pageable pageable) {
//        return imageRepository.findAll(pageable);
//    }

}
