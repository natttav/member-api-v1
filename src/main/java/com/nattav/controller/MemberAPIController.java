package com.nattav.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nattav.service.MemberService;

@RestController
public class MemberAPIController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/member/level", method = RequestMethod.GET)
	public ResponseEntity<?> getMember(HttpServletRequest request) {
		String requestToken = request.getHeader("Authorization").replaceAll("Bearer ", "");
		
		return memberService.getMemberLevel(requestToken);
	}
}
