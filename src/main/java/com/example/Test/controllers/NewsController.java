package com.example.Test.controllers;

import com.example.Test.models.News;
import com.example.Test.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "news/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("news", new News());
        return "news/adde-news";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("news") @Valid News newOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "news/adde-news";

        newsRepository.save(newOne);
        return "redirect:/news/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("title") String title,
            Model model) {
        List<News> newList = newsRepository.findByTitle(title);
        model.addAttribute("news", newList);

        return "news/index";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> newsArrayList = new ArrayList<>();
        news.ifPresent(newsArrayList::add);
        model.addAttribute("news", newsArrayList);
        return "news/info-news";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    ) {
        News news = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(news);

        //newsRepository.deleteById(id);
        return "redirect:/news/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    ) {
        if (!newsRepository.existsById(id)) {
            return "redirect:/news/";
        }
        Optional<News> newsOptional = newsRepository.findById(id);
        ArrayList<News> newsArrayList = new ArrayList<>();
        newsOptional.ifPresent(newsArrayList::add);
        model.addAttribute("news", newsArrayList.get(0));
        return "news/edit-news";
    }

    @PostMapping("/edit/{id}")
    public String editNews(
            @PathVariable("id") Long id,
            @ModelAttribute("news") @Valid News newOne, BindingResult bind,
            Model model
    ) {

        if (!newsRepository.existsById(id)) {
            return "redirect:/news/";
        }
        if (bind.hasErrors())
            return "news/edit-news";

        newOne.setId(id);

        newsRepository.save(newOne);
        return "redirect:/news/";
    }


}
