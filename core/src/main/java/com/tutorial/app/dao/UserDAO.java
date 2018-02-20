package com.tutorial.app.dao;

import com.tutorial.app.domain.User;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

public class UserDAO extends AbstractDAO<User>  {
    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public User getById(Long id){
        return (User) currentSession().get(User.class, id);
    }

    public Long create(User user) {
        return (Long) persist(user).getId();
    }

    public List<User> getAll() {
        return (List<User>) currentSession().createCriteria(User.class).list();
    }

    public void update(User user) {
        currentSession().update(user);
    }

    public void remove(User user) {
        currentSession().delete(user);
    }
}