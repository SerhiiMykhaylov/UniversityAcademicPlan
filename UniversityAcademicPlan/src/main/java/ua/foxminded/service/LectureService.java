package ua.foxminded.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.dto.LectureDto;
import ua.foxminded.entity.Lecture;
import ua.foxminded.exceptions.LectureException;
import ua.foxminded.mapper.CycleAvoidingMappingContext;
import ua.foxminded.mapper.LectureMapper;
import ua.foxminded.repository.LectureJPARepository;

@Service
@Transactional(readOnly = true)
public class LectureService {

	private LectureMapper mapper;
	private LectureJPARepository repository;
	private final Logger logger = LogManager.getLogger();
	private final CycleAvoidingMappingContext context;

	public LectureService(LectureJPARepository repository, LectureMapper mapper, CycleAvoidingMappingContext context) {
		this.repository = repository;
		this.mapper = mapper;
		this.context = context;
	}
	
	public LectureDto get(long id) throws LectureException {
		logger.info("Get by id = {}", id);
		Lecture lectureResult = repository.findById(id)
				.orElseThrow(()-> new LectureException("Cann't find lecture by id = " + id));
		LectureDto lectureDto = mapper.lectureToLectureDto(lectureResult, context);
		logger.info("OUT: get result Lecture = {}", lectureDto);
		return lectureDto;
	}
	
	public LectureDto getByName(String name) throws LectureException {
		logger.info("Get by name = {}", name);
		Lecture lectureResult = repository.findByName(name)
				.orElseThrow(()-> new LectureException("Cann't find lecture by name = " + name));
		LectureDto lectureDto = mapper.lectureToLectureDto(lectureResult, context);
		logger.info("OUT: get result Lecture = {}", lectureDto);
		return lectureDto;
	}
	
	public List<LectureDto> getAll() {
		logger.info("Get all");
		List<LectureDto> lectures = repository.findAll()
				.stream().map(el-> mapper.lectureToLectureDto(el, context)).collect(Collectors.toList());
		logger.info("OUT: result get all lectures = {}", lectures);
		return lectures;
	}
	
	@Transactional(readOnly = false)
	public LectureDto add(LectureDto lecture) {
		logger.info("Add new lecture = {}", lecture);
		Lecture lectureDao = mapper.lectureDtoToLecture(lecture, context);
		Lecture lectureResult = repository.saveAndFlush(lectureDao);
		LectureDto lectureDto = mapper.lectureToLectureDto(lectureResult, context);
		logger.info("OUT result lecture = {}", lectureDto);
		return lectureDto;
	}
	
	@Transactional(readOnly = false)
	public boolean delete(long id) {
		logger.info("Delete lecture by id = {}", id);
		if (repository.existsById(id)) {
			repository.deleteById(id);
			logger.info("Deleting result = {}", true);
			return true;
		} else {
			logger.info("Deleting result = {}", false);
			return false;
		}
	}
	
	@Transactional(readOnly = false)
	public LectureDto update(LectureDto lecture) throws LectureException {
		logger.info("Update lecture = {}", lecture);
		Lecture lectureDao = mapper.lectureDtoToLecture(lecture, context);
		Lecture lectureTemp = repository.findByName(lectureDao.getName())
				.orElseThrow(()-> new LectureException("Cann't find lecture by name = " + lectureDao.getName()));
		lectureTemp.setCourse(lectureDao.getCourse());
		Lecture lectureResult = repository.saveAndFlush(lectureTemp);
		LectureDto lectureDto = mapper.lectureToLectureDto(lectureResult, context);
		logger.info("OUT result lecture = {}", lectureDto);
		return lectureDto;
	}
	
	public boolean ifExistsByName(String name) {
		logger.info("Find lecture by name = {}", name);
		boolean lectureResult = repository.existsByName(name);
		logger.info("OUT: result finding lecture = {}", lectureResult);
		return lectureResult;
	}
}
