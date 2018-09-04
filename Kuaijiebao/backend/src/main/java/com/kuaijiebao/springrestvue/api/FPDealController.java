package com.kuaijiebao.springrestvue.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public Boolean createFPDeal(@RequestBody  FPDeal fpDeal){
        FP fp = fpService.findByFpId(fpDeal.getProductId());
        if(fp.getSum()<fpDeal.getNum()){ return false;}
        fp.setSum(fp.getSum()-fpDeal.getNum());
        fpDeal.setType(true);
        fpService.update(fp);
        fpDealService.save(fpDeal);
        FPDR fpdr = new FPDR();
        fpdr.setDealId(fpDeal.getDealId());
        fpdr.setUserId(fpDeal.getUserId());
        fpdr.setProductId(fpDeal.getProductId());
        fpdr.setTime(fpDeal.getStarttime());
        fpdr.setType(1);
        fpdrService.save(fpdr);
        return true;
    }

    @PostMapping("/EditFPDealActivity")
    public FPDeal update(@RequestBody FPDR fpdr){
        FPDeal fpDeal = fpDealService.findByDealId(fpdr.getDealId());
        if(fpDeal.getType()==false){return fpDeal;}
        fpDeal.setType(false);
        fpDealService.update(fpDeal);
        fpdrService.save(fpdr);
        return fpDeal;
    }

    @GetMapping("/ShowFPDealActivity/User={id}")
    public List<FPDeal> getByUserId(@PathVariable Long id){ return fpDealService.findAllByUserId(id);}

    @GetMapping("/FPDealDetailActivity/FPDeal={id}")
    public FPDeal getByDealId(@PathVariable Long id){ return fpDealService.findByDealId(id);}
}
