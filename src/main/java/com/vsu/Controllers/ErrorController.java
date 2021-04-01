package com.vsu.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {

    public String errorPage(){
        return "error";
    }
}
