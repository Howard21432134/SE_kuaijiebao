package com.kuaijiebao.springrestvue.api;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.kuaijiebao.springrestvue.domain.FP;
import com.kuaijiebao.springrestvue.service.FPService;

@CrossOrigin
@RestController
@RequestMapping("/api/FP")
public class FPController {

    @Autowired
    FPService fpService;

    @GetMapping(path="/ShowFPActivity")
    public List<FP> getAllFPs(){ return fpService.findAll();}

    @GetMapping(path="/FPDetailActivity/{FP_id}")
    public FP getByFpId(@PathVariable Long FP_id){ return fpService.findByFpId(FP_id);}

    @PostMapping(path="/AddFPActivity")
    public FP create(@RequestBody FP fp){
        return fpService.save(fp);
    }

    @PutMapping(path="/EditFPActivity")
    public FP update(@RequestBody FP fp){ return fpService.save(fp);}
}
