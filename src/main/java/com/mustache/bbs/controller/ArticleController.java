package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleRepository repository;

    @GetMapping("/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articles = repository.findAll();
        log.info("list={}", articles);
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/{id}")
    public String findSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = repository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> findArticle = repository.findById(id);
        if (!findArticle.isEmpty()) {
            model.addAttribute("article", findArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message", String.format("%d을 찾지 못했습니다.", id));
            return "articles/error";
        }
    }

    @PostMapping("")
    public String saveArticle(ArticleDto form) {
        log.info("title={}", form.getTitle());
        Article savedArticle = repository.save(form.toEntity());
        log.info("generatedId={}", savedArticle.getId());
        return "redirect:/articles/" + savedArticle.getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto form, Model model) {
        log.info("form.getTitle()={}, form.getContent()={}",form.getTitle(), form.getContent());
        Article saveArticle = repository.save(form.toEntity());
        model.addAttribute("article", saveArticle);
        return "redirect:/articles/" + saveArticle.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/articles";
    }
}
