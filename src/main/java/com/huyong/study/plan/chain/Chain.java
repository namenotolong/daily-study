package com.huyong.study.plan.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 10:11 下午
 */
public class Chain {
    List<Filter> filters = new ArrayList<>();

    Chain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    void doChain(Request request) {
        for (final Filter filter : filters) {
            filter.filter(request);
        }
    }
}
