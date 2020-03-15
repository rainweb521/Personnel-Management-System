package ahualy.neepu.controller;

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
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.Document;
import ahualy.neepu.pojo.User;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.page.PageModel;

@Controller
public class DocumentController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	   //如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/document/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("document/list");
			return mv;
		}
		//如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/document/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/document/list";
			return blank;
		}
		@RequestMapping(value="/document/list",method=RequestMethod.GET)
		 public String index(Integer pageIndex,Model model,String content,Document document){
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
						
			List<Document> job_list = ahualyservice.get_DocumentList(document,pageModel);
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = ahualyservice.countDocument(content);
				job_list = ahualyservice.get_DocumentLikeList(content);
			}
			model.addAttribute("count",count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "document/list";
		}
		@RequestMapping(value="/document/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Document job = ahualyservice.get_DocumentInfo(id);
				model.addAttribute("job",job);
			}
			return "/document/add";
		}
		
		@RequestMapping(value="/document/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				Document job = ahualyservice.get_DocumentInfo(id);
				model.addAttribute("job",job);
			}
			return "/document/edit";
		}
		
		
		@RequestMapping(value="/document/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute Document document ,Integer id,HttpSession session
				,Integer user_id )
				 throws Exception{
			if(id!=null){
				String path = session.getServletContext().getRealPath("/WEB-INF/files");
				String filename = document.getFile().getOriginalFilename();
				
				File tempFile = new File(path+"/"+filename);
				 tempFile.createNewFile();  
				 document.getFile().transferTo(tempFile);  
				 document.setFilename(filename);
				ahualyservice.update_DocumentInfo(document);
			}else{
				/**
				 * 上传文件
				 */
				String path = session.getServletContext().getRealPath("/WEB-INF/files");
				String filename = document.getFile().getOriginalFilename();
				
				File tempFile = new File(path+"/"+filename);
				 tempFile.createNewFile();  
				 document.getFile().transferTo(tempFile);  
				 document.setFilename(filename);
				 System.out.println("******"+user_id);
				 this.genericAssociation(user_id,document);
				ahualyservice.insert_DocumentInfo(document);
			}
			mv.setViewName("redirect:/document/list");
			return mv;
		}
		@RequestMapping(value="/document/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.delete_DocumentInfo(id);
			}
		}
		
		
		/**
		 * 处理文档下载请求
		 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
		 * */
		@RequestMapping(value="/document/downLoad")
		 public ResponseEntity<byte[]>  downLoad(
				 Integer id,
				 HttpSession session) throws Exception{
			// 根据id查询文档
			Document target = ahualyservice.get_DocumentInfo(id);
			String fileName = target.getFilename();
			// 上传文件路径
			String path = session.getServletContext().getRealPath(
					"/WEB-INF/files");
			// 获得要下载文件的File对象
			File file = new File(path+"/"+ fileName);
			// 创建springframework的HttpHeaders对象
			HttpHeaders headers = new HttpHeaders();  
	        // 下载显示的文件名，解决中文名称乱码问题  
	        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
	        // 通知浏览器以attachment（下载方式）打开图片
	        headers.setContentDispositionFormData("attachment", downloadFielName); 
	        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        // 201 HttpStatus.CREATED
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                headers, HttpStatus.CREATED); 
		}
		
		
		
		private void genericAssociation(Integer user_id,Document document){
			if(user_id != null){
				User user = new User();
				user.setId(user_id);
				document.setUser(user);
			}
		}
}
