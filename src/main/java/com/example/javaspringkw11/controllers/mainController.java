package com.example.javaspringkw11.controllers;

import com.example.javaspringkw11.Dao.ContactDAO;
import com.example.javaspringkw11.Services.UserService;
import com.example.javaspringkw11.models.Contact;
import com.example.javaspringkw11.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class mainController {
    @Autowired
    private ContactDAO contactDAO;

    @GetMapping("/")
    public String index2(Model model) {
        model.addAttribute("message", "Ihor");
        return "index";
    }

//    @PostMapping("/save")//Використовують, зоб в урлі не містились такі дані як імя,пароль
//    public String save(Contact contact)
//    {
//        contactDAO.save(contact);
//        System.out.println(contact);
//        return "redirect:/";// коли метод сейв відпрацює, відпрацює після нього ще метод з урлою "/"
//    }


    @PostMapping("/save")//Використовують, зоб в урлі не містились такі дані як імя,пароль
    public String save(
            Contact contact,
            @RequestParam MultipartFile image) throws IOException {        //Зберігаю файл в файлову систему
        String path = System.getProperty("user.home")//прописую шлях де буде зберігатись картинка
                + File.separator
                + "images"
                + File.separator
                + image.getOriginalFilename();
        image.transferTo(new File(path));

        contact.setAvatar(image.getOriginalFilename());//Вказуємо імя файла який ми зберігаєм

        contactDAO.save(contact);
        System.out.println(contact);
        return "redirect:/";   // коли метод сейв відпрацює, відпрацює після нього ще метод з урлою "/"
    }


    @GetMapping("/allContacts")
    public String allContacts(Model model) {
        List<Contact> contacts = contactDAO.findAll();
        model.addAttribute("contacts", contacts);
        return "contactList";
    }

    @GetMapping("/contactDetail/{id}")
    public String resolveSingleContact(@PathVariable int id, Model model) {
        Contact contact = contactDAO.findById(id).get();
        model.addAttribute("contact", contact);
        return "singleContact";
    }


    @PostMapping("/updateContact")
    public String updateContact(Contact contact) {
        contactDAO.save(contact);
        return "redirect:/allContacts";
    }

    @PostMapping("/successURL")
    public String successURL() {

        return "index";
    }

@Autowired
private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
    public String saveUser(User user){
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userService.save(user);
        return "redirect:/login";
    }
}

