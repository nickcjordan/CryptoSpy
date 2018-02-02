package crypto.spy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import crypto.Log;
import crypto.spy.api.CryptoCompareAPI;

@Controller
public class BaseController {
	
    @RequestMapping(value = "/")
    public String init(Model model) {
    	Log.info("READY");
    	update(model);
    	return "home";
    }
    
	protected void update(Model model) {
		CryptoCompareAPI api = new CryptoCompareAPI();
		model.addAttribute("coins", api.getCoins().values());
		model.addAttribute("profit", CryptoCompareAPI.previousProfitPrice);
	}
	
}
