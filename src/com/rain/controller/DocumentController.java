package com.rain.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.rain.domain.Document;
import com.rain.service.RainService;

@Controller
public class DocumentController {
	@Autowired
	@Qualifier("RainService")
	private RainService rainservice;
	// 如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/document/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("document/list");
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/document/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/document/list";
			return blank;
		}
		@RequestMapping(value="/document/list",method=RequestMethod.GET)
		 public String index(Model model,String content){
			List<Document> job_list = rainservice.get_DocumentList();
			if (content!=null){
				job_list = rainservice.get_DocumentLikeList(content);
			}
			model.addAttribute("list",job_list);
			return "document/list";
		}
		@RequestMapping(value="/document/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Document job = rainservice.get_DocumentInfo(id);
				model.addAttribute("job",job);
			}
			return "/document/add";
		}
		@RequestMapping(value="/document/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute Document document ,Integer id,HttpSession session
				 )
				 throws Exception{
			System.out.println(id);
			if(id!=null){
				rainservice.update_DocumentInfo(document);
			}else{
				/**
				 * 上传文件
				 */
				String path = session.getServletContext().getRealPath("/upload/");
				String filename = document.getFile().getOriginalFilename();
				path = "C://Users//Rain//Documents//RainMe//JavaWed//";
				File tempFile = new File(path+File.separator+filename);
				 tempFile.createNewFile();  
				 document.getFile().transferTo(tempFile);  
				document.setFilename(filename);
				rainservice.insert_DocumentInfo(document);
			}
			mv.setViewName("redirect:/document/list");
			return mv;
		}
		@RequestMapping(value="/document/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				rainservice.delete_DocumentInfo(id);
			}
		}
}
