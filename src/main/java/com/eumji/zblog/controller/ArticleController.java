package com.eumji.zblog.controller;

import com.eumji.zblog.service.CategoryService;
import com.eumji.zblog.service.PartnerService;
import com.eumji.zblog.service.TagService;
import com.eumji.zblog.vo.*;
import com.eumji.zblog.service.ArticleService;
import com.eumji.zblog.util.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

import java.util.List;

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private PartnerService partnerService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagService tagService;

    @RequestMapping("/list/{id}")
    @ResponseBody
    public ResultInfo articleList(Pager pager){

        return null;
    }

    @RequestMapping("/load")
    public String loadArticle(Pager<Article> pager, Model model){
        List<ArticleCustom> articleList = articleService.articleList(pager);
        System.out.println(articleList);
        model.addAttribute("articleList",articleList);
        return "blog/part/articleSummary";
    }

    /**
     * 加载文章
     * @return
     */
    @RequestMapping("/article/details/{articleId}")
    public String loadArticle(@PathVariable Integer articleId, Model model){
        List<Partner> partnerList = partnerService.findAll();
        List<CategoryCustom> categoryList = categoryService.initCategoryList();
        int articleCount = articleService.getArticleCount();
        int tagCount = tagService.getTagCount();
        ArticleCustom articleCustom = articleService.getArticleCustomById(articleId);
        model.addAttribute("article",articleCustom);
        model.addAttribute("categoryCount",categoryList.size());
        model.addAttribute("articleCount",articleCount);
        model.addAttribute("tagCount",tagCount);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("partnerList",partnerList);
        return "article";
    }


}
