package com.rhc.lab.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rhc.lab.dao.BookingRepository;
import com.rhc.lab.dao.VenueRepository;
import com.rhc.lab.domain.BookingRequest;
import com.rhc.lab.domain.Venue;
import com.rhc.lab.service.proxy.LabProxySender;

/**
 * 
 * This class is the home controller for lab web application.
 * 
 */
@Controller
public class LabController {

	@Autowired
	private VenueRepository venueDao;

	@Autowired
	private BookingRepository bookingRequestDao;

	@Autowired
	private LabProxySender labProxySender;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {

		List<Venue> venues = (List<Venue>) venueDao.findAll();
		Collections.sort(venues);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Associate Consultant Bootcamp");
		modelAndView.addObject("venues", venues);
		modelAndView.setViewName("index");

		return modelAndView;
	}

	@RequestMapping(value = "/bookingRequest", method = RequestMethod.GET)
	public String newbookingRequest(Model model) {
		model.addAttribute("bookingRequest", new BookingRequest());
		return "bookingRequest";
	}

	@RequestMapping(value = "/bookingRequest", method = RequestMethod.POST)
	public String submitBookingRequest(
			@ModelAttribute BookingRequest bookingRequest, Model model) {

		// labProxySender.submit(bookingRequest);

		model.addAttribute("bookingRequest", bookingRequest);
		return "index";
	}

	@RequestMapping(value = "/venue", method = RequestMethod.GET)
	public String newVenue(Model model) {
		model.addAttribute("venue", new Venue());
		return "venue";
	}

	@RequestMapping(value = "/venue", method = RequestMethod.POST)
	public String submitVenue(@ModelAttribute Venue venue, Model model) {
		model.addAttribute("venue", venue);
		return "index";
	}
}
