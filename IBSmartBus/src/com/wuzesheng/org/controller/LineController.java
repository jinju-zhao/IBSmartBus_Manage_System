package com.wuzesheng.org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuzesheng.org.domain.Line;
import com.wuzesheng.org.service.LineService;

/**
 * @Author 作者 : 吴泽胜
 * @Date 创建时间：2020年6月16日 下午1:51:30
 */

@Controller
@RequestMapping("/line")
public class LineController {

	
	//注入service对象
	@Resource
	private LineService lineService;

	
	/*
	 * 查询所有数据，给页面返回Json格式数据
	 */
	@RequestMapping("/list")
	@ResponseBody	 //用于转换对象
	public List<Line> list(){
		//查询数据
		List<Line> list = lineService.FindAll();		
		return list;
	}

	//设计Map聚合存储需要给页面的对象数据
	private Map<String,Object> result = new HashMap<String,Object>();
	
	/**
	 * 保存数据
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Line line){
		System.out.println("用户保存数据操作");
		System.out.println(line);
		try {
			lineService.Linesave(line);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 删除数据
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer lineCode){
		System.out.println("删除用户");
		System.out.println(lineCode.toString());
		try {
			lineService.LineDelete(lineCode);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 根据id 查询对象
	 */
	@RequestMapping("/findById")
	@ResponseBody
	public Line findById(Integer lineCode,Model model){
		System.out.println(lineCode);
		Line line = lineService.findById(lineCode);
		System.out.println(line);
		model.addAttribute("findById",line);
		return line; 
	}
}
