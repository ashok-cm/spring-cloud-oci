/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package util;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.oracle.cloud.spring.core.compartment.StaticCompartmentProvider;
import com.oracle.cloud.spring.core.util.OCIObjectMapper;
import org.junit.Assert;
import org.junit.Test;
public class OCIObjectMapperTests {

    @Test
    public void testOCIObjectMapper() {
        OCIObjectMapper ociObjectMapper = new OCIObjectMapper();
        User user = new User(1231, "demo", 12);
        String userJson = "{\n" +
                "  \"userId\" : 1231,\n" +
                "  \"userName\" : \"demo\",\n" +
                "  \"age\" : 12\n" +
                "}";

        String outputUserJson = ociObjectMapper.toPrintableString(user);
        outputUserJson = outputUserJson.replaceAll("\\n","").replaceAll("\\r","");
        userJson = userJson.replaceAll("\\n","").replaceAll("\\r","");
        Assert.assertNotNull(outputUserJson);
        Assert.assertEquals(outputUserJson, userJson);
    }


    public static void main1(String[] args) {
        User user = new User(1231, "______", 12);
        String userJson = "{ \"userId\":1231,\"userName\":\"______\",\"age\":12}";

        JSONPObject jsonpObject = new JSONPObject(userJson, User.class);

        System.out.println(jsonpObject);
        System.out.println(jsonpObject.getValue().toString());
        User value = (User) jsonpObject.getValue();
        System.out.println(value);
    }

    static class User {
        private int userId;

        public User(int userId, String userName, int age) {
            this.userId = userId;
            this.userName = userName;
            this.age = age;
        }

        private String userName;
        private int age;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof User)) return false;
            User user = (User) obj;
            return (user.getUserId() == this.userId) &&
                    (user.getUserName().equals(this.userId)) &&
                    (user.getAge() == this.age);
        }
    }
}
