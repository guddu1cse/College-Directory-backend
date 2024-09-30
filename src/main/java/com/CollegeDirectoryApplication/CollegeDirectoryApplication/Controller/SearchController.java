package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Controller;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Search;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class SearchController {

    @Autowired private SearchService searchService;
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Search>> getSearch(@PathVariable String keyword , Principal principal){
        System.out.println(principal.getName());
        return new ResponseEntity<>(searchService.getSearchAcrossRole(keyword , principal.getName()) , HttpStatus.OK);
    }
}
