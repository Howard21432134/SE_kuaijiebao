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

    @PostMapping(path="/AddFPDealActivity/User={id}&FP={FP_id}&Time={time}&num={num}")
    public Boolean BuyFPActivity(@PathVariable Long id,@PathVariable Long FP_id,@PathVariable Date time,@PathVariable Long num,@PathVariable Float price){
        FP fp=fpService.FPDetailActivity(FP_id);
        if(fp.getSum()<num){ return false;}
        FPDeal fpDeal=new FPDeal();
        fpDeal.setNum(num);
        fpDeal.setProductId(FP_id);
        fpDeal.setUserId(id);
        fpDeal.setType(true);
        fpDealService.AddFPDealActivity(fpDeal);
        FPDR fpdr=new FPDR();
        fpdr.setType(1);
        fpdr.setUserId(id);
        fpdr.setProductId(FP_id);
        fpdr.setNum(num);
        fpdr.setPrice(fp.getPrice());
        fpdr.setTime(time);
        fpdr.setDealId(fpDeal.getId());
        fpdrService.AddFPDRActivity(fpdr);
        fp.setSum(fp.getSum()-num);
        fpService.AddFPActivity(fp);
        return true;
    }

    @DeleteMapping("/DeleteFPDealActivity/FPDeal={id}&Time={time}")
    public void CancelFPDealActivity(@PathVariable Long id,@PathVariable Date time){
        FPDeal fpDeal=fpDealService.FPDealActivity(id);
        fpDeal.setType(false);
        FP fp=fpService.FPDetailActivity(fpDeal.getProductId());
        FPDR fpdr=new FPDR();
        fpdr.setDealId(fpDeal.getId());
        fpdr.setTime(time);
        fpdr.setPrice(fp.getPrice());
        fpdr.setProductId(fp.getId());
        fpdr.setUserId(fpDeal.getUserId());
        fpdr.setNum(fpDeal.getNum());
        fpdr.setType(3);
        fpdrService.AddFPDRActivity(fpdr);
        fpDealService.AddFPDealActivity(fpDeal);
    }

    @GetMapping("/ShowFPDealActivity/User={id}")
    public List<FPDeal> ShowFPDealActivity(@PathVariable Long id){ return fpDealService.ShowFPDealActivity(id);}

    @GetMapping("/FPDealDetailActivity/FPDeal={id}")
    public FPDeal FPDealDetailActivity(@PathVariable Long id){ return fpDealService.FPDealActivity(id);}
}
