package com.tutorial.app.dao;

import com.tutorial.app.domain.User;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

public class UserDAO extends AbstractDAO<User>  {
    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public User findById(Long id){
        return get(id);
    }

    public long create(User user) {
        return persist(user).getId();
    }

    public List<User> findAll() {
        return (List<User>) currentSession().createCriteria(User.class).list();
    }
}