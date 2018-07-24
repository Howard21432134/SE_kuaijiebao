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
    public List<FP> ShowFPActivity(){ return fpService.ShowFPActivity();}

    @GetMapping(path="/FPDetailActivity/{FP_id}")
    public FP FPDetailActivity(@PathVariable Long FP_id){ return fpService.FPDetailActivity(FP_id);}

    @PostMapping(path="/AddFPActivity")
    public FP AddFPActivity(@RequestBody FP fp){
        return fpService.AddFPActivity(fp);
    }
}
