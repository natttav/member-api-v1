package com.nattav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nattav.config.JwtTokenUtil;
import com.nattav.model.CommonResponse;
import com.nattav.model.JWTRequest;
import com.nattav.model.MemberResponse;
import com.nattav.model.UserDao;
import com.nattav.model.common.CommonResponseDataObject;
import com.nattav.model.common.CommonStatusObject;
@Service
public class MemberService {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	private final String verifyAuthURL = "http://localhost:8090/authentication/verify";

	private static RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity<?> getMemberLevel(String requestToken) {

		JWTRequest jwtRequest = new JWTRequest(requestToken);
		ResponseEntity<CommonResponseDataObject> restServiceResponse = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(requestToken);
		HttpEntity<JWTRequest> entity = new HttpEntity<>(jwtRequest, headers);
		CommonStatusObject status = new CommonStatusObject();
		try {
			restServiceResponse = restTemplate.exchange(verifyAuthURL, HttpMethod.POST, entity, CommonResponseDataObject.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status.setCode( "E999");
			status.setMessage("Unhandled Exception");
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setStatus(status);
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.ok(commonResponse);
		}
		
		
		if (restServiceResponse.getStatusCode().value() == 200 && restServiceResponse.getBody().getStatus().getCode().equals("0000")) {
			String username = jwtTokenUtil.getUsernameFromToken(requestToken);
			UserDao user = userDetailsService.getUserObjByUsername(username);
			CommonResponseDataObject commonResponse = new CommonResponseDataObject();
			
			status.setCode( "0000");
			status.setMessage( "Success");
			commonResponse.setStatus(status);

			MemberResponse member = new MemberResponse();
			member.setSalary(user.getSalary());
			member.setUsername(username);
			member.setMember_level(user.getMember_level());
			commonResponse.setData(member);
			
			return ResponseEntity.ok(commonResponse);
		}else {
			status.setCode( "E401");
			status.setMessage( "Unauthorized");
			CommonResponseDataObject commonResponse = new CommonResponseDataObject();
			commonResponse.setStatus(status);
			commonResponse.setData(restServiceResponse.getBody().getData());
			ResponseEntity.status(HttpStatus.UNAUTHORIZED);
			return ResponseEntity.ok(commonResponse);
		}
	}
	
}
