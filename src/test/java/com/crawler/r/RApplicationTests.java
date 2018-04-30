package com.crawler.r;

import com.crawler.r.entity.TargetToken;
import com.crawler.r.entity.TokenTransfers;
import com.crawler.r.service.TargetTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RApplicationTests {

	@Autowired
	TargetTokenService targetTokenService;

	@Test
	public void contextLoads() throws IOException {

		File file = new File("D:\\work\\work.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "";
		List<String> list = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}
		List<TokenTransfers> list1=new ArrayList<>();
		for (int i=1;i<=list.size();i++){
			String[] s=list.get(i).split(",");
		}
		System.out.println("hah");
	}

}
