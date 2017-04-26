package com.zisezhixin.calculator.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zisezhixin.model.calculator.Factor;
import com.zisezhixin.service.calculator.FactorService;

@Controller
@RequestMapping(value = "/file")
public class IndexController {
    
    @Autowired
    FactorService factorService;
    
    @RequestMapping(value = "/index.html")
    public String upload(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
        List<Factor> factorList = factorService.getFactorList("229");
        request.setAttribute("factorList", factorList);
        return "index";
    }
}
