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

    @GetMapping(path="ShowFPAcitivity/{}")
    public List<FP> ShowFPActivity(){ return fpService.ShowFPActivity();}

    @GetMapping(path="/FPDetailAcitivity/{FP_id}")
    public FP FPDetailActivity(@PathVariable Long FP_id){ return fpService.FPDetailActivity(FP_id);}

    @PostMapping(path="/AddFPActivity/Sum={sum}&Name={name}&Price={price}&Productor={productor}")
    public void AddFPActivity(@PathVariable Long sum,@PathVariable String name,@PathVariable Float price,@PathVariable String productor){
        FP fp=new FP();
        fp.setSum(sum);
        fp.setName(name);
        fp.setPrice(price);
        fp.setProductor(productor);
        fpService.AddFPActivity(fp);
    }
}
