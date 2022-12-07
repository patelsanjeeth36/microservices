package com.sanjeeth.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("kavita patel");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("san", "diksha"));
	}
	
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getVersion1RequestParm() {
		return new PersonV1("version one");
	}
	
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getVersion2RequestParm() {
		return new PersonV2(new Name("san", "diksha"));
	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getVersion1OfPersonRequestHeader() {
		return new PersonV1("Request Header one");
	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=2")
	public PersonV2 getVersion2OfPersonRequestHeader() {
		return new PersonV2(new Name("san", "diksha"));
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
 
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
