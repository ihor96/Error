package com.example.javaspringkw11.Dao;

import com.example.javaspringkw11.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactDAO extends JpaRepository<Contact,Integer> {
}
