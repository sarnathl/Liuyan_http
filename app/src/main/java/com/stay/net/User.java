package com.stay.net;

/**
 * Created by apple on 15/10/7.
 */
public class User {
    //{"ret":200,"data":{"id":"2","account":"stay4it","email":"stay4it@163.com","username":"stay","password":"123456","avatar":"uploads\/avatar\/f6fe4487d7210445aeee867b58c490bd.jpg","token":"DGPIA8mJ5EkWhb3byLrN2R\/KrCzPtmXwTJUQlMAkLDs="},"msg":""}
    public String id;
    public String account;
    public String email;
    public String username;
    public String token;

    @Override
    public String toString() {
        return username+"----"+email;
    }
}
