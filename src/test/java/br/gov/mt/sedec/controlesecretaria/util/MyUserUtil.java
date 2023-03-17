package br.gov.mt.sedec.controlesecretaria.util;

import br.gov.mt.sedec.controlesecretaria.domain.myUser.MyUser;

import java.util.Arrays;

public class MyUserUtil {

    public static MyUser returnSampleUser() {
        return MyUser.builder()
                .id(1L)
                .username("testuser@gmail.com")
                // password = 123456
                .password("$2a$10$PxHa0k.0Wv9uaJjVqYI50.nt.w/dJ/8ysZh4Qww2cTumjbHzcIdem")
                .roles(Arrays.asList(MyRoleUtil.returnSampleRole()))
                .build();
    }
    public static MyUser returnSamplePasswordUpdatedUser() {
        return MyUser.builder()
                .id(1L)
                .username("testuser@gmail.com")
                //password = 1234567
                .password("$2a$10$iSQFLg0AG7tCq59oky3cTO4sUjF6mbR/QgWzyRvvMD/jpw8QPTNDm")
                .roles(Arrays.asList(MyRoleUtil.returnSampleRole()))
                .build();
    }
}
