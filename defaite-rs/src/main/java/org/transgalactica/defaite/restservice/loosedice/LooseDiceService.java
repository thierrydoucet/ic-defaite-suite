package org.transgalactica.defaite.restservice.loosedice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.transgalactica.defaite.util.DeLoose;

@RestController
@RequestMapping("/loosedice")
public class LooseDiceService {

	@RequestMapping(value="/cast", method = RequestMethod.GET)
	public DeLoose castLooseDice() {
		return DeLoose.lancerLeDe();
	}

	@RequestMapping(value = "/sides", method = RequestMethod.GET)
	public DeLoose[] getAllSides() {
		return DeLoose.values();
	}
}