package com.blogApplication.blogDemo.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
  private List<PostDto2> content;
  private int pageNumber;
  private int pageSize;
  private long totalElements;
  private int totalPages;
  
  private boolean lastPage;
}
