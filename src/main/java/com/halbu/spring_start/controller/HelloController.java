package com.halbu.spring_start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!!");
        return "hello"; //resources/templates/hello.html이랑 연결
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ //외부에서 매개변수를 받아야함. -> url에 get방식으로 파라미터를 전달해야 함.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http 프로토콜 바디에 아래 리턴값을 그대로 전달
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // 그냥 바로 출력함
    }

    @GetMapping("hello-api")
    @ResponseBody // viewResolver 대신 HttpMessageConverter가 동작함.
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //json 형태로 반환
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

    }
}
