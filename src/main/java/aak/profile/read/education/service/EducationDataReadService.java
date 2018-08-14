package aak.profile.read.education.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aak.common.vo.education.Education;
import aak.profile.read.education.capability.EducationDataReadCapability;
import aak.profile.vo.ProfileInfoRequestContext;

@RestController

public class EducationDataReadService {

	@RequestMapping(value = "/read/education", method = RequestMethod.POST)
	/*
	 * 
	 */
	public ResponseEntity<List<Education>> getEducationInfo(
			@RequestBody ProfileInfoRequestContext requestContext) throws Exception {
		System.out.println("----------getEducationInfo request----------");
		// rules, limit, security checking
		return new ResponseEntity<List<Education>>(
				EducationDataReadCapability.getEducationInfo(requestContext), HttpStatus.OK);
	}

}
