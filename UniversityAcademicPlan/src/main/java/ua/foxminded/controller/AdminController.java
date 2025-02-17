package ua.foxminded.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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
import ua.foxminded.dto.GroupsDto;
import ua.foxminded.dto.LocationDto;
import ua.foxminded.dto.ScheduleDto;
import ua.foxminded.dto.StudentDto;
import ua.foxminded.dto.StuffDto;
import ua.foxminded.dto.TeacherDto;
import ua.foxminded.dto.UsersDto;
import ua.foxminded.entity.UserType;
import ua.foxminded.exceptions.AdminException;
import ua.foxminded.exceptions.CourseException;
import ua.foxminded.exceptions.GroupsException;
import ua.foxminded.exceptions.LocationException;
import ua.foxminded.exceptions.ScheduleException;
import ua.foxminded.exceptions.StudentException;
import ua.foxminded.exceptions.UsersException;
import ua.foxminded.service.AdminService;
import ua.foxminded.service.CourseService;
import ua.foxminded.service.GroupsService;
import ua.foxminded.service.LocationService;
import ua.foxminded.service.ScheduleService;
import ua.foxminded.service.StudentService;
import ua.foxminded.service.StuffService;
import ua.foxminded.service.TeacherService;
import ua.foxminded.service.UsersService;
import ua.foxminded.util.CourseDtoValidator;
import ua.foxminded.util.GroupsDtoValidator;
import ua.foxminded.util.ScheduleDtoValidator;
import ua.foxminded.util.UsersDtoValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UsersDtoValidator usersDtoValidator;
	private final CourseDtoValidator courseDtoValidator;
	private final UsersService usersService;
	private final AdminService adminService;
	private final StudentService studentService;
	private final TeacherService teacherService;
	private final StuffService stuffService;
	private final CourseService courseService;
	private final LocationService locationService;
	private final GroupsService groupsService;
	private final GroupsDtoValidator groupsDtoValidator;
	private final ScheduleService scheduleService;
	private final ScheduleDtoValidator scheduleDtoValidator;
	
	private String userType = UserType.ROLE_ADMIN.getUserType();
	
	public AdminController(UsersDtoValidator usersDtoValidator, UsersService usersService, TeacherService teacherService,
			StudentService studentService, AdminService adminService, CourseService courseService, LocationService locationService, CourseDtoValidator courseDtoValidator, StuffService stuffService, GroupsService groupsService, GroupsDtoValidator groupsDtoValidator, ScheduleService scheduleService, ScheduleDtoValidator scheduleDtoValidator) {
		this.usersDtoValidator = usersDtoValidator;
		this.courseDtoValidator = courseDtoValidator;
		this.usersService = usersService;
		this.adminService = adminService;
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.stuffService = stuffService;
		this.courseService = courseService;
		this.locationService = locationService;
		this.groupsService = groupsService;
		this.groupsDtoValidator = groupsDtoValidator;
		this.scheduleService = scheduleService;
		this.scheduleDtoValidator = scheduleDtoValidator;
	}
	
	@GetMapping("/user/registration")
	public String userRegistrationPage(@ModelAttribute("users") UsersDto users) {
		return "registration/user_registration";
	}
	
	@PostMapping("/user/registration")
	public String performUserRegistration(@ModelAttribute("users") UsersDto usersDto, BindingResult bindingResult) {
		usersDtoValidator.validate(usersDto, bindingResult);
		
		if (bindingResult.hasErrors())
			return "registration/user_registration";
		
		switch (usersDto.getUserType().getUserType()) {
		case "admin":
			AdminDto adminDto = new AdminDto("----", "----");
			adminDto.setId(usersDto.getId());
			adminDto.setNickName(usersDto.getNickName());
			adminDto.setPassword(usersDto.getPassword());
			adminDto.setUserType(usersDto.getUserType());
			adminService.add(adminDto);
			break;
		case "student":
			StudentDto studentDto = new StudentDto("----", "----");
			studentDto.setId(usersDto.getId());
			studentDto.setNickName(usersDto.getNickName());
			studentDto.setPassword(usersDto.getPassword());
			studentDto.setUserType(usersDto.getUserType());
			studentService.add(studentDto);
			break;
		case "teacher":
			TeacherDto teacherDto = new TeacherDto("----", "----");
			teacherDto.setId(usersDto.getId());
			teacherDto.setNickName(usersDto.getNickName());
			teacherDto.setPassword(usersDto.getPassword());
			teacherDto.setUserType(usersDto.getUserType());
			teacherService.add(teacherDto);
			break;
		case "stuff":
			StuffDto stuffDto = new StuffDto("----", "----");
			stuffDto.setId(usersDto.getId());
			stuffDto.setNickName(usersDto.getNickName());
			stuffDto.setPassword(usersDto.getPassword());
			stuffDto.setUserType(usersDto.getUserType());
			stuffService.add(stuffDto);
			break;
		}
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/user/{id}")
	public String updateUsers(@PathVariable("id") long id, Model model) {
		UsersDto usersDto;
		try {
			usersDto = usersService.get(id);
			model.addAttribute("usersDto", usersDto);
		} catch (UsersException e) {
			e.printStackTrace();
		}
		return "users";
	}
	
	@PostMapping("/user/{id}")
	public String updateUsers(@PathVariable("id") long id, @ModelAttribute("usersDto") UsersDto usersDto) {
		try {
			usersService.update(usersDto);
		} catch (UsersException e) {
			e.getMessage();
		}
		return "redirect:/showUserPage";
	}
	
	@PostMapping("/user/delet")
	public String deletUser(@ModelAttribute("usersDto") UsersDto usersDto) {
		usersService.delete(usersDto.getId());
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/course/{id}")
	public String updateCourse(@PathVariable("id") long id, Model model) {
		CourseDto courseDto;
		try {
			courseDto = courseService.get(id);
			List<LocationDto> locationDtoList = locationService.getAll();
			model.addAttribute("courseDto", courseDto);
			model.addAttribute("locationDtoList", locationDtoList);
			model.addAttribute("userType", userType);
		} catch (CourseException e) {
			e.printStackTrace();
		}
		return "course";
	}
	
	@PostMapping("/course/{id}")
	public String updateCourse(@PathVariable("id") long id, @ModelAttribute("courseDto") CourseDto courseDto) {
		try {
			CourseDto courseDtoTemp = courseService.get(id);
			LocationDto locationDto = locationService.getByName(courseDto.getLocation().getName());
			courseDtoTemp.setName(courseDto.getName());
			courseDtoTemp.setLocation(locationDto);
			courseService.update(courseDtoTemp);
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
	
	@GetMapping("/group/{id}")
	public String updateGroup(@PathVariable("id") long id, Model model) {
		GroupsDto groupsDto;
		try {
			groupsDto = groupsService.get(id);
			model.addAttribute("group", groupsDto);
			model.addAttribute("userType", userType);
		} catch ( GroupsException e) {
			e.printStackTrace();
		}
		return "group";
	}
	
	@PostMapping("/group/{id}")
	public String updateGroupName(@PathVariable("id") long id, @ModelAttribute("group") GroupsDto group) {
		try {
			GroupsDto groupsDto = groupsService.get(id);
			groupsDto.setName(group.getName());
			
			groupsService.updateName(groupsDto);
			
		} catch (GroupsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/group/registration")
	public String groupRegistrationPage(@ModelAttribute("group") GroupsDto group, Model model) {
		model.addAttribute("userType", userType);
		return "registration/group_registration";
	}
	
	@PostMapping("/group/registration")
	public String performGroupRegistration(@ModelAttribute("group") GroupsDto group, BindingResult bindingResult, Model model) {
		List<LocationDto> locationDtoList = locationService.getAll();
		model.addAttribute("locationDtoList", locationDtoList);
		groupsDtoValidator.validate(group, bindingResult);
		
		if (bindingResult.hasErrors())
			return "registration/group_registration";
		
		groupsService.add(group);
		
		return "redirect:/showUserPage";
	}
	
	@PostMapping("/deleteGroup")
	public String deletGroup(@ModelAttribute("group") GroupsDto group) {
		try {
			GroupsDto groupsTemp = groupsService.get(group.getId());
			groupsTemp.getStudent()
			.stream()
			.forEach(el->{
				try {
					studentService.deletStudentFromGroup(el);
				} catch (StudentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			groupsService.delete(group.getId());
		} catch (GroupsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/schedule/registration")
	public String scheduleRegistrationPage(@ModelAttribute("schedule") ScheduleDto schedule, Model model) {
		model.addAttribute("userType", userType);
		return "registration/schedule_registration";
	}
	
	@PostMapping("/schedule/registration")
	public String performScheduleRegistration(@ModelAttribute("schedule") ScheduleDto schedule, BindingResult bindingResult, Model model) {
		String[] s = schedule.getNameSchedule().split(",");
		LocalTime startTime = LocalTime.parse(s[0]);
		LocalTime endTime = LocalTime.parse(s[1]);
		
		ScheduleDto scheduleDto = new ScheduleDto(startTime, endTime, schedule.getDayOfWeek());
		
		scheduleDtoValidator.validate(scheduleDto, bindingResult);
		
		if (bindingResult.hasErrors())
			return "registration/schedule_registration";
		
		scheduleService.add(scheduleDto);
		
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/schedule/{id}")
	public String updateSchedule(@PathVariable("id") long id, Model model) {
		ScheduleDto scheduleDto;
		try {
			scheduleDto = scheduleService.get(id);
			List<CourseDto> courseScheduleList = scheduleDto.getCourse();
			List<CourseDto> courseLeftList = courseService.getAll()
					.stream()
					.filter(el->!courseScheduleList.contains(el))
					.collect(Collectors.toList());
			model.addAttribute("schedule", scheduleDto);
			model.addAttribute("courseLeftList", courseLeftList);
			model.addAttribute("courseScheduleList", courseScheduleList);
			model.addAttribute("userType", userType);
		} catch ( ScheduleException e) {
			e.printStackTrace();
		}
		return "schedule";
	}
	
	@PostMapping("/schedule/{id}")
	public String updateGroupName(@PathVariable("id") long id, @ModelAttribute("schedule") ScheduleDto schedule) {
		try {
			String[] s = schedule.getNameSchedule().split(",");
			LocalTime startTime = LocalTime.parse(s[0]);
			LocalTime endTime = LocalTime.parse(s[1]);
			ScheduleDto schedulesDto = scheduleService.get(id);
			schedulesDto.setDayOfWeek(schedule.getDayOfWeek());
			schedulesDto.setStartTime(startTime);
			schedulesDto.setEndTime(endTime);
			
			scheduleService.update(schedulesDto);
			
		} catch (ScheduleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/showUserPage";
	}
	
	@PostMapping("/deleteSchedule")
	public String deletSchedule(@ModelAttribute("schedule") ScheduleDto schedule) {
		try {
			ScheduleDto scheduleTemp = scheduleService.get(schedule.getId());
			scheduleService.delete(scheduleTemp.getId());
		} catch (ScheduleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/showUserPage";
	}
	
	@GetMapping("/course/registration")
	public String courseRegistrationPage(@ModelAttribute("course") CourseDto courseDto, Model model) {
		List<LocationDto> locationDtoList = locationService.getAll();
		model.addAttribute("locationDtoList", locationDtoList);
		model.addAttribute("userType", userType);
		return "registration/course_registration";
	}
	
	@PostMapping("/course/registration")
	public String performCourseRegistration(@ModelAttribute("course") CourseDto courseDto, BindingResult bindingResult, Model model) {
		List<LocationDto> locationDtoList = locationService.getAll();
		model.addAttribute("locationDtoList", locationDtoList);
		courseDtoValidator.validate(courseDto, bindingResult);
		
		if (bindingResult.hasErrors())
			return "registration/course_registration";
		try {
			courseService.add(courseDto);
		} catch (LocationException e) {
			bindingResult.rejectValue("courseError", "", "Add course error. Contact the Administrator");
			return "registration/course_registration";
		}
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/updateprofile")
	public String updateProfilePage(@RequestParam("id") long id, Model model) {
		try {
			AdminDto adminDto = adminService.get(id);
			model.addAttribute("userInfo", adminDto);
		} catch (AdminException e) {
			e.printStackTrace();
		}
		return "update_profile";
	}
	
	@PostMapping("/updateprofile")
	public String updateProfile(@ModelAttribute("userInfo") AdminDto adminDto) {
		try {
			AdminDto adminResult = adminService.get(adminDto.getId());
			adminResult.setFirstName(adminDto.getFirstName());
			adminResult.setLastName(adminDto.getLastName());
			adminService.update(adminResult);
		} catch (AdminException e) {
			e.printStackTrace();
		}
		return "redirect:/showUserPage";
	}
	
	@GetMapping("/updatePassword")
	public String updatePasswordGet(@RequestParam("id") long id, Model model) {
		try {
			AdminDto adminDto = adminService.get(id);
			model.addAttribute("userInfo", adminDto);
		} catch (AdminException e) {
			e.printStackTrace();
		}
		return "update_password";
	}
	
	@PostMapping("/updatePassword")
	public String updatePasswordPost(@ModelAttribute("userInfo") AdminDto adminDto) {
		try {
			AdminDto adminResult = adminService.get(adminDto.getId());
			adminResult.setPassword(adminDto.getPassword());
			adminService.update(adminResult);
		} catch (AdminException e) {
			e.printStackTrace();
		}
		return "redirect:/showUserPage";
	}
}
