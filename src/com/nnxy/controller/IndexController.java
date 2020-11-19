package com.nnxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
  
  
  // @RequestMapping("/test")
  @RequestMapping(value = "/test")
  public String test() {
    System.out.println("test:没惊喜...");
    
    return "welcome";
  }
  @RequestMapping("/test1")
  public String test1(){
    return "test1";
  }
  // @RequestMapping("/test2")
  // public String test2(@RequestParam String username){
  // System.out.println("test2()...");
  // System.out.println("用户名：" + username);
  //
  // return "welcome";
  // }
  
  // @RequestMapping("/test2")
  // public String test2(@RequestParam(value="username",required=false) String
  // username){
  // System.out.println("test2()...");
  // System.out.println("用户名：" + username);
  //
  // return "welcome";
  // }
  
  @RequestMapping(value="/test2",method=RequestMethod.POST)
  public String test2(String username, String password, Model model){
    
    ModelAndView modelAndView =new ModelAndView();
    Map<String,String> modelMap = new HashMap<>();
    
    modelMap.put("username", username);
    modelMap.put("password", password);
    modelAndView.addAllObjects(modelMap);
    
    modelAndView.setViewName("welcome");
    System.out.println("用户名:"+username);
    System.out.println("密码:"+password);
    
    model.addAttribute("username", username);
    model.addAttribute("password", password);
    
    return "welcome";
  }

	/**
	 * 单文件上传
	 *
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam(value = "attach", required = false) MultipartFile attach
			, HttpServletRequest request) {

		System.out.println("【单文件上传】uploadFile()...");
		
		saveOneFile(attach, request);

		return "fileUploadResult";
	}
	
	// 多文件上传
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public String uploadFiles(@RequestParam(value = "attachs", required = false) MultipartFile[] attachs
			, HttpServletRequest request) {
		
		for (int i = 0; i < attachs.length; i++) {
			MultipartFile attach = attachs[i];

			saveOneFile(attach, request);
		}
		
		return "uploads";
	}

	// 单文件上传
	public void saveOneFile(MultipartFile attach, HttpServletRequest request) {

		// 获取服务器存放上传文件的文件夹路径
		String path = request.getSession().getServletContext().getRealPath("/image/");
		System.out.println("uploadFile path: " + path);
    
    // 获取上传的文件名全称
    String fileName=attach.getOriginalFilename();
    // 获取上传文件的后缀名
    String suffix=fileName.substring(fileName.lastIndexOf("."));
		
		// 新文件名：前缀+"-"+当前系统时间+"."+后缀
		String newFileName = fileName+"-"+System.currentTimeMillis()+suffix;
		System.out.println("newFileName：" + newFileName);
		//end  : add by yzx20200927===============

		// 创建文件（全路径名）
    File tempFile=new File(path);
    if(!tempFile.exists()){
      tempFile.mkdir();
    }
    File file=new File(path + newFileName);

		try {
			// 向文件中写入数据
			attach.transferTo(file);
			request.setAttribute("newFileName",newFileName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("fileUploadError", " * 上传失败！");
		}

	}
}
