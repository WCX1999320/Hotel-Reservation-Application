package org.example;

import org.example.model.Customer;
import org.junit.Test;

public class ModelTest {
    @Test
    public void test1(){
        Customer customer=new Customer("first","second","j@domain.com");
        System.out.println(customer);
    }

    /**
     * Test the email validation
     */
    @Test
    public void test2(){
        Customer customer=new Customer("first","second","email");
        System.out.println(customer);
    }
}
