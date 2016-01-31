package com.teaman.data.parse.mapper;

import com.teaman.data.parse.UserEntity;
import com.teaman.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * <h1> UserEntityMapper </h1>
 * <p>
 * Transforms {@link UserEntity} in the data layer to {@link User} in the domain layer.
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/30/16
 */
@Singleton
public class UserEntityMapper {

    @Inject
    public UserEntityMapper() { }

    /**
     * <p>
     * Maps a {@link UserEntity} to a {@link User}
     * </p>
     *
     * @param userEntity    UserEntity object from database
     * @return              User object from domain model
     */
    public User map(UserEntity userEntity) {
        User user = null;
        if(userEntity != null) {
            user = new User(userEntity.getObjectId());
            user.setEmail(userEntity.getEmail());
            user.setUsername(userEntity.getUsername());
        }

        return user;
    }

    /**
     * <p>
     * Maps a collection of {@link UserEntity} to a list of {@link User}
     * </p>
     *
     * @param userEntities  Collection of User entities from database
     * @return              List of User objects from domain model
     */
    public List<User> map(Collection<UserEntity> userEntities) {
        List<User> userList = new ArrayList<>();
        User user;
        for(UserEntity entity : userEntities) {
            user = map(entity);
            if(user != null) {
                userList.add(user);
            }
        }

        return userList;
    }
}
