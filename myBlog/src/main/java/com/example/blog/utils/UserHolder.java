package com.example.blog.utils;


import com.example.blog.DTO.UserDTO;
/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void saveUser(UserDTO user){
        tl.set(user);
    }

    public static UserDTO getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
