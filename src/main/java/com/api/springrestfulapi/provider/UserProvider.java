package com.api.springrestfulapi.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public static  String getAllUsers(String filterName){
        return  new SQL(){{
            SELECT("*");
            FROM("users");
            // filter for the description text
            if (!filterName.isEmpty()){
                WHERE("upper(name) like upper('%'||#{filterName}||'%')");
            }
        }}.toString();
    }

}
