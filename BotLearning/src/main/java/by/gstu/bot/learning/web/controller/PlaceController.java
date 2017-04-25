package by.gstu.bot.learning.web.controller;

import by.gstu.bot.learning.domain.Place;
import by.gstu.bot.learning.service.PlaceService;
import by.gstu.bot.learning.web.dto.JQueryDataTableParamModel;
import by.gstu.bot.learning.web.dto.JsonDTO;
import by.gstu.bot.learning.web.dto.SortHelper;
import by.gstu.bot.learning.web.validator.PlaceFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class PlaceController {

	@Autowired
	public PlaceService placeService;
	@Autowired
	PlaceFormValidator placeFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(placeFormValidator);
	}

	@RequestMapping("/index")
	public String listPlaces() {

		return "jsp/places/table";
	}
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping(value = "/api/places", method = RequestMethod.GET)
	public
	@ResponseBody
	Object getAll(JQueryDataTableParamModel param) {

		List<Place> placeList = placeService.list();
		int iTotalRecords = placeList.size();

		//Search
		if (!StringUtils.isEmpty(param.getsSearch())) {
			placeList = (List<Place>) SortHelper.search(placeList, param.getsSearch());
		}

		//Sorting
		int sortColumnIndex = param.getiSortCol_0();
		String sortDirection = param.getsSortDir_0();
		switch (sortColumnIndex) {
			case 0: {
				placeList = SortHelper.order(sortDirection, placeList, new Comparator<Place>() {
					@Override
					public int compare(Place o1, Place o2) {
						return Integer.compare(o1.getId(), o2.getId());
					}
				});
//                placeList.stream()
//                        .sorted((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
			}
			break;
			case 1: {
				placeList = SortHelper.order(sortDirection, placeList, new Comparator<Place>() {
					@Override
					public int compare(Place o1, Place o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
			}
			break;
			case 2: {
				placeList = SortHelper.order(sortDirection, placeList, new Comparator<Place>() {
					@Override
					public int compare(Place o1, Place o2) {
						return o1.getType().compareTo(o2.getType());
					}
				});
			}
			break;
			case 3: {
				placeList = SortHelper.order(sortDirection, placeList, new Comparator<Place>() {
					@Override
					public int compare(Place o1, Place o2) {
						return o1.getDescription().compareTo(o2.getDescription());
					}
				});
			}
			break;
			case 4: {
				placeList = SortHelper.order(sortDirection, placeList, new Comparator<Place>() {
					@Override
					public int compare(Place o1, Place o2) {
						return o1.getType().compareTo(o2.getType());
					}
				});
			}
			break;
		}
//        if (sortDirection.equals("desc")) // asc or desc
//            Collections.reverse(placeList);

		//Pagination
		List<Place> result = placeList.stream().skip(param.getiDisplayStart()).limit(param.getiDisplayLength()).collect(Collectors.toList());
		JsonDTO jsonDTO = new JsonDTO(param.getsEcho(), iTotalRecords, placeList.size(), result);

		return jsonDTO;
	}

	// show place
	@RequestMapping(value = "/places/{id}", method = RequestMethod.GET)
	public String showPlace(@PathVariable("id") int id, Model model, Locale locale) {

		Place place = placeService.get(id);
		if (place == null) {
			model.addAttribute("css", "danger");
			ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
			bean.setBasename("messages");
			String msg = bean.getMessage("msg.place_not_found", null, locale);
			model.addAttribute("msg", msg);
		}
		model.addAttribute("place", place);

		return "jsp/places/show";

	}

	// save or update place
	@RequestMapping(value = "/places/save", method = RequestMethod.POST)
	public String saveOrUpdatePlace(@ModelAttribute("placeForm") @Validated Place place,
									 BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale) {

		if (result.hasErrors()) {
//			populateDefaultModel(model);
			return "jsp/places/placeForm";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			if (place.getId() == null || place.getId() == 0) {
				redirectAttributes.addFlashAttribute("msg", "place_added_successfully");
				placeService.add(place);
			} else {
				ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
				bean.setBasename("messages");
				String msg = bean.getMessage("msg.place_updated_successfully", null, locale);
				redirectAttributes.addFlashAttribute("msg", msg);
				placeService.edit(place);
			}

			// POST/REDIRECT/GET
			return "redirect:/places/" + place.getId();
		}

	}

	// show add place form
	@RequestMapping(value = "/places/add", method = RequestMethod.GET)
	public String showAddPlaceForm(Model model) {

		Place place = new Place();

		// set default value
		place.setId(0);
		place.setName("");
		place.setType("");
		place.setDescription("");
		place.setWorktime("");

		model.addAttribute("placeForm", place);
		model.addAttribute("isNew", true);

//		populateDefaultModel(model);

		return "jsp/places/placeForm";

	}

	// show update form
	@RequestMapping(value = "/places/update/{id}", method = RequestMethod.GET)
	public String showUpdatePlaceForm(@PathVariable("id") int id, Model model) {

		Place place = placeService.get(id);
		model.addAttribute("placeForm", place);
		model.addAttribute("isNew", false);

//		populateDefaultModel(model);

		return "jsp/places/placeForm";

	}

	// delete place
	@RequestMapping(value = "/places/delete/{id}", method = RequestMethod.GET)
	public String deletePlace(@PathVariable("id") int id, final RedirectAttributes redirectAttributes, Locale locale) {

		placeService.remove(id);

		redirectAttributes.addFlashAttribute("css", "success");
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
		bean.setBasename("messages");
		String msg = bean.getMessage("msg.place_is_deleted", null, locale);
		redirectAttributes.addFlashAttribute("msg", msg);

		return "redirect:/index";

	}

//	private void populateDefaultModel(Model model) {
//
//		List<String> manufacturer = new ArrayList<>();
//		for (Manufacturers manuf : Manufacturers.values()) {
//			manufacturer.add(manuf.getString());
//		}
//		model.addAttribute("manufacturerList", manufacturer);
//
//	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex, Locale locale) {

		ModelAndView model = new ModelAndView();
		model.setViewName("places/show");
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
		bean.setBasename("messages");
		String msg = bean.getMessage("msg.place_not_found", null, locale);
		model.addObject("msg", msg);

		return model;

	}
}
