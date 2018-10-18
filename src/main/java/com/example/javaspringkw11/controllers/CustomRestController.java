package com.example.javaspringkw11.controllers;

import com.example.javaspringkw11.Dao.ContactDAO;
import com.example.javaspringkw11.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class CustomRestController {
    @Autowired
private ContactDAO contactDAO;

    @PostMapping("/saveAJAX")
    public List<Contact> saveAJAX (@RequestBody Contact contact){
       contactDAO.save(contact);
        return contactDAO.findAll();
    }
    
    
    @PostMapping("/upload")    
    public void upload(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String phone,
            @RequestParam MultipartFile image
    ) throws IOException {
        Contact contact = new Contact(name, surname, phone);
        contact.setAvatar(image.getOriginalFilename());
        String path = System.getProperty("user.home")//прописую шлях де буде зберігатись картинка
                +File.separator
                +"images"
                +File.separator
                +image.getOriginalFilename();
        image.transferTo(new File(path));

        contact.setAvatar(image.getOriginalFilename());//Вказуємо імя файла який ми зберігаєм

        contactDAO.save(contact);
        System.out.println(contact);

    }
}
