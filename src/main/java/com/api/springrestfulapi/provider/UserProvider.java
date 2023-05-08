package com.api.springrestfulapi.provider;

import org.apache.ibatis.annotations.Param;
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

    public static String deleteById() {
        return new SQL()
        {{
            DELETE_FROM("users");
            WHERE("id=#{id}");
        }}.toString();
    }

    public static String updateById()
    {
        return new SQL(){{
            UPDATE("users");
            SET("name=#{user.name},gender=#{user.gender},address=#{user.address}");
            WHERE("WHERE id=#{id}");
        }}.toString();

    }

}
