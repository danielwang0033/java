package com.coin.service;

import com.coin.entity.ThreadTags;
import com.coin.req.ForumsReq;

import java.util.List;

public interface ThreadTagsService {

    List<ThreadTags> tags(ForumsReq req);
}
