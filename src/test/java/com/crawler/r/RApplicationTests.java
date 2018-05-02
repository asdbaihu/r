package com.crawler.r;

import com.crawler.r.entity.TargetToken;
import com.crawler.r.entity.UserHolders;
import com.crawler.r.service.TargetTokenService;
import com.crawler.r.service.UserHoldersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RApplicationTests {

	@Autowired
	TargetTokenService tokenService;
	@Autowired
	UserHoldersService holdersService;

	@Test
	public void contextLoads() throws IOException {

		List<UserHolders> list=holdersService.getListHolders();

		if (list.size()>0){
			int i=0;
			for (UserHolders holders:list){
				i++;
				if (i==15){
					break;
				}
				try {
					TargetToken token=tokenService.findById(holders.getTargetId());
					String name=token.getName();
					String m=holders.getBalance();
					//余额
					String q=m.replace(" "+name,"").replace(",","").trim();
					if (token!=null){
						String html=token.getHtml();
						String n;
						try {
							n=html.substring(html.indexOf(0)+1,html.lastIndexOf("<b>")).replace(",","");
						}catch (Exception e){
							n=html.substring(html.indexOf(0)+1,html.lastIndexOf(name)).replace(",","");
						}
						Double c=Double.valueOf(n);
						Double d=Double.valueOf(q);
						NumberFormat numberFormat = NumberFormat.getInstance();
						numberFormat.setMaximumFractionDigits(5);
//						numberFormat.setRoundingMode(RoundingMode.HALF_UP);
						String result = numberFormat.format(d/c*100)+"%";
						System.out.println(result);
					}
					System.out.println(q);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

}
