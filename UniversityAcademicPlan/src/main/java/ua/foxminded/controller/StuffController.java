package ua.foxminded.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.foxminded.dto.AdminDto;
import ua.foxminded.dto.CourseDto;
import ua.foxminded.dto.LocationDto;
import ua.foxminded.dto.StuffDto;
import ua.foxminded.exceptions.AdminException;
import ua.foxminded.exceptions.CourseException;
import ua.foxminded.exceptions.LocationException;
import ua.foxminded.exceptions.StuffException;
import ua.foxminded.service.AdminService;
import ua.foxminded.service.CourseService;
import ua.foxminded.service.LocationService;
import ua.foxminded.service.StudentService;
import ua.foxminded.service.StuffService;
import ua.foxminded.service.TeacherService;
import ua.foxminded.service.UsersService;
import ua.foxminded.util.CourseDtoValidator;
import ua.foxminded.util.UsersDtoValidator;

@Controller
@RequestMapping("/stuff")
public class StuffController {
	
	private final UsersDtoValidator usersDtoValidator;
	private final CourseDtoValidator courseDtoValidator;
	private final UsersService usersService;
	private final AdminService adminService;
	private final StudentService studentService;
	private final TeacherService teacherService;
	private final StuffService stuffService;
	private final CourseService courseService;
	private final LocationService locationService;
	
	public StuffController(UsersDtoValidator usersDtoValidator, CourseDtoValidator courseDtoValidator,
			UsersService usersService, AdminService adminService, StudentService studentService,
			TeacherService teacherService, StuffService stuffService, CourseService courseService,
			LocationService locationService) {
		this.usersDtoValidator = usersDtoValidator;
		this.courseDtoValidator = courseDtoValidator;
		this.usersService = usersService;
		this.adminService = adminService;
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.stuffService = stuffService;
		this.courseService = courseService;
		this.locationService = locationService;
	}

	@GetMapping("/course/{id}")
	public String updateCourse(@PathVariable("id") long id, Model model) {
		CourseDto courseDto;
		try {
			courseDto = courseService.get(id);
			List<LocationDto> locationDtoList = locationService.getAll();
			model.addAttribute("courseDto", courseDto);
			model.addAttribute("locationDtoList", locationDtoList);
		} catch (CourseException e) {
			e.printStackTrace();
		}
		return "adminpanel/course";
	}
	
	@PostMapping("/course/{id}")
	public String updateCourse(@PathVariable("id") long id, @ModelAttribute("courseDto") CourseDto courseDto) {
		try {
			courseService.update(courseDto);
		} catch (CourseException | LocationException e) {
			e.getMessage();
		}
		return "redirect:/showUserPage";
	}
	
	@PostMapping("/course/delet")
	public String deletCourse(@ModelAttribute("courseDto") CourseDto courseDto) {
		courseService.delete(courseDto.getId());
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/course/registration")
	public String courseRegistrationPage(@ModelAttribute("course") CourseDto courseDto, Model model) {
		List<LocationDto> locationDtoList = locationService.getAll();
		model.addAttribute("locationDtoList", locationDtoList);
		return "registration/course_registration";
	}
	
	@PostMapping("/course/registration")
	public String performCourseRegistration(@ModelAttribute("course") CourseDto course, BindingResult bindingResult, Model model) {
		List<LocationDto> locationDtoList = locationService.getAll();
		model.addAttribute("locationDtoList", locationDtoList);
		courseDtoValidator.validate(course, bindingResult);
		
		if (bindingResult.hasErrors())
			return "registration/course_registration";
		try {
			courseService.add(course);
		} catch (LocationException e) {
			bindingResult.rejectValue("courseError", "", "Add course error. Contact the Administrator");
			return "registration/course_registration";
		}
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/updateprofile")
	public String updateProfilePage(@RequestParam("id") long id, Model model) {
		try {
			StuffDto stuffDto = stuffService.get(id);
			model.addAttribute("userInfo", stuffDto);
		} catch (StuffException e) {
			e.printStackTrace();
		}
		return "update_profile";
	}
	
	@PostMapping("/updateprofile")
	public String updateProfile(@ModelAttribute("userInfo") StuffDto stuffDto) {
		try {
			StuffDto stuffResult = stuffService.get(stuffDto.getId());
			stuffResult.setFirstName(stuffDto.getFirstName());
			stuffResult.setLastName(stuffDto.getLastName());
			stuffService.update(stuffResult);
		} catch (StuffException e) {
			e.printStackTrace();
		}
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/updatePassword")
	public String updatePasswordGet(@RequestParam("id") long id, Model model) {
		try {
			StuffDto stuffDto = stuffService.get(id);
			model.addAttribute("userInfo", stuffDto);
		} catch (StuffException e) {
			e.printStackTrace();
		}
		return "update_password";
	}
	
	@PostMapping("/updatePassword")
	public String updatePasswordPost(@ModelAttribute("userInfo") StuffDto stuffDto) {
		try {
			StuffDto stuffResult = stuffService.get(stuffDto.getId());
			stuffResult.setPassword(stuffDto.getPassword());
			stuffService.update(stuffResult);
		} catch (StuffException e) {
			e.printStackTrace();
		}
		return "redirect:/showUserPage";
	}
}
