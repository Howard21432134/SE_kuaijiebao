package com.kuaijiebao.springrestvue.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Date;

import com.kuaijiebao.springrestvue.domain.FP;
import com.kuaijiebao.springrestvue.domain.FPDeal;
import com.kuaijiebao.springrestvue.domain.FPDR;
import com.kuaijiebao.springrestvue.service.FPDealService;
import com.kuaijiebao.springrestvue.service.FPDRService;
import com.kuaijiebao.springrestvue.service.FPService;
@CrossOrigin
@RestController
@RequestMapping("/api/FP")
public class FPDealController {
    @Autowired
    FPDRService fpdrService;
    @Autowired
    FPDealService fpDealService;
    @Autowired
    FPService fpService;

    @PostMapping(path="/AddFPDealActivity")
    public Boolean BuyFPActivity(@RequestBody FPDR fpdr){
        FPDeal fpDeal = new FPDeal();
        fpDeal.setProductId(fpdr.getProductId());
        fpDeal.setUserId(fpdr.getUserId());
        fpDeal.setNum(fpdr.getNum());
        FP fp=fpService.FPDetailActivity(fpDeal.getProductId());
        if(fp.getSum()<fpDeal.getNum()){ return false;}
        fpDeal.setType(true);
        fpDealService.AddFPDealActivity(fpDeal);
        fp.setSum(fp.getSum()-fpDeal.getNum());
        fpService.AddFPActivity(fp);
        fpdr.setDealId(fpDeal.getId());
        fpdr.setType(1);
        fpdr.setPrice(fp.getPrice());
        fpdrService.AddFPDRActivity(fpdr);
        return true;
    }

    @PutMapping("/CancelFPDealActivity")
    public Boolean CancelFPDealActivity(@RequestBody FPDR fpdr){
        FPDeal fpDeal = fpDealService.FPDealActivity(fpdr.getDealId());
        if(fpDeal.getType()!=true){ return false;}
        fpDeal.setType(false);
        fpDealService.AddFPDealActivity(fpDeal);
        FP fp = fpService.FPDetailActivity(fpDeal.getProductId());
        fp.setSum(fp.getSum()+fpDeal.getNum());
        fpService.AddFPActivity(fp);
        fpdr.setType(3);
        fpdr.setPrice(fp.getPrice());
        fpdr.setProductId(fpDeal.getProductId());
        fpdr.setUserId(fpDeal.getUserId());
        fpdr.setNum(fpDeal.getNum());
        fpdrService.AddFPDRActivity(fpdr);
        return true;
    }

    @GetMapping("/ShowFPDealActivity/User={id}")
    public List<FPDeal> ShowFPDealActivity(@PathVariable Long id){ return fpDealService.ShowFPDealActivity(id);}

    @GetMapping("/FPDealDetailActivity/FPDeal={id}")
    public FPDeal FPDealDetailActivity(@PathVariable Long id){ return fpDealService.FPDealActivity(id);}
}
