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

    @PostMapping(path="/AddFPDRActivity")
    public FPDR createFPDR(@RequestBody FPDR fpdr){
        return fpdrService.save(fpdr);
    }

    @GetMapping(path="/ShowFPDRActivity/User={id}")
    public List<FPDR> getByUserId(@PathVariable Long id){ return fpdrService.findAllByUserId(id);}

    @GetMapping(path="/FPDRDetailActivity/FPDR={id}")
    public FPDR getByRecordId(@PathVariable Long id){ return fpdrService.findByRecordId(id);}

}
