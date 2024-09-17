package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
	
	@Autowired
	StudentDAO studentdao;
	
	@RequestMapping("index")
	public String index()
	{
		return "index.jsp";
	}
	
	@RequestMapping("addUser")
	public String addUser(Student student)
	{
		studentdao.save(student);
		return "index.jsp";
	}
	
	@RequestMapping("readUser")
	public ModelAndView readUser(@RequestParam int id)
	{
		ModelAndView mav = new ModelAndView("showUser.jsp");
		Student student = studentdao.findById(id).orElse(new Student());
		mav.addObject(student);
		return mav;
	}

	@RequestMapping("deleteUser")
	public ModelAndView deleteUser(@RequestParam int id)
	{
		ModelAndView mav = new ModelAndView("deleteUser.jsp");
		Student student = studentdao.findById(id).orElse(new Student());
		studentdao.deleteById(id);
		mav.addObject(student);
		return mav;
	}
	
	@RequestMapping("updateUser")
	public ModelAndView updateUser(Student student)
	{
		ModelAndView mav = new ModelAndView("updateUser.jsp");
		student = studentdao.findById(student.getId()).orElse(new Student());
		studentdao.deleteById(student.getId());
		mav.addObject(student);
		return mav;
	}
}
