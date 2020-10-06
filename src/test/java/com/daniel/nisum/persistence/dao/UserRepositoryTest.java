package com.daniel.nisum.persistence.dao;

import com.daniel.nisum.persistence.model.Phone;
import com.daniel.nisum.persistence.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUser() {
        User user = new User();
        user.setEmail("myemail");
        user.setName("name");
        Phone phone = new Phone();
        phone.setNumber("1");
        phone.setCityCode("1");
        phone.setCountryCode("1");
        user.setPhones(Collections.singletonList(phone));
        entityManager.persist(phone);
        entityManager.persist(user);
        assertNotNull(userRepository.findByEmail("myemail"));
    }

    @Test
    public void whenFindByEmailNotFound_thenDontReturnUser() {
        User user = new User();
        user.setEmail("myemail");
        user.setName("name");
        Phone phone = new Phone();
        phone.setNumber("1");
        phone.setCityCode("1");
        phone.setCountryCode("1");
        user.setPhones(Collections.singletonList(phone));
        entityManager.persist(phone);
        entityManager.persist(user);
        assertNull(userRepository.findByEmail("noEmail"));
    }
}