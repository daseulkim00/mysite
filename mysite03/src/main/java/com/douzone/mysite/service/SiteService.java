package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;

	public SiteVo getsite() {
		
		return siteRepository.find();
	}
	
	public boolean update(SiteVo siteVo) {      //controller에서 SiteVo sitevo = (SiteVo)siteService.getsite(); 넣어줫으니깐 sitevo를 가져와야함
		
		return siteRepository.update(siteVo);  // vo값을 가져와서 넣어줘야한다.

	}
	
}