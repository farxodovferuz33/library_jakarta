package com.example.cloned_library.dao;

import com.example.cloned_library.entity.AuthUser;
import lombok.NonNull;


import java.util.Optional;

public class AuthUserDAO extends BaseDAO<AuthUser, String> {


    public Optional<AuthUser> findByEmail(@NonNull String email) {
        try {
            begin();
            AuthUser authUser = em.createQuery("select t from AuthUser t where t.email ilike :email", AuthUser.class)
                    .setParameter("email", email)
                    .getSingleResult();
            commit();
            return Optional.ofNullable(authUser);
        } catch (Exception e) {
//            e.printStackTrace();
            rollBack();
            return Optional.empty();
        }
    }

}
