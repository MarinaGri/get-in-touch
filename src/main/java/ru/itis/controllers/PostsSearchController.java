package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.dto.FilterDto;
import ru.itis.dto.response.PostResponse;
import ru.itis.services.PostsService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts/search")
@Controller
public class PostsSearchController {

    private final PostsService postsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/subscribers")
    public String getByUsersSubs(ModelMap map) {
        fillMap(map, "page.posts-top.subs", postsService.getByUsersRating());
        return "posts_search";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/skills")
    public String getByUsersSkills(ModelMap map) {
        fillMap(map, "page.posts-top.skills", postsService.getByUsersSkills());
        return "posts_search";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/activities")
    public String getByUsersActivities(ModelMap map) {
        fillMap(map, "page.posts-top.activities", postsService.getByUsersActivities());
        return "posts_search";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getByFilter(FilterDto filter, ModelMap map) {
        map.put("filter", filter);
        map.put("posts", postsService.getByFilter(filter));
        return "posts_search";
    }

    private void fillMap(ModelMap map, String title, List<PostResponse> posts){
        map.put("title", title);
        map.put("posts", posts);
        map.put("filter", new FilterDto());
    }
}
