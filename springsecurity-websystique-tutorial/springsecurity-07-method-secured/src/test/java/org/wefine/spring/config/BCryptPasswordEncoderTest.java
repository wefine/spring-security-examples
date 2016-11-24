package org.wefine.spring.config;
 
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
public class BCryptPasswordEncoderTest {

    @Test
    public void testEncrypt() {
        String password = "abc125";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
    }

}