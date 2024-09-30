package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Search;

import java.security.Principal;
import java.util.List;

public interface SearchService {
    List<Search> getSearchAcrossRole(String keyword , String username);
}
