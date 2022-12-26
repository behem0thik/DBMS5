package ru.rgr.Blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.rgr.Blog.service.PostService;
import ru.rgr.Blog.service.TagService;
import ru.rgr.Blog.service.UserService;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("main");
//        registry.addViewController("/index").setViewName("main");
        registry.addViewController("/article").setViewName("article");
        registry.addViewController("/articles").setViewName("articles");
        registry.addViewController("/account").setViewName("post-create");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                        "/static/styles/**",
                        "/static/img/**")
                .addResourceLocations(
                        "/static/styles/",
                        "/static/img/");
    }

    @Bean
    public CommandLineRunner dataLoader(UserService userService, PostService postService, TagService tagService) {
//        User primeUser = new User("Valera", "abobus");
//
//
//        Tag tag1 = new Tag("IT");
//        Tag tag2 = new Tag("Video Games");
//        Tag tag3 = new Tag("Science");
//
//        Set<Tag> tagsForPost1 = new HashSet<>();
//        tagsForPost1.add(tag1);
//        tagsForPost1.add(tag3);
//
//        Set<Tag> tagsForPost2 = new HashSet<>();
//        tagsForPost2.add(tag2);

        return args -> {
//            userService.create(new User("Valera", "abobus"));
//
//            tagService.create(tag1);
//            tagService.create(tag2);
//            tagService.create(tag3);
//            postService.create(new Post(UUID.fromString("ca1e2e46-e78c-42fe-808f-7fe6cee9ce41"), "Computer Science",
//                    "...Quality Content about CS...", null, tagsForPost1));
//            postService.create(new Post(UUID.fromString("ca1e2e46-e78c-42fe-808f-7fe6cee9ce41"), "Minecraft my favourite game",
//                    "...Quality Content about Minecraft...", null, tagsForPost2));
        };
    }
}
