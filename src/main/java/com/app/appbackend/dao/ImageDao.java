package com.app.appbackend.dao;

import com.app.appbackend.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class ImageDao {

    private static String UPLOAD_ROOT = "src/main/resources/static";
    public MultipartFile ues;

    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    UsersDao usersDao;

    @PersistenceContext
    public EntityManager em;


    //Probably works
    public Resource findOneImage(String filename) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + filename);
    }


    public File getImage(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getResource(filename));
        File fi = new File(classLoader.getResource(filename).getFile());
        return fi;
    }

    @Transactional
    public void createImage(MultipartFile file) throws IOException {

        ues = file;

        if (!file.isEmpty()) {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));

            Image image = new Image();
            image.setName("http://localhost:8081/" + file.getOriginalFilename());
            image.setUserId(1L);
            image.setDateCreated(LocalDate.now());
            em.persist(image);

        }
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
