package ahualy.neepu.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import ahualy.neepu.pojo.TrainData;
import ahualy.neepu.pojo.User;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.page.PageModel;

@Controller
public class TrainDataController {
	
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	   //如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/traindata/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("traindata/list");
			return mv;
		}
		//如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/traindata/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/traindata/list";
			return blank;
		}
		@RequestMapping(value="/traindata/list",method=RequestMethod.GET)
		 public String index(Integer pageIndex,Model model,String content,TrainData trainData){
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
						
			List<TrainData> job_list = ahualyservice.get_TrainDataList(trainData,pageModel);
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = ahualyservice.countTrainData(content);
				job_list = ahualyservice.get_TrainDataLikeList(content);
			}
			model.addAttribute("count",count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "traindata/list";
		}
		@RequestMapping(value="/traindata/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				TrainData job = ahualyservice.get_TrainDataInfo(id);
				model.addAttribute("job",job);
			}
			return "/traindata/add";
		}
		
		@RequestMapping(value="/traindata/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				TrainData job = ahualyservice.get_TrainDataInfo(id);
				model.addAttribute("job",job);
			}
			return "/traindata/edit";
		}
		
		
		@RequestMapping(value="/traindata/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute TrainData trainData ,Integer id,HttpSession session
				,Integer user_id ,HttpServletRequest request)
				 throws Exception{
			if(id!=null){
				String path = session.getServletContext().getRealPath("/WEB-INF/upload");
				String filename = trainData.getFile().getOriginalFilename();
				
				 File tempFile = new File(path+"/"+filename);
				 tempFile.createNewFile();  
				 trainData.getFile().transferTo(tempFile);  
				 trainData.setFilename(filename);
				ahualyservice.update_TrainDataInfo(trainData);
			}else{
				/**
				 * 上传文件
				 */
				//String path = request.getRealPath("/WEB-INF/upload");
				String path = session.getServletContext().getRealPath("/WEB-INF/upload");
				String filename = trainData.getFile().getOriginalFilename();
				
				//path = "F:\\TrainData\\JavaWed\\";
				System.out.println("文件路径*****"+path);
				 File tempFile = new File(path+"/"+filename);
				 
				 tempFile.createNewFile();  
				 trainData.getFile().transferTo(tempFile); 
				 
				 trainData.setFilename(filename);
				 System.out.println("******"+user_id);
				 this.genericAssociation(user_id,trainData);
				ahualyservice.insert_TrainDataInfo(trainData);
			}
			mv.setViewName("redirect:/traindata/list");
			return mv;
		}
		@RequestMapping(value="/traindata/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.delete_TrainDataInfo(id);
			}
		}
		
		
		/**
		 * 处理文档下载请求
		 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
		 * */
		@RequestMapping(value="/traindata/downLoad")
		 public ResponseEntity<byte[]>  downLoad(
				 Integer id,
				 HttpSession session) throws Exception{
			// 根据id查询文档
			TrainData target = ahualyservice.get_TrainDataInfo(id);
			String fileName = target.getFilename();
			// 上传文件路径
			String path = session.getServletContext().getRealPath(
	                "/WEB-INF/upload");
			
			// 获得要下载文件的File对象
			//path = "F:\\TrainData\\JavaWed\\";
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
		
		
		
		private void genericAssociation(Integer user_id,TrainData trainData){
			if(user_id != null){
				User user = new User();
				user.setId(user_id);
				trainData.setUser(user);
			}
		}
		
		//  视频播放页面跳转
		@RequestMapping(value="/traindata/play",method=RequestMethod.GET)
		 public String play(String filename,Model model){
			
			filename=filename.substring(0,filename.length()-4);
			System.out.println("********"+filename);
			
			model.addAttribute("filename", filename);
			
			return "traindata/play";
		}
	
	
	

}
