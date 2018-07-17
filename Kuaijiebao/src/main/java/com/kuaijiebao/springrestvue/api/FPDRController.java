package com.kuaijiebao.springrestvue.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.kuaijiebao.springrestvue.domain.FPDR;
import com.kuaijiebao.springrestvue.service.FPDRService;

@CrossOrigin
@RestController
@RequestMapping("/api/FP")
public class FPDRController {

    @Autowired
    FPDRService fpdrService;

    @GetMapping(path="/ShowFPDRActivity/User={id}")
    public List<FPDR> ShowFPDRByUserActivity(@PathVariable Long id){ return fpdrService.ShowFPDRByUserActivity(id);}

    @GetMapping(path="/ShowFPDRActivity/Product={id}")
    public List<FPDR> ShowFPDRByProductActivity(@PathVariable Long id){ return fpdrService.ShowFPDRByProductActivity(id);}

    @GetMapping(path="/FPDRDetailActivity/FPDR={id}")
    public FPDR FPDRDetailActivity(@PathVariable Long id){ return fpdrService.FPDRDetailActivity(id);}
}
