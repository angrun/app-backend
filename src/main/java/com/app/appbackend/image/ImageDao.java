package com.app.appbackend.image;

import com.app.appbackend.exceptions.InvalidUserException;
import com.app.appbackend.user.User;
import com.app.appbackend.validation.Validation;
import org.apache.commons.io.FileUtils;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import static com.app.appbackend.utils.Utils.DEFAULT_PIC;
import static com.app.appbackend.utils.Utils.UPLOAD_ROOT;

@Service
public class ImageDao {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    Environment environment;

    @Autowired
    Validation validation;

    @PersistenceContext
    public EntityManager em;


    public Resource findOneImage(String filename) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + filename);
    }


    @Transactional
    public void createImage(MultipartFile file, String email) throws IOException, InvalidUserException {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User client = query.getResultList().get(0);

        Long userId =client.getId();

        String filename = file.getOriginalFilename();
        validation.validateImage(filename);

        boolean check = new File(UPLOAD_ROOT, filename).exists();

        if (!check) {

            if (!file.isEmpty()) {
                Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            }
        }

        Image image = new Image();
        image.setName(UPLOAD_ROOT + filename);
        image.setUserId(userId);
        image.setDateCreated(LocalDateTime.now());
        em.persist(image);

    }

    public List<Image> getUserImages(Long userId) throws IOException {

        TypedQuery<Image> query1 = em.createQuery("SELECT i FROM Image i WHERE i.userId = :userId ORDER BY i.dateCreated DESC ", Image.class);
        query1.setParameter("userId", userId);
        List<Image> resultList = query1.getResultList();
        List<Image> rsList = new ArrayList<>();

        if (resultList.isEmpty()) {
            resultList.add(new Image(UPLOAD_ROOT + DEFAULT_PIC, userId, LocalDateTime.now()));
        }

        for (Image aResultList : resultList) {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(aResultList.name));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            rsList.add(new Image(encodedString, userId, aResultList.dateCreated));
        }

        return rsList;
    }

}
