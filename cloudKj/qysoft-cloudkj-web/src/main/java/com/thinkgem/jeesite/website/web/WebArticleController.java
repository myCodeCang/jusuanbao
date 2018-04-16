/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.website.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.security.shiro.session.SessionDAO2;
import com.thinkgem.jeesite.common.utils.StringUtils2;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.dao.ArticleDataDao;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.CmsArticleData;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CmsArticleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文章
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/webArticle")
public class WebArticleController extends BaseController {
	@Resource
	private SessionDAO2 sessionDAO2;

	@Autowired
	private ArticleDataDao articleDataDao;

	@Resource
	private ArticleService articleService;

	@Resource
	private CmsArticleDataService cmsArticleDataService;


	/**
	 * 根据栏目id查询文章
	 * 表名:
	 * 条件:
	 * 其他:
	 */
	@RequestMapping(value = "/findByCategory", method = RequestMethod.POST)
	public String  list(HttpServletRequest request, HttpServletResponse response, Model model) {
		String categoryId = request.getParameter("categoryId");
		String keywords = request.getParameter("keywords");
		Article article = new Article();
		if(StringUtils2.isNotBlank(categoryId)){
			Category category = new Category();
			category.setId(categoryId);
			article.setCategory(category);
		}
		if(StringUtils2.isNotBlank(keywords)){
			article.setKeywords(keywords);
		}


		Page<Article> page =  articleService.findPage(new Page<Article>(request,response), article, true);
		model.addAttribute("page",page);
		return success("成功!!",response, model);
	}

	/**
	 * 根据文章id 查询文章详情 json
	 * 表名:
	 * 条件:
	 * 其他:
	 */
	@ResponseBody
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model) {

		CmsArticleData cmsArticleData = cmsArticleDataService.get(request.getParameter("articleId"));
		model.addAttribute("article",cmsArticleData);
		return success("成功!!",response, model);
	}



	/**
	 * 根据文章keyword 查询文章详情 json
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/detailByKeyword", method = RequestMethod.POST)
	public String detailByKeyword(HttpServletRequest request, HttpServletResponse response, Model model) {
		String keywords = request.getParameter ("keywords");
		model.addAttribute("agreement",articleService.getArticleDataByKeyword (keywords));
		return success("成功!!",response, model);
	}

	/**
	 * 根据关键字分组查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "findByKeyGroup", method = RequestMethod.POST)
	public String findByKeyGroup(HttpServletRequest request ,HttpServletResponse response , Model model){
		String categoryId = request.getParameter("categoryId");
		List<Article> byKeyGroup = articleService.findByKeyGroup(categoryId);
		model.addAttribute("list",byKeyGroup);
		return success("成功",response,model);
	}

	/**
	 * 根据关键字查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "findByKeywords", method = RequestMethod.POST)
	public String findByKeywords(HttpServletRequest request ,HttpServletResponse response , Model model){
		String keywords = request.getParameter("keywords");
		List<Article> byKeyGroup = articleService.findByKeywords(keywords);
		model.addAttribute("list",byKeyGroup);
		return success("成功",response,model);
	}

	/**
	 * 根据文章keyword 查询文章详情 json 返回list
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findDetailByKeywords", method = RequestMethod.POST)
	public String findDetailByKeywords(HttpServletRequest request, HttpServletResponse response, Model model) {
		String keywords = request.getParameter ("keywords");
		List<Article> list = articleService.findByKeywords(keywords);
		for (Article article : list) {
			article.setArticleData(articleDataDao.get(article.getId()));
		}
		model.addAttribute("list",list);
		return success("成功!!",response, model);
	}

}
